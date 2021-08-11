package me.minemis.pomodoro02;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import me.minemis.pomodoro02.activities.MainActivity;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent1) {
        MainActivity mainActivity = MainActivity.getInstance();

        Intent intent = mainActivity.getIntent();
        TaskStackBuilder pendingIntent1 = TaskStackBuilder.create(mainActivity);
        pendingIntent1.addNextIntent(intent);

        PendingIntent pIntent = pendingIntent1.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);



        Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show();
    }
}
