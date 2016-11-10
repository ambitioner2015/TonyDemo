package com.example.tony.tonydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by lzy on 2016/11/9.
 */

public class LoadMoreListView extends ListView {
    private String TAG = "LoadMoreListView";
    private int mLastVisiblePosition = 0;
    private onLoadMore mloadmore;
    public interface onLoadMore{
        public void onLoad();
    }
    public LoadMoreListView(Context context) {
        super(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState)
                {
                    case OnScrollListener.SCROLL_STATE_IDLE:
                        break;
                    case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.e(TAG,"SCROLL_STATE_TOUCH_SCROLL");
                        break;
                    case OnScrollListener.SCROLL_STATE_FLING:
                        Log.e(TAG,"SCROLL_STATE_FLING");
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount == totalItemCount&&totalItemCount>0)
                {
                    Log.e(TAG,"已经到最后一行了");
                    mloadmore.onLoad();
                }

                if(firstVisibleItem>mLastVisiblePosition)
                {
                    Log.e(TAG,"上滑");
                }
                else if(firstVisibleItem<mLastVisiblePosition)
                {
                    Log.e(TAG,"下滑");
                }
                mLastVisiblePosition = firstVisibleItem;

            }
        });
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnLoadMoreListener(onLoadMore l)
    {
        mloadmore = l;
    }

//    @Override
//    public void setOnScrollListener(OnScrollListener l) {
//        super.setOnScrollListener();
//
//    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, 200, isTouchEvent);
    }
}
