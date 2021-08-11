package me.minemis.pomodoro02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import me.minemis.pomodoro02.App;
import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.managers.CountdownManager;
import me.minemis.pomodoro02.managers.PomNotificationManager;
import me.minemis.pomodoro02.managers.RoundManager;
import me.minemis.pomodoro02.managers.SaveManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class MainActivity extends AppCompatActivity {

    private RoundManager roundManager;
    private SaveManager saveManager;
    private ViewManager.Main mainViewManager;

    @SuppressLint("StaticFieldLeak")
    private static MainActivity instance;

    public MainActivity() {
        super();
        instance = this;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.assignValues();
        this.saveManager.load();

        this.roundManager.nextRound();
    }


    @Override
    protected void onStop() {
        super.onStop();
        this.saveManager.save();
    }

    private void assignValues() {
        App app = App.getInstance();
        this.mainViewManager = new ViewManager.Main(this);
        this.mainViewManager.assignListeners();
        this.roundManager = app.getRoundManager();
        this.saveManager = app.getSaveManager();

    }

    public ViewManager.Main getMainViewManager() {
        return mainViewManager;
    }

    public static MainActivity getInstance() {
        return instance;
    }
}