package com.example.tony.tonydemo.View;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.tonydemo.BaseFragment;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NewsFragment extends BaseFragment {

    @Bind(R.id.tab_selector)
    TabLayout mTabSelector;
    @Bind(R.id.veiwpager)
    ViewPager mViewPager;

    fAdpter mAdapter;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initData() {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("国内");
        mTitleList.add("国际");
        mTitleList.add("体育");
        mTitleList.add("健康");

        mFragmentList.add(new DomNewsFragment());
        mFragmentList.add(new INTNewsFragment());
        mFragmentList.add(new SportNewsFragment());
        mFragmentList.add(new FunnyFragment());
    }

    @Override
    protected void initView() {
        mAdapter = new fAdpter(getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mAdapter);
        mTabSelector.setupWithViewPager(mViewPager);
        mTabSelector.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private class fAdpter extends FragmentStatePagerAdapter {
        List<SubNewsFragment> mFlist;
        List<String> mTlist;

        public fAdpter(FragmentManager fm) {
            super(fm);

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
