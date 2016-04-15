package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.example.broadcasttest.MyBroadcastReceiver.java
 * @author: yyxyz
 * @date: 2016-04-14 20:44
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context,"received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast(); //将这条广播截断，后面的广播接收器将无法在接收到这条广播
    }
}
