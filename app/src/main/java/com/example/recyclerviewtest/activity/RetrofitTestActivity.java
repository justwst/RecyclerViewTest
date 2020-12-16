package com.example.recyclerviewtest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.adapter.MainListAdapter;
import com.example.recyclerviewtest.bean.Constant;
import com.example.recyclerviewtest.bean.GankEntity;
import com.example.recyclerviewtest.bean.GirlsBean;
import com.example.recyclerviewtest.bean.PostInfo;
import com.example.recyclerviewtest.bean.RetrofitService;
import com.example.recyclerviewtest.bean.RetrofitUtils;
import com.example.recyclerviewtest.bean.SendMessageManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Administrator
 * date : 2020/12/8
 * desc :
 **/
public class RetrofitTestActivity extends AppCompatActivity {

    private SendMessageManager sendMessageManager;
    private List<GankEntity> mGankEntityList;
    private RecyclerView mRecyclerView;
    private MainListAdapter mMainListAdapter;
    private SmartRefreshLayout mSmartRefreshLayout;

    private int pageSize = 10;
    private int pageIndex = 1;

    private Retrofit mRetrofit;
    private RetrofitService mRetrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
        sendMessageManager = SendMessageManager.getInstance();
        mRecyclerView = findViewById(R.id.recycler_list);
        mSmartRefreshLayout= findViewById(R.id.refreshLayout);
        mGankEntityList = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initRetrofit();
        baseRequest();
        mSmartRefreshLayout.setEnableRefresh(true);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Call<GirlsBean> call = mRetrofitService.getCagetorys(Constant.FlagGirls, Constant.FlagGirls, 1, pageSize);
                call.enqueue(new Callback<GirlsBean>() {
                    @Override
                    public void onResponse(Call<GirlsBean> call, Response<GirlsBean> response) {
                        Log.i("http返回：", response.body().getData().toString());
                        Toast.makeText(RetrofitTestActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        mMainListAdapter.refreshData(response.body().getData());
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onFailure(Call<GirlsBean> call, Throwable t) {
                        Log.i("http返回：", "onFailure");
                        refreshLayout.finishRefresh();
                    }
                });
                pageIndex = 1;
                pageIndex++;
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Call<GirlsBean> call = mRetrofitService.getCagetorys(Constant.FlagGirls, Constant.FlagGirls, pageIndex, pageSize);
                call.enqueue(new Callback<GirlsBean>() {
                    @Override
                    public void onResponse(Call<GirlsBean> call, Response<GirlsBean> response) {
                        Log.i("http返回：", response.body().getData().toString());
                        Toast.makeText(RetrofitTestActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                        mMainListAdapter.loadMore(response.body().getData());
                        pageIndex++;
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onFailure(Call<GirlsBean> call, Throwable t) {
                        Log.i("http返回：", "onFailure");
                        refreshLayout.finishLoadMore();
                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }




    /*@OnClick({R.id.btn_base_request, R.id.btn_rx_request, R.id.btn_encap_request})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_base_request:
                // 基本使用
                baseRequest();
                break;

            case R.id.btn_rx_request:
                // Rx方式使用
                rxRequest();
                break;

            case R.id.btn_encap_request:
                // 封装使用
                encapRequest();
                break;

            default:
                break;
        }
    }*/
    private void initRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.GANK2_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitUtils.getOkHttpClient()) // 打印请求参数
                .build();

        mRetrofitService = mRetrofit.create(RetrofitService.class);
    }

    /**
     * 基本使用
     */
    private void baseRequest() {

        Call<GirlsBean> call = mRetrofitService.getCagetorys(Constant.FlagGirls, Constant.FlagGirls, 1, 20);
        call.enqueue(new Callback<GirlsBean>() {
            @Override
            public void onResponse(Call<GirlsBean> call, Response<GirlsBean> response) {
                Log.i("http返回：", response.body().getData().toString());
                Toast.makeText(RetrofitTestActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                mGankEntityList = response.body().getData();
                mMainListAdapter = new MainListAdapter(RetrofitTestActivity.this ,R.layout.item_retrofit_list, mGankEntityList);
                mRecyclerView.setAdapter(mMainListAdapter);
                mMainListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<GirlsBean> call, Throwable t) {
                Log.i("http返回：", "onFailure");
            }
        });
    }

    /**
     * Rx方式使用
     */
    private void rxRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .client(RetrofitUtils.getOkHttpClient()) // 打印请求参数
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        Observable<PostInfo> observable = service.getPostInfoRx("yuantong", "11111111111");
        observable.subscribeOn(Schedulers.io()) // 在子线程中进行Http访问
                .observeOn(AndroidSchedulers.mainThread()) // UI线程处理返回接口
                .subscribe(new Observer<PostInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("rxRequest：", "onSubscribe");
                    }

                    @Override
                    public void onNext(PostInfo value) {
                        Log.i("rxRequest：", "onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("rxRequest：", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("rxRequest：", "onComplete");
                    }
                });
    }

    /**
     * 封装使用
     */
    private void encapRequest() {
        sendMessageManager.getPostInfo("yuantong", "11111111111");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PostInfo postInfo) {
        Log.i("接收消息：", postInfo.toString());
        Toast.makeText(RetrofitTestActivity.this, postInfo.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
