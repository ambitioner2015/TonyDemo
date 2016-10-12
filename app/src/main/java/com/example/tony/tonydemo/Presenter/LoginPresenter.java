package com.example.tony.tonydemo.Presenter;

import android.util.Log;

import com.example.tony.tonydemo.Contract.LoginContract;
import com.example.tony.tonydemo.Model.Entity.LoginEntity;
import com.example.tony.tonydemo.Model.Service.APIService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lzy on 2016/7/25.
 */
public class LoginPresenter implements LoginContract.ILoginPresenter{
    private LoginContract.ILoginView mLoginView;
    String baseUrl = "http://192.168.31.155:2262/webapi/";
    @Override
    public void login(String name, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        APIService login = retrofit.create(APIService.class);
        login.Login("student6","123456").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ddddddddd",e.getMessage());
                    }

                    @Override
                    public void onNext(LoginEntity loginEntity) {
                        Log.e("ddddddddd",loginEntity.getData().getLOGIN_NAME());
                    }
                });
    }

    @Override
    public void init(LoginContract.ILoginView view) {
        mLoginView = view;
        mLoginView.initView();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {
    }
}
