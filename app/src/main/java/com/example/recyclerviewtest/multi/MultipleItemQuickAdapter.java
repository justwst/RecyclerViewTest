package com.example.recyclerviewtest.multi;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewtest.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * author: Administrator
 * date : 2020/12/8
 * desc :
 **/
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    public MultipleItemQuickAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ONE, R.layout.view_one);
        addItemType(MultipleItem.TWO, R.layout.view_two);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MultipleItem multipleItem) {
        switch (baseViewHolder.getItemViewType()) {
            case MultipleItem.ONE:

                break;
            case MultipleItem.TWO:
                //...
                break;
        }
    }
}
