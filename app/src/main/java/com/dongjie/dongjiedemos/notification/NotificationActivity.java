package com.dongjie.dongjiedemos.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.ycbjie.notificationlib.NotificationUtils;

/**
 * 第三方的通知库
 * GitHub地址：https://github.com/yangchong211/YCNotification
 */
public class NotificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    // 简单调用
    public void onButtonClick(View v) {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils.sendNotification(1,"这个是标题","这个是内容",R.mipmap.ic_launcher);
    }

    public void onButtonClick2(View v) {
        long[] vibrate = new long[]{0, 500, 1000, 1500};
        //处理点击Notification的逻辑
        //创建intent
        Intent resultIntent = new Intent(this, NotificationClickJumpActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        resultIntent.putExtra("what",3);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,3,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                //让通知左右滑的时候是否可以取消通知, false可清除， true不可清除
                .setOngoing(false)
                //设置内容点击处理intent
                .setContentIntent(resultPendingIntent)
                //设置状态栏的标题
                .setTicker("来通知消息啦")
                //设置自定义view通知栏布局
                .setContent(getNotificationView()) // 设置了这一句下面sendNotification设置的都会被盖掉
                //设置sound
                .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
                //设置优先级
                .setPriority(Notification.PRIORITY_DEFAULT)
                //自定义震动效果
                .setVibrate(vibrate)
                //必须设置的属性，发送通知
                .sendNotification(3,"这个是标题3", "这个是内容3", R.mipmap.ic_launcher);
    }

    private RemoteViews getNotificationView() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);//通知栏布局
        remoteViews.setTextViewText(R.id.title, "这是自定义通知的title");
        remoteViews.setTextViewText(R.id.content, "这是自定义通知的内容");
        return remoteViews;
    }

    public void onButtonClick3(View v) {
        NotificationUtils notificationUtils = new NotificationUtils(this);
//        notificationUtils.clearNotification(); // 取消所有
        notificationUtils.getManager().cancel(3); // 取消某一个通知
    }
}
