package me.minemis.pomodoro02.managers;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import me.minemis.pomodoro02.NotificationReceiver;
import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.activities.MainActivity;

public class PomNotificationManager extends AppCompatActivity {

    private final MainActivity mainActivity;
    private final NotificationManagerCompat notificationManager;
    private RemoteViews notificationView;
    private String CHANNEL_ID = "pom_1";
    private String CHANNEL_NAME = "Pomodoro";
    private Notification notification;
    private int NOTIFICATION_ID = 0;

    public PomNotificationManager() {
        this.mainActivity = MainActivity.getInstance();
        this.notificationManager = NotificationManagerCompat.from(mainActivity);
        this.createTimerNotification();
    }

    private void createTimerNotification() {

        this.notificationView = new RemoteViews(mainActivity.getPackageName(),
                R.layout.notification_timer);

        Intent intent = mainActivity.getIntent();
        TaskStackBuilder pendingIntent1 = TaskStackBuilder.create(mainActivity);
        pendingIntent1.addNextIntent(intent);

        PendingIntent pIntent = pendingIntent1.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        this.notification = new NotificationCompat.Builder(mainActivity, CHANNEL_ID)
                .setCustomContentView(notificationView)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_play_arrow)
                .setContentIntent(pIntent)
                //.setOngoing(true)
                .build();
    }
    public void notifyTimer() {
        this.notificationManager.notify(this.NOTIFICATION_ID, this.notification);
    }

    public void setTimerText(String text) {
        this.notificationView.setTextViewText(R.id.txt_noti_timer, text);
    }
}
