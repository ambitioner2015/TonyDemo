package com.example.tony.tonydemo.View;


import android.os.Bundle;
import android.widget.Button;

import com.example.tony.tonydemo.BaseActivity;
import com.example.tony.tonydemo.Contract.LoginContract;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.Presenter.LoginPresenter;
import com.example.tony.tonydemo.R;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginView{
    @Bind(R.id.btnlogin)
    Button btnlogin;

    private LoginPresenter mLoginPresenter = new LoginPresenter();
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[]{ mLoginPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mLoginPresenter.init(this);
    }


    @Override
    public void onShowSuccessLoginView() {

    }

    @Override
    public void onShowFailedLoginView(int failed) {

    }

    @Override
    public void showLoginingView() {

    }

    @Override
    public void dissLoginingView() {

    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.btnlogin)
    void login()
    {
        mLoginPresenter.login("student6","123456");
    }
}
