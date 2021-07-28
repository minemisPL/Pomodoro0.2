package me.minemis.pomodoro02.listeners.main;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;

import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.activities.MainActivity;
import me.minemis.pomodoro02.managers.CountdownManager;

public class StartPauseButtonListener implements View.OnClickListener {
    private final MainActivity mainActivity;
    private final CountdownManager countdownManager;

    public StartPauseButtonListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.countdownManager = mainActivity.getCountdownManager();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {

        ImageButton button = (ImageButton) v;

        if (countdownManager.isRunning()){
            countdownManager.stopTimer();
            button.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.ic_play_arrow));
            return;
        }

        countdownManager.startTimer();
        button.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.ic_pause));
    }
}
