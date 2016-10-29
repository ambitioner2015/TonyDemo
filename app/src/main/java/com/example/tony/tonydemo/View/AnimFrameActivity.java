package com.example.tony.tonydemo.View;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.tony.tonydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnimFrameActivity extends AppCompatActivity {

    @Bind(R.id.animView)
    ImageView animView;

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_frame);
        ButterKnife.bind(this);

        animationDrawable = (AnimationDrawable) animView.getDrawable();
        //开始动画
        animationDrawable.start();
    }
}
