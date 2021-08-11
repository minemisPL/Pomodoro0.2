package me.minemis.pomodoro02.listeners.main;

import android.view.View;

import me.minemis.pomodoro02.App;
import me.minemis.pomodoro02.activities.MainActivity;
import me.minemis.pomodoro02.managers.CountdownManager;
import me.minemis.pomodoro02.managers.RoundManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class NextButtonListener implements View.OnClickListener {

    private final CountdownManager countdownManager;
    private final RoundManager roundManager;
    private final ViewManager.Main mainViewManager;

    public NextButtonListener() {
        App app = App.getInstance();
        MainActivity mainActivity = MainActivity.getInstance();
        this.countdownManager = app.getCountdownManager();
        this.roundManager = app.getRoundManager();
        this.mainViewManager = mainActivity.getMainViewManager();
    }

    @Override
    public void onClick(View v) {
        countdownManager.stopTimer();
        roundManager.nextRound();
        countdownManager.startTimer();
        mainViewManager.setPlayButtonAsPause();
    }
}
