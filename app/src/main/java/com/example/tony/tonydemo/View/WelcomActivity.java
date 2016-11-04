package com.example.tony.tonydemo.View;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.tony.tonydemo.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomActivity extends AppCompatActivity {

    @Bind(R.id.welcomeImg)
    ImageView mImageView;

    WelcomeHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcom);
        ButterKnife.bind(this);
        mHandler = new WelcomeHandler(this);
        mImageView.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();

        Timer timer=new Timer();
        TimerTask task=new TimerTask(){
            public void run(){
                Intent intent=new Intent(WelcomActivity.this,MainActivity.class); //通过Intent实现跳转
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(task,1000*3); //呈现2秒跳转到MainActivity(主界面)中
        //mHandler.sendEmptyMessageAtTime(1,5000);

    }
    private static class WelcomeHandler extends Handler{
        Context mContext;
        public WelcomeHandler(Context context) {
            mContext = context;
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1)
            {
                //mContext.startActivity(new Intent(mContext,MainActivity.class));
            }
        }
    }
}
