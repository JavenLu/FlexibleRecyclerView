# FlexibleRecyclerView
Delegate 高度解耦与灵活性的 插拔式(乐高模式)  RecyclerView    

此项目是根据HANNES DORFMANN 的一篇文章《JOE'S GREAT ADAPTER HELL ESCAPE》构建完成的。
文章是根据一个开发者的小故事对Delegate 模式进行逐步的揭秘，这种设计思路可以拓宽你的视野，对
构建高质量项目有所助益！  

public class CommonAdapter<T extends List<DisplayItem>> extends RecyclerView.Adapter {
    private T itemList;
    private AdapterDelegatesManager<T> adapterDelegatesManager;
    private Activity activity;

   public CommonAdapter(Activity activity, T itemList) {
        this.itemList = itemList;
        this.activity = activity;

        adapterDelegatesManager = new AdapterDelegatesManager<>();
        adapterDelegatesManager.addDelegate((AdapterDelegate<T>) new BeeDelegate(activity));
        adapterDelegatesManager.addDelegate((AdapterDelegate<T>) new CatDelegate(activity));
        adapterDelegatesManager.addDelegate((AdapterDelegate<T>) new HeaderDelegate(activity));
        adapterDelegatesManager.addDelegate((AdapterDelegate<T>) new FooterDelegate(activity));

    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegatesManager.getItemViewType(itemList, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapterDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapterDelegatesManager.onBindViewHolder(itemList, position, holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        adapterDelegatesManager.onBindViewHolder(itemList, position, holder, payloads);
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public void addHeaderAndFooter(boolean isAddHeader, boolean isAddFooter, DisplayItem headerData, DisplayItem footerData)     {

       if (isAddHeader && isAddFooter) {
            addHeaderOrFooter(headerData, 0);
            addHeaderOrFooter(footerData, itemList.size());
        } else if (isAddHeader && headerData != null) {
            addHeaderOrFooter(headerData, 0);
        } else if (isAddFooter && footerData != null) {
            addHeaderOrFooter(footerData, itemList.size());
        }

    }

    private void addHeaderOrFooter(DisplayItem headerData, int itemListPosition) {
        itemList.add(itemListPosition, headerData);
        notifyItemInserted(itemListPosition);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    AdapterDelegate<T> adapterDelegate = adapterDelegatesManager.getDelegateForViewType(getItemViewType(position));
                    return adapterDelegate instanceof HeaderDelegate || adapterDelegate instanceof FooterDelegate ? gridLayoutManager.getSpanCount() : 1;
                }
            });

            MyApplication.isStaggeredGridLayoutManager = false;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            MyApplication.isStaggeredGridLayoutManager = true;
        } else {
            MyApplication.isStaggeredGridLayoutManager = false;
        }
    }
}





