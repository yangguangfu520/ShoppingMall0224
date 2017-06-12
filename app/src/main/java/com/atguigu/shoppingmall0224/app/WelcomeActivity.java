package com.atguigu.shoppingmall0224.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.home.activity.MainActivity;

public class WelcomeActivity extends AppCompatActivity {


    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //两秒钟进入主页面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程
                //启动主页面
                startMainActivity();
            }
        },2000);
    }

    private void startMainActivity() {
        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
        //关闭当前页面
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //点击快速进入主页面
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            startMainActivity();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除消息
        handler.removeCallbacksAndMessages(null);
    }
}
