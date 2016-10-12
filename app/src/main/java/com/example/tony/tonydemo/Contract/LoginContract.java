package com.example.tony.tonydemo.Contract;

import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.View.IView;

/**
 * Created by lzy on 2016/7/25.
 */
public interface LoginContract {
    //需要view层来实现的登录view接口，IView是所有view的基类
    interface ILoginView extends IView{
        void onShowSuccessLoginView();
        void onShowFailedLoginView(int failed);
        void showLoginingView();
        void dissLoginingView();
    }

    //定义了登录presenter的一些方法，IPresenter是所有Presenter的基类
    interface ILoginPresenter extends IPresenter<ILoginView>{
        void login(String name,String password);
    }


}
