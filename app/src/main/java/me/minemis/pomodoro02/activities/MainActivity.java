package me.minemis.pomodoro02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.managers.CountdownManager;
import me.minemis.pomodoro02.managers.RoundManager;
import me.minemis.pomodoro02.managers.SaveManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class MainActivity extends AppCompatActivity {

    private CountdownManager countdownManager;
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.saveManager.save();
    }

    private void assignValues() {
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

    public static MainActivity getInstance() {
        return instance;
    }
}