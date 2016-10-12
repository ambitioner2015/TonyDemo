package com.example.tony.tonydemo.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

import com.example.tony.tonydemo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind({R.id.tab_news, R.id.tab_pictures, R.id.tab_found, R.id.tab_about})
    List<RadioButton> mTabs;

    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        setSelect(0);
    }

    @OnClick({R.id.tab_news, R.id.tab_pictures, R.id.tab_found, R.id.tab_about})
    void bottomClick(View view) {
        resetImgs();
        switch (view.getId()) {
            case R.id.tab_news:
                setSelect(0);
                break;
            case R.id.tab_pictures:
                setSelect(1);
                break;
            case R.id.tab_found:
                setSelect(2);
                break;
            case R.id.tab_about:
                setSelect(3);
                break;
        }
    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (i)
        {
            case 0:
                if (mTab01 == null)
                {
                    mTab01 = new NewsFragment();
                    transaction.add(R.id.id_content, mTab01);
                } else
                {
                    transaction.show(mTab01);
                }
                mTabs.get(0).setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_comprehensive_pressed_icon, 0, 0);
                break;
            case 1:
                if (mTab02 == null)
                {
                    mTab02 = new BeautyFragment();transaction.add(R.id.id_content, mTab02);
                } else
                {
                    transaction.show(mTab02);

                }
                mTabs.get(1).setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_move_pressed_icon, 0, 0);
                break;
            case 2:
                if (mTab03 == null)
                {
                    mTab03 = new KnowledgeFragment();
                    transaction.add(R.id.id_content, mTab03);
                } else
                {
                    transaction.show(mTab03);
                }
                mTabs.get(2).setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_found_pressed_icon, 0, 0);
                break;
            case 3:
                if (mTab04 == null)
                {
                    mTab04 = new AboutFragment();
                    transaction.add(R.id.id_content, mTab04);
                } else
                {
                    transaction.show(mTab04);
                }
                mTabs.get(3).setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_me_pressed_icon, 0, 0);
                break;

            default:
                break;
        }

        transaction.commit();
    }
    /**
     * 切换图片至暗色
     */
    private void resetImgs()
    {
        mTabs.get(0).setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.tab_comprehensive_icon, 0, 0);
        mTabs.get(1).setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_move_icon, 0, 0);
        mTabs.get(2).setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_found_icon, 0, 0);
        mTabs.get(3).setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.tab_me_icon, 0, 0);
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if (mTab01 != null)
        {
            transaction.hide(mTab01);
        }
        if (mTab02 != null)
        {
            transaction.hide(mTab02);
        }
        if (mTab03 != null)
        {
            transaction.hide(mTab03);
        }
        if (mTab04 != null)
        {
            transaction.hide(mTab04);
        }
    }

}
