package com.example.tony.tonydemo.NewFramework;

import com.example.tony.tonydemo.Model.Entity.NewsEntity;

import java.util.List;

/**
 * Created by lzy on 2016/10/17.
 */
public interface NetNewsContract {
    interface INetNewsView{
        void showInfo(List<NewsEntity> list);
        void showError(Throwable e);
    }

    interface INetNewsPresenter{
        void GetNewsList(int type, int page, int num);
    }
}
