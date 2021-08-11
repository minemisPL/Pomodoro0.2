package me.minemis.pomodoro02;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import me.minemis.pomodoro02.managers.CountdownManager;
import me.minemis.pomodoro02.managers.PomNotificationManager;
import me.minemis.pomodoro02.managers.RoundManager;
import me.minemis.pomodoro02.managers.SaveManager;

public class App extends Application {
    public static final String CHANNEL_ID = "pom_1";
    public static final String CHANNEL_NAME = "Pomodoro";
    private static App instance;
    private CountdownManager countdownManager;
    private RoundManager roundManager;
    private SaveManager saveManager;
    private PomNotificationManager pomNotificationManager;

    public App() {
        super();
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //createNotificationChannel();

        assignValues();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    App.CHANNEL_ID,
                    App.CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_LOW);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    private void assignValues() {
        this.pomNotificationManager = new PomNotificationManager();
        this.countdownManager = new CountdownManager();
        this.roundManager = new RoundManager();
        this.saveManager = new SaveManager(getSharedPreferences("PomodoroTimes02", MODE_PRIVATE));
    }

    public static App getInstance() {
        return instance;
    }

    public CountdownManager getCountdownManager() {
        return countdownManager;
    }

    public RoundManager getRoundManager() {
        return roundManager;
    }

    public SaveManager getSaveManager() {
        return saveManager;
    }

    public PomNotificationManager getPomNotificationManager() {
        return pomNotificationManager;
    }
}
