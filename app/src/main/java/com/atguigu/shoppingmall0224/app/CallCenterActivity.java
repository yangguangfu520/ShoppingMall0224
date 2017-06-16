package com.atguigu.shoppingmall0224.app;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.atguigu.shoppingmall0224.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallCenterActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.activity_call_center)
    RelativeLayout activityCallCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_center);
        ButterKnife.bind(this);

        initWebview();

    }

    private void initWebview() {
        WebSettings settings = webview.getSettings();
        //支持javascript
        settings.setJavaScriptEnabled(true);
        //支持缩放
        settings.setUseWideViewPort(true);
        //不跳转到系统的浏览器里
        webview.setWebViewClient(new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }
        });

        //加载地址
        webview.loadUrl("http://www6.53kf.com/webCompany.php?arg=10007377&style=1&kflist=off&kf=info@atguigu.com,video@atguigu.com,public@atguigu.com,3069368606@qq.com,215648937@qq.com,sudan@atguigu.com,wangya@atguigu.com,zhuchangqing@atguigu.com,wanggang@atguigu.com&zdkf_type=1&language=zh-cn&charset=gbk&referer=http%3A%2F%2Fatguigu.com%2F&keyword=&tfrom=1&tpl=crystal_blue&uid=81fc9fa92026169eed179e071eaab8b0&timeStamp=1497584383254&ucust_id=");
//        webview.loadUrl("http://atguigu.com/");
    }
}
