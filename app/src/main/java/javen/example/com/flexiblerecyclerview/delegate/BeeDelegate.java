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
import javen.example.com.flexiblerecyclerview.bean.BeeBean;
import javen.example.com.flexiblerecyclerview.bean.iinterface.DisplayItem;


/**
 * Created by LuJun on 02/11/2017.
 */

public class BeeDelegate extends AdapterDelegate<List<DisplayItem>> {
    private LayoutInflater inflater;

    public BeeDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<DisplayItem> items, int position) {
        return items.get(position) instanceof BeeBean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new BeeViewHolder(inflater.inflate(R.layout.drawable_card_bule_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<DisplayItem> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        BeeBean beeBean = (BeeBean) items.get(position);
        BeeViewHolder beeViewHolder = (BeeViewHolder) holder;
        beeViewHolder.imageView.setImageResource(beeBean.getImageResourceId());
    }

    static class BeeViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public BeeViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.drawable_card_item_iv);
        }
    }
}
