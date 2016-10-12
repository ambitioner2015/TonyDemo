package com.example.tony.tonydemo.Model;

import android.util.Log;

import rx.functions.Func1;

/**
 * Created by lzy on 2016/8/9.
 */
public class HttpResultFunc <T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> tHttpResult) {
        if (tHttpResult.getCode() != 200) {
            throw new ApiException(tHttpResult.getCode());
        }
        Log.e("aaa",tHttpResult.getNewslist().toString());
        return tHttpResult.getNewslist();
    }
}
