package com.example.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.example.recyclerviewtest.adapter.MainListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MainListAdapter mMainListAdapter;
    private List<Model> mModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_list);
        mModelList= new ArrayList<>();
        Model model;
        for (int i = 0; i <20 ; i++) {
            model = new Model();
            model.setTitle("第" + i + "条标题");
            model.setContent("第" + i + "条内容");
            mModelList.add(model);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mMainListAdapter = new MainListAdapter(R.layout.item_main_list,mModelList);
        mRecyclerView.setAdapter(mMainListAdapter);
        mMainListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
            }
        });
        mMainListAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Toast.makeText(MainActivity.this, "长按了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mMainListAdapter.addChildClickViewIds(R.id.tv_title,R.id.tv_content,R.id.iv_img);
        mMainListAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                switch (view.getId()){
                    case R.id.tv_title:
                        Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的标题", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_content:
                        Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的内容", Toast.LENGTH_SHORT).show();
                        break;
                        case R.id.iv_img:
                        Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的图片", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

    }
}