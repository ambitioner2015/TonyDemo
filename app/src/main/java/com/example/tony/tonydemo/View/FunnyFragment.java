package com.example.tony.tonydemo.View;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.tony.tonydemo.BaseAdapter;
import com.example.tony.tonydemo.Contract.NewsContract;
import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.Presenter.NewsPresenter;
import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.ViewHolder;
import com.example.tony.tonydemo.MVPFragment;
import com.example.tony.tonydemo.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lzy on 2016/8/10.
 */
public class FunnyFragment extends MVPFragment implements NewsContract.INewsView{

    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipLayout;

    @Bind(R.id.rv_newslist)
    RecyclerView mRecylerView;

    LinearLayoutManager mLayoutManager;
    private BaseAdapter mAdapter;
    int lastVisibleItem;
    private List<NewsEntity> mNewsList;
    private int currentPage;
    private int size=10;
    private NewsPresenter mNewsPresenter = new NewsPresenter();

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_funny;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[]{ mNewsPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mNewsPresenter.init(this);
    }

    @Override
    public void showInfo(List<NewsEntity> list) {
        if(currentPage==1){
            mNewsList.clear();
        }
        mNewsList.addAll(list);
        mAdapter.notifyDataSetChanged();
        mSwipLayout.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG);
    }

    @Override
    public void initView() {
        mNewsList = new ArrayList<>();
        mSwipLayout.setColorSchemeColors(R.color.blue);
        mSwipLayout.setDistanceToTriggerSync(100);
        mSwipLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage++;
                mNewsPresenter.GetNewsList(0,currentPage,size);
            }
        });

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipLayout.setProgressViewOffset(false, 0, (int) TypedValue
          .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
            .getDisplayMetrics()));
        mRecylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                      && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    mSwipLayout.setRefreshing(true);
                       // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                        currentPage++;
                        mNewsPresenter.GetNewsList(0,currentPage,size);
                      }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
        mRecylerView.setHasFixedSize(true);
        mRecylerView.addItemDecoration(new RecycleViewDivider(getActivity()));
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new BaseAdapter<NewsEntity>(getActivity(),R.layout.item_fragment_news, mNewsList,mRecylerView) {
            @Override
            public void convert(ViewHolder holder, final NewsEntity newsEntity) {
                holder.setText(R.id.item_title,newsEntity.getTitle());
                holder.setText(R.id.item_time,newsEntity.getCtime());
                holder.setImageWithUrl(R.id.left_image,newsEntity.getPicUrl());
                holder.setOnClickListener(R.id.title_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(getActivity(),WebDetailActivity.class);
//                        intent.putExtra("url", newslistEntity.getUrl());
//                        getActivity().startActivity(intent);
                        Toast.makeText(getActivity(),newsEntity.getTitle(),Toast.LENGTH_LONG);
                    }
                });
            }
        };
        mRecylerView.setAdapter(mAdapter);

        // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
        mNewsPresenter.GetNewsList(0,currentPage,size);
        currentPage++;
    }
}
