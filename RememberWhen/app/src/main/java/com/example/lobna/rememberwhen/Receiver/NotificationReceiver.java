package com.example.lobna.rememberwhen.Receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.example.lobna.rememberwhen.Activity.AddMemoryActivity;
import com.example.lobna.rememberwhen.Activity.MemoryActivity;
import com.example.lobna.rememberwhen.Database.DBSource;
import com.example.lobna.rememberwhen.Model.Memory;
import com.example.lobna.rememberwhen.R;

/**
 * Created by Lobna on 09-Jan-17.
 */

public class NotificationReceiver extends BroadcastReceiver{

    public static int requestCode = 100;
    private Memory memory;

    @Override
    public void onReceive(Context context, Intent intent) {
        memory = DBSource.getInstance().getRandomMemory();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // action resulted from clicking on Notification
        Intent openMemoryIntent;
        if(memory != null){
            openMemoryIntent = new Intent(context, MemoryActivity.class).putExtra("memory", memory);
        }else{
            openMemoryIntent = new Intent(context, AddMemoryActivity.class);
            memory = new Memory(); // Fake memory for notification description.
            memory.setDescription(context.getString(R.string.addMemory));
        }
        openMemoryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Even if the app is opened (same memory or any other activity) ,, the memory notification opening will replace any opened view.

        // Show intent into the notification
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, openMemoryIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(memory.getDescription())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true);

        notificationManager.notify(requestCode, notificationBuilder.build());
    }
}
