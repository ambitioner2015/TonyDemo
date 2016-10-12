package com.example.tony.tonydemo.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tony.tonydemo.BaseAdapter;
import com.example.tony.tonydemo.BaseFragment;
import com.example.tony.tonydemo.Contract.BeautyContract;
import com.example.tony.tonydemo.Model.Entity.NewsEntity;
import com.example.tony.tonydemo.Presenter.BeautyPresenter;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.ViewHolder;
import com.example.tony.tonydemo.widget.LoadMoreRecyclerView;
import com.example.tony.tonydemo.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class BeautyFragment extends BaseFragment implements BeautyContract.IBeautyView {

    @Bind(R.id.rv_news)
    LoadMoreRecyclerView rvNews;
    @Bind(R.id.material_style_ptr_frame)
    PtrClassicFrameLayout mPtrFrame;
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BeautyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeautyFragment newInstance(String param1, String param2) {
        BeautyFragment fragment = new BeautyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(),"wozaizheli",Toast.LENGTH_LONG);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_image;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[]{ mBeautyPresenter};
    }

    @Override
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
        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG);
    }

    @Override
    public void initView() {
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
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvNews.setLayoutManager(layoutManager);
        //rvNews.addItemDecoration(new RecycleViewDivider(getActivity()));
        //rvNews.addItemDecoration(new RecycleViewDivider(getActivity()));
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
}
