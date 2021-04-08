package com.example.healthcompanion;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class WaterService extends Service {

    long millis;
    int id;
    String programTime;
    Notification notification;
    NotificationManager notificationManager;
    IntentFilter intentFilter;


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("water", "onCreate: ");
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showNotification(String programTime, int id) {

        RemoteViews views = new RemoteViews(getPackageName(), R.layout.add_schedule);

        String CHANNEL_ID = "1";
        CharSequence channelName = "Water";

        Intent recentIntent = new Intent(this, MainActivity.class);
        recentIntent.setAction("com.healthCompanion.remainder.STOP_SERVICE");
        recentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendInt = PendingIntent.getActivity(this, id,
                recentIntent, PendingIntent.FLAG_ONE_SHOT);

        views.setTextViewText(R.id.text, programTime);

        notification = new NotificationCompat.Builder(this)
                .setCustomContentView(views)
                .setContentIntent(pendInt)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setChannelId(CHANNEL_ID)
                .build();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    channelName, NotificationManager.IMPORTANCE_LOW);
            channel.setSound(null, null);
            notificationManager.createNotificationChannel(channel);
        }

        startForeground(id, notification);
    }
}