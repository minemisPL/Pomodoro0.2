package me.minemis.pomodoro02.listeners.main;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageButton;

import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.activities.MainActivity;
import me.minemis.pomodoro02.managers.CountdownManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class ResetButtonMainListener implements View.OnClickListener {

    private final MainActivity mainActivity;
    private final CountdownManager countdownManager;
    private final ViewManager.Main mainViewManager;

    public ResetButtonMainListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.countdownManager = mainActivity.getCountdownManager();
        this.mainViewManager = mainActivity.getMainViewManager();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        countdownManager.reset();
        mainViewManager.setPlayButtonAsArrow();
    }
}
