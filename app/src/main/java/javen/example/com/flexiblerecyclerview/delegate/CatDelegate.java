package javen.example.com.flexiblerecyclerview.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import javen.example.com.flexiblerecyclerview.R;
import javen.example.com.flexiblerecyclerview.bean.CatBean;
import javen.example.com.flexiblerecyclerview.bean.iinterface.DisplayItem;

/**
 * Created by LuJun on 02/11/2017.
 */

public class CatDelegate extends AdapterDelegate<List<DisplayItem>> {

    private LayoutInflater inflater;

    public CatDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    protected boolean isForViewType(@NonNull List<DisplayItem> items, int position) {
        return items.get(position) instanceof CatBean;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new CatViewHolder(inflater.inflate(R.layout.drawable_card_bule_text_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<DisplayItem> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        CatBean catBean = (CatBean) items.get(position);
        CatViewHolder catViewHolder = (CatViewHolder) holder;

        catViewHolder.imageView.setImageResource(catBean.getImageResourceId());
        catViewHolder.name.setText(catBean.getName());
    }

    static class CatViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView name;

        public CatViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.drawable_card_item_iv);
            name = (TextView) itemView.findViewById(R.id.name_text_view);
        }
    }
}
