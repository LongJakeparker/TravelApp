package com.binmobile.foodyapp.Fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.binmobile.foodyapp.Activity.MainActivity;
import com.binmobile.foodyapp.Activity.MusicActivity;
import com.binmobile.foodyapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    private static final String TAG = "MyFirebaseService";

    //nhận kết quả các message từ server FCM gửi về, từ đây có thể xử lý chúng
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        // Sets up the Snooze and Dismiss action buttons that will appear in the
// big view of the notification.
        Intent dismissIntent = new Intent(this, MainActivity.class);
        dismissIntent.setAction("MainActivity");
        PendingIntent piDismiss = PendingIntent.getService(this, 0, dismissIntent, 0);

        Intent snoozeIntent = new Intent(this, MusicActivity.class);
        snoozeIntent.setAction("MusicActivity");
        PendingIntent piSnooze = PendingIntent.getService(this, 0, snoozeIntent, 0);

        // Constructs the Builder object.
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon_account)
                        .setContentTitle("Push Notification")
                        .setContentText("Test Firebase Cloud Messaging")
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(remoteMessage.getNotification().getBody()))
                        .addAction(R.drawable.icon_home,
                                "Đóng", piDismiss)
                        .addAction(R.drawable.icon_search, "Đồng ý", piSnooze);
        final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1000, builder.build());
    }

}
