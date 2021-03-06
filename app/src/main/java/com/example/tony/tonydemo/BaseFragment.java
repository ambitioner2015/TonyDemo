package com.example.tony.tonydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.tonydemo.Presenter.IPresenter;

import java.util.HashSet;
import java.util.Set;

import butterknife.ButterKnife;

/**
 * Created by lzy on 2016/8/8.
 */
public abstract class BaseFragment extends Fragment {

    /** * 获取layout的id，具体由子类实现
     * @return
     */
    protected abstract int getLayoutResId();

    /** * 从intent中解析数据，具体子类来实现
     * @param argIntent
     */
    protected void parseArgumentsFromIntent(Intent argIntent){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this,view);
        initData();
        initView();
        return view;
    }

    protected abstract void initData();
    protected abstract void initView();
}
