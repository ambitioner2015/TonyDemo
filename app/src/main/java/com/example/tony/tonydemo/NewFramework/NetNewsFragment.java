package com.example.tony.tonydemo.NewFramework;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.View.SubNewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NetNewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NetNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NetNewsFragment extends NBaseFragment {

    @Bind(R.id.tab_selector)
    TabLayout mTabSelector;
    @Bind(R.id.veiwpager)
    ViewPager mViewPager;

    fAdpter mAdapter;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NetNewsFragment() {
        // Required empty public constructor
    }

    public static NetNewsFragment newInstance(String param1, String param2) {
        NetNewsFragment fragment = new NetNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
    protected int initLayoutId() {
        return R.layout.fragment_net_news;
    }

    @Override
    protected void initView() {
        mAdapter = new fAdpter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabSelector.setupWithViewPager(mViewPager);
        mTabSelector.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void initData() {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("头条");
        mTitleList.add("娱乐");
        mTitleList.add("体育");
        mTitleList.add("健康");

        for(int i=0;i<mTitleList.size();i++)
        {
            mFragmentList.add(SubNetNewsFragment.newInstance(i));
        }
    }

    private class fAdpter extends FragmentStatePagerAdapter {
        List<SubNewsFragment> mFlist;
        List<String> mTlist;

        public fAdpter(FragmentManager fm) {
            super(fm);
//            mFlist = flist;
//            mTlist = titlelist;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
