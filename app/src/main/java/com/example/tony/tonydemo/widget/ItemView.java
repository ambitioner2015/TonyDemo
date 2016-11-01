package com.example.tony.tonydemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tony.tonydemo.R;

/**
 * Created by lzy on 2016/10/29.
 */

public class ItemView extends LinearLayout {
    String itemtext;
    Drawable img;
    TextView itemtextTextView;
    ImageView imgImageView;

    private LayoutParams mTextParams, mimgParams;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        itemtextTextView = new TextView(getContext());
        imgImageView = new ImageView(getContext());

        //获得属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        itemtext = ta.getString(R.styleable.ItemView_itemtext);
        img = ta.getDrawable(R.styleable.ItemView_imgsrc);

        //设置子控件的属性
        itemtextTextView.setText(itemtext);
        itemtextTextView.setTextColor(Color.BLACK);
        itemtextTextView.setGravity(Gravity.CENTER);
        imgImageView.setImageDrawable(img);

        //添加view
        mimgParams = new LayoutParams(LayoutParams.MATCH_PARENT,200);
        addView(imgImageView,mimgParams);
        mTextParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(itemtextTextView,mTextParams);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
