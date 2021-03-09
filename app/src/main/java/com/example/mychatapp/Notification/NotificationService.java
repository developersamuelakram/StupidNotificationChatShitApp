package com.example.mychatapp.Notification;

import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.example.mychatapp.Model.Chats;

public class NotificationService extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput!=null) {

            String replyText = remoteInput.getString("key_text_reply");
            Chats answser = new Chats(null, null, replyText, false);
            FirebaseMessaging.chatsArrayList.add(answser);



        }
    }
}
