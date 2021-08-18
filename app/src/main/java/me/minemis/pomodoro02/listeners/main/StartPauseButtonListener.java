package me.minemis.pomodoro02.listeners.main;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import me.minemis.pomodoro02.App;
import me.minemis.pomodoro02.activities.MainActivity;
import me.minemis.pomodoro02.managers.CountdownManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class StartPauseButtonListener implements View.OnClickListener {
    private final App app;
    private final CountdownManager countdownManager;
    private final ViewManager.Main viewManager;

    public StartPauseButtonListener() {
        this.app = App.getInstance();
        this.countdownManager = app.getCountdownManager();
        this.viewManager = MainActivity.getInstance().getMainViewManager();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {

        app.getPomNotificationManager().notifyTimer();

        if (countdownManager.isRunning()){
            countdownManager.stopTimer();
            return;
        }

        countdownManager.startTimer();
        viewManager.setPlayButtonAsPause();
    }
}
