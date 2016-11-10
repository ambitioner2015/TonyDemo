package com.example.tony.tonydemo.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tony.tonydemo.Contract.NewsContract;
import com.example.tony.tonydemo.MVPFragment;
import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.Presenter.NewsPresenter;
import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.widget.LoadMoreListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class INTNewsFragment extends MVPFragment implements NewsContract.INewsView{

    @Bind(R.id.rv_news)
    LoadMoreListView mlistNews;

    @Bind(R.id.swip)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String TAG = "INTNewsFragment";
    private NewsPresenter mNewsPresenter = new NewsPresenter();
    private int currentPage = 1;
    private int size = 20;
    private List<NewsEntity> mNewsList;
    private INTNewsAdapter mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INDEX = "index";

    // TODO: Rename and change types of parameters
    private int index;

    public INTNewsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static INTNewsFragment newInstance(int index) {
        INTNewsFragment fragment = new INTNewsFragment();
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
        Log.e(TAG,"onDetach");
    }


    @Override
    public void showInfo(List<NewsEntity> list) {
        Log.e(TAG,"showInfo");
        mSwipeRefreshLayout.setRefreshing(false);
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
        mNewsList = new ArrayList<>();
        mAdapter = new INTNewsAdapter(getContext(),mNewsList);
        mlistNews.setAdapter(mAdapter);
//        mlistNews.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });
        mlistNews.setOnLoadMoreListener(new LoadMoreListView.onLoadMore() {
            @Override
            public void onLoad() {
                Log.e(TAG,"onLoad");
                currentPage ++;
                mNewsPresenter.GetNewsList(index,currentPage,size);
            }
        });
       initSwip();

 }

    private void initSwip() {
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mSwipeRefreshLayout.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // 通过 setEnabled(false) 禁用下拉刷新
        //mSwipeRefreshLayout.setEnabled(false);

        // 设定下拉圆圈的背景
        mSwipeRefreshLayout.setProgressBackgroundColor(R.color.white);
          /*
         * 设置手势下拉刷新的监听
         */
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // 刷新动画开始后回调到此方法
                        currentPage = 1;
                        mNewsPresenter.GetNewsList(index,currentPage,size);
                    }
                }
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_int_news;
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
    protected void lazyLoad() {
        mNewsPresenter.GetNewsList(index,currentPage,size);
        currentPage++;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestory");
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Log.e(TAG,"onDestroyView");
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"onActivityCreated");
    }

    class INTNewsAdapter extends BaseAdapter{
        private List<NewsEntity> mData;
        private LayoutInflater mInflater;
        private Context mContext;

        public INTNewsAdapter(Context context,List<NewsEntity> data) {
            mData = data;
            mInflater = LayoutInflater.from(context);
            mContext = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView==null)
            {
                convertView = mInflater.inflate(R.layout.item_fragment_news,null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.left_image);
                holder.time = (TextView) convertView.findViewById(R.id.item_time);
                holder.title = (TextView) convertView.findViewById(R.id.item_title);
                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.time.setText(mData.get(position).getCtime());
            holder.title.setText(mData.get(position).getTitle());
            String url = mData.get(position).getPicUrl();
            if(url!="") {
                Picasso.with(mContext).load(url)
                        .placeholder(R.mipmap.pic_loading)
                        .error(R.mipmap.pic_loading)
                        .into(holder.img);
            }
            return convertView;
        }

        public class ViewHolder
        {
            public ImageView img;
            public TextView title;
            public TextView time;
        }
    }
}
