package com.example.tony.tonydemo.View;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.tonydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NewsFragment extends Fragment {

    @Bind(R.id.tab_selector)
    TabLayout mTabSelector;
    @Bind(R.id.veiwpager)
    ViewPager mViewPager;

    fAdpter mAdapter;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void initData() {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("国内");
        mTitleList.add("国际");
        mTitleList.add("体育");
        mTitleList.add("健康");

        for(int i=0;i<mTitleList.size();i++)
        {
            mFragmentList.add(SubNewsFragment.newInstance(i));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this,view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new fAdpter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabSelector.setupWithViewPager(mViewPager);
        mTabSelector.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }



    @Override
    public void onDetach() {
        super.onDetach();
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
