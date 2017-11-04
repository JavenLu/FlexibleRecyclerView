package javen.example.com.flexiblerecyclerview.bean;

import javen.example.com.flexiblerecyclerview.bean.iinterface.DisplayItem;

/**
 * Created by LuJun on 03/11/2017.
 */

public class FooterBean implements DisplayItem {
    private int imageResourceId;

    public FooterBean(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
