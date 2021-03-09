package com.example.mychatapp.Notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class OreaAndAboveNotification extends ContextWrapper {

    public static final String ID = "com.example.mychatapp";
    public static final String NAME = "MyChatApp";
    private NotificationManager notificationManager;


    public OreaAndAboveNotification(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O) {

            createChannel();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {

        NotificationChannel notificationChannel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            getManager().createNotificationChannel(notificationChannel);




    }

    public NotificationManager getManager() {


        if (notificationManager == null) {


            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        }

        return  notificationManager;


    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder getNotification(String title, String body, PendingIntent pIntent, Uri soundUri, String icon) {



        return  new Notification.Builder(getApplicationContext(), ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(soundUri)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSmallIcon(Integer.parseInt(icon));




    }


    // not using
    @TargetApi(Build.VERSION_CODES.O)
    public NotificationCompat.Builder getNotificationShit(NotificationCompat.MessagingStyle style, NotificationCompat.Action action, String title, String body, PendingIntent pIntent, Uri soundUri, String icon) {



        return  new NotificationCompat.Builder(this, ID)
                .addAction(action)
                .setStyle(style)
                .setContentIntent(pIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(soundUri)
                .setSmallIcon(Integer.parseInt(icon));




    }


}
