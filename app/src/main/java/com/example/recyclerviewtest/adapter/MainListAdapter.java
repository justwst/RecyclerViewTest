package com.example.recyclerviewtest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.collection.ArrayMap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewtest.Model;
import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.bean.GankEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Administrator
 * date : 2020/12/7
 * desc :
 **/
public class MainListAdapter extends BaseQuickAdapter<GankEntity, BaseViewHolder> {
    private List<GankEntity> mDataList;
    private Context mContext;
    private RequestOptions options;
    private ArrayMap<String, PicSizeEntity> picSizeEntityArrayMap = new ArrayMap<>();
    private int screenWidth;

    public MainListAdapter(Context context, int layoutResId, @Nullable List<GankEntity> data) {
        super(layoutResId, data);
        mDataList = data;
        mContext = context;
        options = new RequestOptions();
        options.fitCenter();
        options.placeholder(R.mipmap.pic_gray_bg);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        screenWidth = DensityUtil.getWidth(context);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GankEntity gankEntity) {
        Log.d("WYJ", "convert 1 : " + mDataList.get(baseViewHolder.getAdapterPosition()).getUrl());
        ImageView imageView=baseViewHolder.getView(R.id.iv_img_mm);
        TextView mTextView=baseViewHolder.getView(R.id.tv_content);
        mTextView.setText(mDataList.get(baseViewHolder.getAdapterPosition()).getDesc());
        String url = mDataList.get(baseViewHolder.getAdapterPosition()).getUrl();
        Glide.with(mContext).load(url).placeholder(R.mipmap.pic_gray_bg).into(imageView);
        /*PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(mDataList.get(baseViewHolder.getAdapterPosition()).getUrl());
        if (picSizeEntity != null && !picSizeEntity.isNull()) {
            int width = picSizeEntity.getPicWidth();
            int height = picSizeEntity.getPicHeight();
            //计算高宽比
            int finalHeight = (screenWidth / 2) * height / width;
            ViewGroup.LayoutParams layoutParams = baseViewHolder.itemView.getLayoutParams();
            layoutParams.height = finalHeight;
        }
        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .apply(options)
                .thumbnail(0.2f)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@androidx.annotation.Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        PicSizeEntity picSizeEntity = picSizeEntityArrayMap.get(mDataList.get(baseViewHolder.getAdapterPosition()).getUrl());
                        if (picSizeEntity == null || picSizeEntity.isNull()) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            //计算高宽比
                            int finalHeight = (screenWidth / 2) * height / width;
                            ViewGroup.LayoutParams layoutParams = baseViewHolder.itemView.getLayoutParams();
                            layoutParams.height = finalHeight;
                            picSizeEntityArrayMap.put(mDataList.get(baseViewHolder.getAdapterPosition()).getUrl(), new PicSizeEntity(width, height));
                        }
                        return false;
                    }
                })
                .into(imageView);*/

    }


    public void loadMore(List<GankEntity> list) {
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    //底部下拉刷新，数据直接从上往下添加数据，显示在顶部
    public void refreshData(List<GankEntity> list) {
        mDataList.addAll(0, list);
        notifyDataSetChanged();
//            notifyItemInserted(0); 一次只能加一项数据
    }


}
