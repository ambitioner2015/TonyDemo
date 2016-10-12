package com.example.tony.tonydemo.Presenter;

import com.example.tony.tonydemo.View.IView;

/**
 * Created by lzy on 2016/7/25.
 */
public interface IPresenter<V  extends IView>{
    void onStop();
    void onResume();
    void onDestroy();
    void onPause();
    void onStart();
    void init(V view);
}
