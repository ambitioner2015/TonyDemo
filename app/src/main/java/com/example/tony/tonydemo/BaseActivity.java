package com.example.tony.tonydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.tony.tonydemo.Presenter.IPresenter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import butterknife.ButterKnife;

/**
 * Created by lzy on 2016/7/25.
 */
public abstract class BaseActivity extends FragmentActivity{
    private Set<IPresenter> mAllPresenters = new HashSet<IPresenter>(1);

    /** * 获取layout的id，具体由子类实现
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     *需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter
     */
    protected abstract IPresenter[] getPresenters();

    //初始化presenters，
    protected abstract void onInitPresenters();

    /** * 从intent中解析数据，具体子类来实现
     * @param argIntent
     */
    protected void parseArgumentsFromIntent(Intent argIntent){
    }

    private void addPresenters(){
        IPresenter[] presenters = getPresenters();
        if(presenters != null){
            Collections.addAll(mAllPresenters, presenters);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        if(getIntent() != null){
            parseArgumentsFromIntent(getIntent());
        }
        addPresenters();

        onInitPresenters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //依次调用IPresenter的onResume方法
        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onResume();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //依次调用IPresenter的onStart方法
        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onStart();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //依次调用IPresenter的onStop方法
        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onStop();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //依次调用IPresenter的onPause方法
        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onPause();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //依次调用IPresenter的onDestroy方法
        for (IPresenter presenter:mAllPresenters  ) {
            if(presenter != null){
                presenter.onDestroy();
            }
        }
    }
}
