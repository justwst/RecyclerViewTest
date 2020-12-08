package com.example.recyclerviewtest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.example.recyclerviewtest.MainActivity;
import com.example.recyclerviewtest.Model;
import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.adapter.MainListAdapter;
import com.example.recyclerviewtest.multi.MultipleItem;
import com.example.recyclerviewtest.multi.MultipleItemQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Administrator
 * date : 2020/12/8
 * desc :
 **/
public class MultiActivity  extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MultipleItemQuickAdapter mMultipleItemQuickAdapter;
    private List<MultipleItem> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_list);
        mList= new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add(new MultipleItem(MultipleItem.ONE));
            mList.add(new MultipleItem(MultipleItem.TWO));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mMultipleItemQuickAdapter = new MultipleItemQuickAdapter(mList);
        mRecyclerView.setAdapter(mMultipleItemQuickAdapter);


    }
}
