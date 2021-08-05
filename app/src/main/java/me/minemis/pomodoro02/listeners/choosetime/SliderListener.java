package me.minemis.pomodoro02.listeners.choosetime;

import androidx.annotation.NonNull;

import com.google.android.material.slider.Slider;

import java.util.Optional;

import me.minemis.pomodoro02.activities.ChooseTimeActivity;
import me.minemis.pomodoro02.PomOption;
import me.minemis.pomodoro02.managers.RoundManager;
import me.minemis.pomodoro02.managers.SliderManager;

public class SliderListener implements Slider.OnChangeListener {
    private final SliderManager sliderManager;
    private final RoundManager roundManager;

    public SliderListener(ChooseTimeActivity chooseTimeActivity) {
        sliderManager = chooseTimeActivity.getSliderManager();
        this.roundManager = chooseTimeActivity.getRoundManager();
    }

    @Override
    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
        int intValue = (int) value;

        sliderManager.setSliderValue(slider, intValue);
        Optional<PomOption> pomOption = sliderManager.getPomOptionFromSlider(slider);

        pomOption.ifPresent(option -> roundManager.setPomOptionValue(option, intValue));
    }
}
