package com.example.tony.tonydemo.NewFramework;

import android.os.Bundle;

/**
 * Author: Othershe
 * Time: 2016/8/12 12:19
 */
public abstract class BaseMvpFragment<V, P extends BasePresenter<V>> extends NBaseFragment {
    protected static final String SUB_TYPE = "subtype";

    protected P mPresenter;

    protected abstract P initPresenter();

    protected abstract void fetchData();

    protected boolean mIsViewInitiated;
    protected boolean mIsVisibleToUser;
    protected boolean mIsDataInitiated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        initFetchData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);

        mIsViewInitiated = true;
        initFetchData();
    }

    protected void initFetchData() {
        if (mIsVisibleToUser && mIsViewInitiated && !mIsDataInitiated) {
            fetchData();
            mIsDataInitiated = true;
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
