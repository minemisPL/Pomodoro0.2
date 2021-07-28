package me.minemis.pomodoro02.listeners.choosetime;

import android.view.View;

import me.minemis.pomodoro02.activities.ChooseTimeActivity;
import me.minemis.pomodoro02.managers.SliderManager;

public class ResetButtonChooseTimeListener implements View.OnClickListener {
    private final SliderManager sliderManager;

    public ResetButtonChooseTimeListener(ChooseTimeActivity chooseTimeActivity) {
        sliderManager = chooseTimeActivity.getSliderManager();
    }

    @Override
    public void onClick(View v) {
        sliderManager.reset();
    }
}
