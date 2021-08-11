package me.minemis.pomodoro02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import me.minemis.pomodoro02.PomodoroCache;
import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.managers.CountdownManager;
import me.minemis.pomodoro02.managers.PomNotificationManager;
import me.minemis.pomodoro02.managers.RoundManager;
import me.minemis.pomodoro02.managers.SaveManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class MainActivity extends AppCompatActivity {

    private CountdownManager countdownManager;
    private RoundManager roundManager;
    private SaveManager saveManager;
    private ViewManager.Main mainViewManager;
    private PomNotificationManager pomNotificationManager;

    @SuppressLint("StaticFieldLeak")
    private static MainActivity instance;

    public MainActivity() {
        super();
        instance = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Started!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("Restarted!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Paused!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resumed!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Created!");
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.assignValues();
        this.saveManager.loadPreferences();
        System.out.println("Time before load after load: " + PomodoroCache.timeLeftInMillis);
        this.saveManager.loadData();
        System.out.println("Time left after load: " + PomodoroCache.timeLeftInMillis);
        this.createNotificationChannel();

        if (PomodoroCache.isFirstRun) {
            this.roundManager.nextRound();
            return;
        }
        this.roundManager.continueRound();

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.saveManager.savePreferences();
        System.out.println("Stopped!");
    }

    @Override
    protected void onDestroy() {
        saveManager.saveData();
        System.out.println("Time left: " + PomodoroCache.timeLeftInMillis);
        super.onDestroy();
        System.out.println("Destroyed!");
    }



    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    "pom_1",
                    "Pomodoro",
                    NotificationManager.IMPORTANCE_LOW);

            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableLights(true);

            @SuppressLint("ServiceCast")
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    private void assignValues() {
        this.pomNotificationManager = new PomNotificationManager();
        this.mainViewManager = new ViewManager.Main(this);
        this.countdownManager = new CountdownManager(this);
        this.roundManager = new RoundManager(this);
        this.saveManager = new SaveManager(this, getSharedPreferences("PomodoroTimes02", MODE_PRIVATE));
        this.mainViewManager.assignListeners();
    }

    public ViewManager.Main getMainViewManager() {
        return mainViewManager;
    }

    public RoundManager getRoundManager() {
        return roundManager;
    }

    public SaveManager getSaveManager() {
        return saveManager;
    }

    public CountdownManager getCountdownManager() {
        return countdownManager;
    }

    public PomNotificationManager getPomNotificationManager() {
        return pomNotificationManager;
    }

    public static MainActivity getInstance() {
        return instance;
    }
}