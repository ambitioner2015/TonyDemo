package com.example.tony.tonydemo.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tony.tonydemo.R;
import com.example.tony.tonydemo.widget.CustomProgressDialog;
import com.github.ybq.android.spinkit.SpinKitView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnPageChange;
import retrofit2.http.Url;

public class NewsContentActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.webview)
    WebView webview;

    WebSettings webSettings;

    Context cxt = this;
    private CustomProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitle("新闻详情");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("lll","导航点击了。。。。");
                finish();
            }
        });
    }
    private void initView() {
        String tmp = getIntent().getExtras().getString("url");
        //CookieUtil.synCookies(this, Constant.AppWebBaseURL);
        webview.setHorizontalScrollBarEnabled(false);//水平不显示
        webview.setVerticalScrollBarEnabled(false); //垂直不显示
        //加上下面这段代码可以使网页中的链接不以浏览器的方式打开
        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebChromeClient(new WebChromeClient());



        webview.getSettings().setBuiltInZoomControls(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview.getSettings().setBlockNetworkImage(true);


        //得到webview设置
        webSettings = webview.getSettings();
        //允许使用javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
//        mwebview.setWebContentsDebuggingEnabled(true);
        //加载服务器上的页面
        Log.e("url",tmp);

        webview.loadUrl("http://www.jianshu.com/p/b30a5c1c01e8");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }

        private String getparam(String url) {
            if (url.equals("")||!url.contains("="))
                return "";
            else {
                int start = url.indexOf('=');
                return url.substring(start+1, url.length());
            }
        }

        @Override
        public void onPageStarted(final WebView view, String url, Bitmap favicon) {
            if (progressDialog == null){
                progressDialog = CustomProgressDialog.createDialog(cxt);
            }
            progressDialog.setTitle("加载中....");
            progressDialog.setMessage("正在加载。。。");
            progressDialog.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.e("new","onPageFinished");
            if (progressDialog != null){
                progressDialog.dismiss();
                progressDialog = null;
            }
            super.onPageFinished(view, url);
        }


    }
}
