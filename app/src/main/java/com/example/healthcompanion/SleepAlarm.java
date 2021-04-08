package com.example.healthcompanion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SleepAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId = intent.getIntExtra("notificationId",0);


        Intent mainIntent = new Intent(context,SleepTime.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,mainIntent,0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Wake Up!")
                .setContentText("Are you willing to wake up?")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL);



        notificationManager.notify(notificationId,builder.build());


    }
}

