package com.example.tony.tonydemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.tonydemo.BaseFragment;
import com.example.tony.tonydemo.Presenter.IPresenter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import butterknife.ButterKnife;

/**
 * Created by lzy on 2016/11/8.
 */

public abstract class MVPFragment extends Fragment {
    protected boolean isVisible;
    protected boolean isLoadedData = false;
    protected boolean isCreatedView =  false;
    protected View mView;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        mView = view;
        ButterKnife.bind(this,view);
        isCreatedView = true;
        addPresenters();
        onInitPresenters();
        return view;
    }

    @Override
    public void onDestroyView() {
        if(mView!=null){
            ((ViewGroup)mView.getParent()).removeView(mView);
        }

        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()&&isCreatedView == true) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        //如果已经加载过的数据就不再加载了
        if(!isLoadedData) {
            Log.e("222","load....");
            lazyLoad();
            isLoadedData = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint())
            lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible(){}
}
