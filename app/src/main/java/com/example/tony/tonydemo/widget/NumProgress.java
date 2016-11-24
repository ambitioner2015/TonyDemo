package com.example.tony.tonydemo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by lzy on 2016/11/4.
 */

public class NumProgress extends View {
    //画弧线的笔
    Paint mPaint;
    //画文字的笔
    Paint mtxtPaint;
    //圆弧的矩形
    RectF mRect;



    int swipAngle = 0;

    int mWidth;
    int mHeight;
    private float animationValue;

    public NumProgress(Context context) {
        super(context);
    }

    public NumProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);

        mtxtPaint = new Paint();
        mtxtPaint.setColor(Color.BLACK);
        mtxtPaint.setTextSize(50);

        //圆弧的矩形轮廓
        mRect = new RectF();
    }

    public void start() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                swipAngle = (int) Math.round((Float) animation.getAnimatedValue() * 360);
                animationValue = (Float) animation.getAnimatedValue();
                invalidate();
                Log.d(TAG, new Integer(swipAngle).toString());
            }
        });
        animator.start();
    }

    public NumProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NumProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        mWidth = widthSpecSize;
        mHeight = heightSpecSize;
        Log.e("onMeasure","width="+String.valueOf(widthSpecSize));
        Log.e("onMeasure","height"+String.valueOf(heightSpecSize));

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            mWidth = 400;
            mHeight=400;
            setMeasuredDimension(400, 400);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = 400;
            setMeasuredDimension(400, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            mHeight = 400;
            setMeasuredDimension(widthSpecSize, 400);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int times = 0;
        mRect.set(50, 50, mWidth-50, mHeight-50);
        canvas.drawArc(mRect, 0, swipAngle, false, mPaint);

        //计算字体的高度,并且绘制文字
        Paint.FontMetrics fm = mtxtPaint.getFontMetrics();
        float y = (mHeight -(fm.bottom +fm.top))/2;
        float x = (mWidth-mtxtPaint.measureText("10%"))/2;
        canvas.drawText(String.valueOf((int)(animationValue*100))+"%",x,y,mtxtPaint);
    }

}
