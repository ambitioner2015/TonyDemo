package com.example.tony.tonydemo.Contract;

import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.View.IView;

import java.util.List;

/**
 * Created by lzy on 2016/10/10.
 */
public interface BeautyContract {

    interface IBeautyView extends IView{
        void showInfo(List<NewsEntity> list);
        void showError(Throwable e);
    }

    interface IBeautyPresenter extends IPresenter<IBeautyView>
    {
        void GetBeautyList(int page, int num);
    }
}
