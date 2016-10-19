package com.example.tony.tonydemo.NewFramework;

import android.os.Bundle;
import android.support.annotation.Nullable;



/**
 * Author: Othershe
 * Time:  2016/8/11 11:14
 */
public abstract class BaseMvpActivity<V, P extends BasePresenter<V>> extends NBaseActivity {
    protected P mPresenter;

    protected abstract P initPresenter();

    protected abstract void fetchData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);

        fetchData();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
