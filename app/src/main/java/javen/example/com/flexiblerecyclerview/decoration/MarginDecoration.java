package javen.example.com.flexiblerecyclerview.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置RecyclerView Margin
 */
public class MarginDecoration extends RecyclerView.ItemDecoration {
    private int margin;
    private int leftMargin, topMargin, rightMargin, bottomMargin;
    private boolean isSameMargin = true;

    public MarginDecoration(Context context, int allSameMarginValue) {
        margin = context.getResources().getDimensionPixelSize(allSameMarginValue);
        isSameMargin = true;
    }

    public MarginDecoration(Context context, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
        this.rightMargin = rightMargin;
        this.bottomMargin = bottomMargin;
        isSameMargin = false;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (isSameMargin) {
            outRect.set(margin, margin, margin, margin);
        } else {
            outRect.set(leftMargin, topMargin, rightMargin, bottomMargin);
        }
    }
}