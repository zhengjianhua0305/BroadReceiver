package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_1;
    private TextView tv_1;
    //实例化广播方法
    private MyBroadcast mBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = (Button) findViewById(R.id.btn_1);
        tv_1 = (TextView) findViewById(R.id.tv_1);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.myapplication.BroadcastReceiver.class);
                startActivity(intent);
            }
        });
        mBroadcast = new MyBroadcast();
        //接受广播意图过滤
        IntentFilter intentFilter = new IntentFilter();
        //接受到哪个意图
        intentFilter.addAction("com.skypan.update");
        //注册广播
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcast,intentFilter);

    }
    //定义接受广播的方法
    private class MyBroadcast extends android.content.BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "com.skypan.update":
                    tv_1.setText("123456");
                    break;
            }
        }
    }

    //注册过广播，需要注销掉，放置内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcast);
    }
}
