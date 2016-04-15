package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(this); //获取实例
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v->{
            //将要发送的广播的值传入，所有监听这条广播的广播接收器都会收到消息
            //Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
            //sendBroadcast(intent); //将广播发送出去
            //sendOrderedBroadcast(intent,null); //发送一条有序广播，第一个参数是Intent，第二个参数与权限相关的字符串

            Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
            localBroadcastManager.sendBroadcast(intent);//发送本地广播
        });

        /*intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.com.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();*/

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter); //注册本地广播

    }

    protected void onDestory() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    //
    class NetworkChangeReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            //得到一个用于管理网络连接的实例。
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //isAvailable()方法用于判断当前是否有网络
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LocalReceiver extends BroadcastReceiver{
        public void onReceive(Context context,Intent intent){
            Toast.makeText(context,"received local broadcast" ,Toast.LENGTH_SHORT).show();
        }
    }

}
