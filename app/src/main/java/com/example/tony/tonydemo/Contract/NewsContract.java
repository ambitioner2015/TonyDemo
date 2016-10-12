package com.example.tony.tonydemo.Contract;

import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.View.IView;

import java.util.List;

/**
 * Created by lzy on 2016/8/6.
 */
public interface NewsContract {
    interface INewsView extends IView{
        void showInfo(List<NewsEntity> list);
        void showError(Throwable e);
    }

    interface INewsPresenter extends IPresenter<INewsView>
    {
        void GetNewsList(int type,int page, int num);
    }
}
