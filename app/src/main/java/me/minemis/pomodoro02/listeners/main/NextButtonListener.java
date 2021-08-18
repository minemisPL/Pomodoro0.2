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

    public NextButtonListener() {
        App app = App.getInstance();
        this.countdownManager = app.getCountdownManager();
        this.roundManager = app.getRoundManager();
    }

    @Override
    public void onClick(View v) {
        countdownManager.stopTimer();
        roundManager.nextRound();
        countdownManager.startTimer();
    }
}
