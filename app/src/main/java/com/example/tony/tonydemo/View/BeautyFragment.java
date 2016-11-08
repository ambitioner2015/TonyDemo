package com.example.tony.tonydemo.View;

import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tony.tonydemo.BaseAdapter;
import com.example.tony.tonydemo.Contract.BeautyContract;
import com.example.tony.tonydemo.MVPFragment;
import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.Presenter.BeautyPresenter;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.ViewHolder;
import com.example.tony.tonydemo.widget.LoadMoreRecyclerView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.TextHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class BeautyFragment extends MVPFragment implements BeautyContract.IBeautyView {

    @Bind(R.id.rv_news)
    LoadMoreRecyclerView rvNews;
    @Bind(R.id.material_style_ptr_frame)
    PtrClassicFrameLayout mPtrFrame;

    @Bind(R.id.rowllPager)
    RollPagerView mRollPager;
    private BeautyPresenter mBeautyPresenter = new BeautyPresenter();

    private int currentPage = 1;
    private int size = 20;
    private List<NewsEntity> mNewsList;
    private BaseAdapter mAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BeautyFragment() {
        // Required empty public constructor
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_image;
    }


    protected IPresenter[] getPresenters() {
        return new IPresenter[]{ mBeautyPresenter};
    }

    protected void onInitPresenters() {
        mBeautyPresenter.init(this);
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

    }

    @Override
    public void initView() {
        mRollPager.setHintView(new TextHintView(getActivity()));
        mRollPager.setAdapter(new TestNomalAdapter());
        mNewsList = new ArrayList<>();
        mAdapter = new BaseAdapter<NewsEntity>(getActivity(),R.layout.item_fragment_beauty, mNewsList,rvNews) {
            @Override
            public void convert(ViewHolder holder, final NewsEntity newsEntity) {
                holder.setImageWithUrl(R.id.img,newsEntity.getPicUrl());
                final NewsEntity tmp = newsEntity;
//                holder.setOnClickListener(R.id.layout_beauty_item, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getActivity(),NewsContentActivity.class);
//                        intent.putExtra("url", tmp.getUrl());
//                        getActivity().startActivity(intent);
//                    }
//                });
            }
        };

        rvNews.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //横向list
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rvNews.setLayoutManager(linearLayoutManager);
        rvNews.setItemAnimator(new DefaultItemAnimator());
        rvNews.setAdapter(mAdapter);
        initPtr();

        rvNews.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                mBeautyPresenter.GetBeautyList(currentPage,size);
            }
        });


        mBeautyPresenter.GetBeautyList(currentPage,size);
        currentPage++;

    }

    private void initPtr() {
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                mBeautyPresenter.GetBeautyList(currentPage,size);
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

    private class TestNomalAdapter extends StaticPagerAdapter {
        private int[] imgs = {R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
