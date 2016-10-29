package com.example.tony.tonydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.tony.tonydemo.R;

/**
 * Created by lzy on 2016/a10/27.
 */
public class DongzuoView extends ImageView{
    private int mlasty=0;
    private int mStartY = 0;
    public DongzuoView(Context context) {
        super(context);
    }

    public DongzuoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setImageResource(R.drawable.dongzuo);
        AnimationDrawable animationDrawable1 = (AnimationDrawable) this.getDrawable();
        animationDrawable1.start();
    }

    public DongzuoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setImageResource(R.drawable.dongzuo);
        AnimationDrawable animationDrawable1 = (AnimationDrawable) this.getDrawable();
        animationDrawable1.start();
    }

    public DongzuoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setImageResource(R.drawable.dongzuo);
        AnimationDrawable animationDrawable1 = (AnimationDrawable) this.getDrawable();
        animationDrawable1.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mStartY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (int) (event.getY()-mStartY);
                Animation animation = new ScaleAnimation(0.0f, 0.5f, 0.0f, 0.5f);
                animation.setDuration(7000);
                animation.setFillAfter(true);
                animation.setInterpolator(new LinearInterpolator());
                this.startAnimation(animation);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
