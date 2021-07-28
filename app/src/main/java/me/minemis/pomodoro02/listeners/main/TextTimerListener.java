package me.minemis.pomodoro02.listeners.main;

import android.content.Intent;
import android.view.View;

import me.minemis.pomodoro02.activities.ChooseTimeActivity;
import me.minemis.pomodoro02.activities.MainActivity;

public class TextTimerListener implements View.OnClickListener {

    private final MainActivity mainActivity;

    public TextTimerListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mainActivity, ChooseTimeActivity.class);
        mainActivity.startActivity(intent);
    }
}
