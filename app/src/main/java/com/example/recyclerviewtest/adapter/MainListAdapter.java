package com.example.recyclerviewtest.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewtest.Model;
import com.example.recyclerviewtest.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: Administrator
 * date : 2020/12/7
 * desc :
 **/
public class MainListAdapter extends BaseQuickAdapter<Model, BaseViewHolder> {
    public MainListAdapter(int layoutResId, @Nullable List<Model> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Model model) {
        baseViewHolder.setText(R.id.tv_title, model.getTitle())
                .setText(R.id.tv_content, model.getContent())
                .setImageResource(R.id.iv_img, R.mipmap.ic_launcher);

    }
}
