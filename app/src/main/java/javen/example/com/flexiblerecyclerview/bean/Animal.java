package javen.example.com.flexiblerecyclerview.bean;

import javen.example.com.flexiblerecyclerview.bean.iinterface.DisplayItem;

/**
 * Created by LuJun on 02/11/2017.
 */

public class Animal implements DisplayItem {
    private int imageResourceId;
    private String name;

    public Animal(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(int imageResourceId, String name) {
        this.imageResourceId = imageResourceId;
        this.name = name;

    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
