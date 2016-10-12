package com.example.tony.tonydemo.Presenter;

import android.util.Log;

import com.example.tony.tonydemo.Constant;
import com.example.tony.tonydemo.Contract.BeautyContract;
import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.Model.HttpResultFunc;
import com.example.tony.tonydemo.Model.Service.APIService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lzy on 2016/10/10.
 */
public class BeautyPresenter implements BeautyContract.IBeautyPresenter{
    BeautyContract.IBeautyView mView;
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

    @Override
    public void init(BeautyContract.IBeautyView view) {
        mView = view;
        mView.initView();
    }

    @Override
    public void GetBeautyList(int page, int num) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE_NEWS_WEBAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

            retrofit.create(APIService.class).GetBeautyImage(Constant.TX_API_KEY,page, num)
                    .map(new HttpResultFunc<List<NewsEntity>>())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<NewsEntity>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("imageonError",e.getMessage());
                        }

                        @Override
                        public void onNext(List<NewsEntity> list) {
                            Log.e("imagesuccess",(list.get(0).getTitle()));
                            mView.showInfo(list);
                        }
                    });

    }
}
