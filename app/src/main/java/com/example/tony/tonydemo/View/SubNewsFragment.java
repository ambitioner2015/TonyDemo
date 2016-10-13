package com.example.tony.tonydemo.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.tony.tonydemo.BaseAdapter;
import com.example.tony.tonydemo.BaseFragment;
import com.example.tony.tonydemo.Contract.NewsContract;
import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.Presenter.NewsPresenter;
import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.ViewHolder;
import com.example.tony.tonydemo.widget.LoadMoreRecyclerView;
import com.example.tony.tonydemo.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class SubNewsFragment extends BaseFragment implements NewsContract.INewsView{

    @Bind(R.id.rv_news)
    LoadMoreRecyclerView rvNews;
    @Bind(R.id.material_style_ptr_frame)
    PtrClassicFrameLayout mPtrFrame;
    private NewsPresenter mNewsPresenter = new NewsPresenter();
    private int currentPage = 1;
    private int size = 20;
    private List<NewsEntity> mNewsList;
    private BaseAdapter mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INDEX = "index";

    // TODO: Rename and change types of parameters
    private int index;

    public SubNewsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SubNewsFragment newInstance(int index) {
        SubNewsFragment fragment = new SubNewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void showInfo(List<NewsEntity> list) {
        mPtrFrame.refreshComplete();
        if(currentPage==1){
            mNewsList.clear();
        }
        mNewsList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG);
    }

    @Override
    public void initView() {
        mNewsList = new ArrayList<>();
        mAdapter = new BaseAdapter<NewsEntity>(getActivity(),R.layout.item_fragment_news, mNewsList,rvNews) {
            @Override
            public void convert(ViewHolder holder, final NewsEntity newsEntity) {
                holder.setText(R.id.item_title,newsEntity.getTitle());
                holder.setText(R.id.item_time,newsEntity.getCtime());
                holder.setImageWithUrl(R.id.left_image,newsEntity.getPicUrl());
                final NewsEntity tmp = newsEntity;
                holder.setOnClickListener(R.id.title_layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),NewsContentActivity.class);
                        intent.putExtra("url", tmp.getUrl());
                        getActivity().startActivity(intent);
                    }
                });
            }
        };
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),4);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvNews.setLayoutManager(layoutManager);


        //rvNews.addItemDecoration(new RecycleViewDivider(getActivity()));
        rvNews.addItemDecoration(new RecycleViewDivider(getActivity()));
        rvNews.setAdapter(mAdapter);
        initPtr();
        
        rvNews.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {

                currentPage++;
                mNewsPresenter.GetNewsList(index,currentPage,size);
            }
        });


        mNewsPresenter.GetNewsList(index,currentPage,size);
        currentPage++;

    }

    private void initPtr() {
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                mNewsPresenter.GetNewsList(index,currentPage,size);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
//        mPtrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPtrFrame.autoRefresh();
//            }
//        }, 100);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[]{ mNewsPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mNewsPresenter.init(this);
    }
}
