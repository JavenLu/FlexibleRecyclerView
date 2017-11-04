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
import javen.example.com.flexiblerecyclerview.bean.FooterBean;
import javen.example.com.flexiblerecyclerview.bean.iinterface.DisplayItem;
import javen.example.com.flexiblerecyclerview.helper.LayoutManagerHelper;

/**
 * Created by LuJun on 03/11/2017.
 */

public class FooterDelegate extends AdapterDelegate<List<DisplayItem>> {
    private LayoutInflater inflater;

    public FooterDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<DisplayItem> items, int position) {
        return items.get(position) instanceof FooterBean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new FooterDelegateViewHolder(LayoutManagerHelper.handleStaggeredGridNoFullSpan(inflater, parent, R.layout.footer_layout));
    }


    @Override
    protected void onBindViewHolder(@NonNull List<DisplayItem> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        FooterBean footerBean = (FooterBean) items.get(position);
        FooterDelegateViewHolder footerDelegateViewHolder = (FooterDelegateViewHolder) holder;
        footerDelegateViewHolder.imageView.setImageResource(footerBean.getImageResourceId());
    }

    static class FooterDelegateViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public FooterDelegateViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.footer_image_view);
        }
    }

}
