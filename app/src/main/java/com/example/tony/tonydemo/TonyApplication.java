package com.example.tony.tonydemo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * Created by lzy on 2016/10/14.
 */
public class TonyApplication extends Application {
    private static Context mContext;
    //private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        mContext = getContext();
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        TonyApplication application = (TonyApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

    public static Context getContext() {
        return mContext;
    }
}
