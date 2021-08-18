package me.minemis.pomodoro02.listeners.main;

import android.annotation.SuppressLint;
import android.view.View;

import me.minemis.pomodoro02.App;
import me.minemis.pomodoro02.activities.MainActivity;
import me.minemis.pomodoro02.managers.CountdownManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class ResetButtonMainListener implements View.OnClickListener {

    private final CountdownManager countdownManager;

    public ResetButtonMainListener() {
        App app = App.getInstance();
        this.countdownManager = app.getCountdownManager();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        countdownManager.reset();
    }
}
