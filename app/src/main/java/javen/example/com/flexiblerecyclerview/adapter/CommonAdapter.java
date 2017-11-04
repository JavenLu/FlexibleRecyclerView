package javen.example.com.flexiblerecyclerview.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.List;

import javen.example.com.flexiblerecyclerview.MyApplication;
import javen.example.com.flexiblerecyclerview.bean.iinterface.DisplayItem;
import javen.example.com.flexiblerecyclerview.delegate.AdapterDelegate;
import javen.example.com.flexiblerecyclerview.delegate.AdapterDelegatesManager;
import javen.example.com.flexiblerecyclerview.delegate.BeeDelegate;
import javen.example.com.flexiblerecyclerview.delegate.CatDelegate;
import javen.example.com.flexiblerecyclerview.delegate.FooterDelegate;
import javen.example.com.flexiblerecyclerview.delegate.HeaderDelegate;

/**
 * Created by LuJun on 01/11/2017.
 */

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

    public void addHeaderAndFooter(boolean isAddHeader, boolean isAddFooter, DisplayItem headerData, DisplayItem footerData) {

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
