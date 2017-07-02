package com.survey.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.survey.Constants;
import com.survey.MainActivity;
import com.survey.R;
import com.survey.Syso;

import java.util.Random;

public class MyGcmListenerService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            Syso.error("Message "+remoteMessage.getFrom()+":- "+remoteMessage.getNotification().getBody());

           // createNotification(message, gcmResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNotification(String message) {
        Context context = getBaseContext();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setTicker(message);
        notificationBuilder.setContentTitle(getResources().getString(R.string.app_name));
        notificationBuilder.setContentText(message);
        notificationBuilder.setAutoCancel(true);
        Intent intent = new Intent(context, MainActivity.class);

        try {
            PendingIntent pendingIntent = null;
            pendingIntent = PendingIntent.getActivity(context, randomInt, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notificationBuilder.setContentIntent(pendingIntent);
            Notification notification = notificationBuilder.build();
            notification.defaults |= Notification.DEFAULT_SOUND;
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(randomInt, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
