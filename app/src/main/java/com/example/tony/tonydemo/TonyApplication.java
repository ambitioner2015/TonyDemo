package com.example.tony.tonydemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by lzy on 2016/10/14.
 */
public class TonyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
