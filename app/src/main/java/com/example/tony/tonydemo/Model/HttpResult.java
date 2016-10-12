package com.example.tony.tonydemo.Model;

/**
 * Created by lzy on 2016/8/9.
 */
public class HttpResult<T> {
    private int code;
    private T newslist;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getNewslist() {
        return newslist;
    }

    public void setNewslist(T newslist) {
        this.newslist = newslist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
