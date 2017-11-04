package javen.example.com.flexiblerecyclerview.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import javen.example.com.flexiblerecyclerview.R;
import javen.example.com.flexiblerecyclerview.bean.HeaderBean;
import javen.example.com.flexiblerecyclerview.bean.iinterface.DisplayItem;
import javen.example.com.flexiblerecyclerview.helper.LayoutManagerHelper;

/**
 * Created by LuJun on 03/11/2017.
 */

public class HeaderDelegate extends AdapterDelegate<List<DisplayItem>> {

    private LayoutInflater inflater;

    public HeaderDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<DisplayItem> items, int position) {
        return items.get(position) instanceof HeaderBean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new HeaderDelegateViewHolder(LayoutManagerHelper.handleStaggeredGridNoFullSpan(inflater, parent, R.layout.header_layout));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<DisplayItem> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        HeaderBean headerBean = (HeaderBean) items.get(position);
        HeaderDelegateViewHolder headerDelegateViewHolder = (HeaderDelegateViewHolder) holder;
        headerDelegateViewHolder.imageView.setImageResource(headerBean.getImageResourceId());
    }


    static class HeaderDelegateViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public HeaderDelegateViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.header_image_view);
        }
    }

}
