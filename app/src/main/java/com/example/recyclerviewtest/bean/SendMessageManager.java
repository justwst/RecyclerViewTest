package com.example.recyclerviewtest.bean;


import io.reactivex.Observable;

/**
 * author: Administrator
 * date : 2020/12/8
 * desc :消息发送管理
 **/

public class SendMessageManager {

    private static SendMessageManager manager;
    private HttpChannel httpChannel;
    private RetrofitService retrofitService;

    public static SendMessageManager getInstance() {
        return manager == null ? manager = new SendMessageManager() : manager;
    }

    private SendMessageManager() {
        httpChannel = HttpChannel.getInstance();
        retrofitService = httpChannel.getRetrofitService();
    }

    /**
     * 获取快递信息
     *
     * @param type   快递类型
         * @param postid 快递单号
     */
    public void getPostInfo(String type, String postid) {
        Observable<PostInfo> observable = retrofitService.getPostInfoRx(type, postid);
        httpChannel.sendMessage(observable, Constant.UrlOrigin.get_post_info);
    }
}
