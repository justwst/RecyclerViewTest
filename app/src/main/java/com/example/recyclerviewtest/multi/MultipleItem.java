package com.example.recyclerviewtest.multi;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * author: Administrator
 * date : 2020/12/8
 * desc :
 **/
public class MultipleItem implements MultiItemEntity {
    public static final int ONE = 1;
    public static final int TWO = 2;
    private int itemType;

    public MultipleItem(int itemType){
        this.itemType=itemType;
    }
    @Override
    public int getItemType() {
        return itemType;
    }

}
