package com.example.tony.tonydemo.NewFramework;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.tony.tonydemo.BaseAdapter;
import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.View.NewsContentActivity;
import com.example.tony.tonydemo.ViewHolder;
import com.example.tony.tonydemo.widget.LoadMoreRecyclerView;
import com.example.tony.tonydemo.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class SubNetNewsFragment extends BaseMvpFragment<NetNewsContract.INetNewsView,NetNewsPresenter> implements NetNewsContract.INetNewsView{
    @Bind(R.id.material_style_ptr_frame)
    PtrFrameLayout mPtrFrame;

    @Bind(R.id.rv_news)
    LoadMoreRecyclerView rvNews;

    private int currentPage = 1;
    private int size = 20;
    private List<NewsEntity> mNewsList;
    private BaseAdapter mAdapter;

    private int index;

    private static final String ARG_PARAM1 = "index";

    private OnFragmentInteractionListener mListener;

    public SubNetNewsFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static SubNetNewsFragment newInstance(int index) {
        SubNetNewsFragment fragment = new SubNetNewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        fragment.setArguments(args);
        return fragment;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    protected NetNewsPresenter initPresenter() {
        return null;
    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_net_sub_news;
    }

    @Override
    protected void initView() {
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
                mPresenter.GetNewsList(index,currentPage,size);
            }
        });


        mPresenter.GetNewsList(index,currentPage,size);
        currentPage++;
    }

    private void initPtr() {
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                mPresenter.GetNewsList(index,currentPage,size);
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
    protected void initData() {

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
}
