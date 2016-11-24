package com.example.tony.tonydemo.View;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.widget.NumProgress;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnimFrameActivity extends AppCompatActivity {

    @Bind(R.id.animView)
    ImageView animView;

    AnimationDrawable animationDrawable;

    @Bind(R.id.progressNum)
    NumProgress mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_frame);
        ButterKnife.bind(this);
        //Debug.startMethodTracing("tonynews");

        animationDrawable = (AnimationDrawable) animView.getDrawable();
        //开始动画
        animationDrawable.start();
        mProgress.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Debug.stopMethodTracing();
    }
}
