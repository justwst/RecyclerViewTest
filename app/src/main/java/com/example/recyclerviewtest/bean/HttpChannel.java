package com.example.recyclerviewtest.bean;

import androidx.annotation.NonNull;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: Administrator
 * date : 2020/12/8
 * desc :
 **/
public class HttpChannel {

    private static HttpChannel httpChannel;
    private RetrofitService retrofitService;

    public static HttpChannel getInstance() {
        return httpChannel == null ? httpChannel = new HttpChannel() : httpChannel;
    }

    private HttpChannel() {
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create()) // json解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .client(RetrofitUtils.getOkHttpClient()) // 打印请求参数
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    /**
     * 发送消息
     *  @param observable Observable<? extends BaseBean>
     * @param urlOrigin  请求地址
     */
    public void sendMessage(Observable<PostInfo> observable, final String urlOrigin) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseBean baseBean) {
                        Log.i("http返回：", baseBean.toString() + "");
                        ReceiveMessageManager.getInstance().dispatchMessage(baseBean, urlOrigin);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public RetrofitService getRetrofitService() {
        return retrofitService;
    }
}
