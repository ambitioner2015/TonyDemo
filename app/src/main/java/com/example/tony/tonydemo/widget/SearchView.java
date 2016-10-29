package com.example.tony.tonydemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.tony.tonydemo.R;

/**
 * Created by lzy on 2016/10/28.
 */
public class SearchView extends TextView{
    Paint mPaint1;
    Paint mPaint2;
    Paint txtPaint;
    Rect mDestRect;

    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.darker_gray));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(getResources().getColor(R.color.white));
        mPaint2.setStyle(Paint.Style.FILL);

        //文字画笔
        txtPaint = new Paint();
        txtPaint.setColor(this.getCurrentTextColor());
        txtPaint.setStyle(Paint.Style.FILL);
        txtPaint.setTextSize(this.getTextSize());
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //为搜索框加上边框
        int padding = this.getPaddingBottom();
        int width = getWidth();
        int height = getMeasuredHeight();
        int iconwidth = height - 2*padding;
        canvas.drawRoundRect(0,0, width,height,10,10,mPaint1);
        canvas.drawRoundRect(1,1, width -1, height -1,10,10,mPaint2);

        //为搜索框加上搜索图标
        Bitmap bit = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.find_search);
        float x = (width -txtPaint.measureText(getText().toString())-iconwidth-10)/2;
        mDestRect = new Rect((int)x, padding, (int) (x+height-2*padding), height -padding);
        canvas.drawBitmap(bit,null, mDestRect, mPaint2) ;

        //计算字体的高度,并且绘制文字
        Paint.FontMetrics fm = txtPaint.getFontMetrics();
        float y = (height -(fm.bottom +fm.top))/2;
        canvas.drawText(this.getText().toString(),x+iconwidth+10,y,txtPaint);
    }
}
