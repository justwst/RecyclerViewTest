package com.example.recyclerviewtest.bean;

/**
 * author: Administrator
 * date : 2020/12/9
 * desc :
 **/
public class GankBaseBean {
    /**
     * page : 1
     * page_count : 10
     * status : 100
     * total_counts : 96
     */
    private int page;
    private int page_count;
    private int status;
    private int total_counts;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal_counts() {
        return total_counts;
    }

    public void setTotal_counts(int total_counts) {
        this.total_counts = total_counts;
    }
}