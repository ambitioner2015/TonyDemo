package com.example.tony.tonydemo.Presenter;

import android.util.Log;

import com.example.tony.tonydemo.Constant;
import com.example.tony.tonydemo.Contract.NewsContract;
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
 * Created by lzy on 2016/8/6.
 */
public class NewsPresenter implements NewsContract.INewsPresenter{
    NewsContract.INewsView mNewsView;
    @Override
    public void GetNewsList(int type, int page, int num) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE_NEWS_WEBAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        if(type == 0)
        {
            retrofit.create(APIService.class).GetSocialNews(Constant.TX_API_KEY,page, num)
                    .map(new HttpResultFunc<List<NewsEntity>>())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<NewsEntity>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("llonError",e.getMessage());
                        }

                        @Override
                        public void onNext(List<NewsEntity> list) {
                            Log.e("llsuccess",(list.get(0).getTitle()));
                            mNewsView.showInfo(list);
                        }
                    });
        }
        else if(type==1)
        {
            retrofit.create(APIService.class).GetFunnyNews(Constant.TX_API_KEY,page, num)
                    .map(new HttpResultFunc<List<NewsEntity>>())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<NewsEntity>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("llonError",e.getMessage());
                        }

                        @Override
                        public void onNext(List<NewsEntity> list) {
                            Log.e("llsuccess",(list.get(0).getTitle()));
                            mNewsView.showInfo(list);
                        }
                    });
        }
        else if(type==2)
        {
            retrofit.create(APIService.class).GetSportNews(Constant.TX_API_KEY,page, num)
                    .map(new HttpResultFunc<List<NewsEntity>>())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<NewsEntity>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("llonError",e.getMessage());
                        }

                        @Override
                        public void onNext(List<NewsEntity> list) {
                            Log.e("llsuccess",(list.get(0).getTitle()));
                            mNewsView.showInfo(list);
                        }
                    });
        }
        else
        {
            retrofit.create(APIService.class).GetHealthNews(Constant.TX_API_KEY,page, num)
                    .map(new HttpResultFunc<List<NewsEntity>>())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<NewsEntity>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("llonError",e.getMessage());
                        }

                        @Override
                        public void onNext(List<NewsEntity> list) {
                            Log.e("llsuccess",(list.get(0).getTitle()));
                            mNewsView.showInfo(list);
                        }
                    });
        }


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

    @Override
    public void init(NewsContract.INewsView view) {
        mNewsView = view;
        mNewsView.initView();
    }
}
