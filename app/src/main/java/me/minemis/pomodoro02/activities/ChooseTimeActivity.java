package me.minemis.pomodoro02.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.minemis.pomodoro02.managers.PomOption;
import me.minemis.pomodoro02.managers.SliderManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class ChooseTimeActivity extends AppCompatActivity {

    private SliderManager sliderManager;
    private ViewManager.ChooseTime ctViewManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMetrics();
        assignValues();
    }

    private void assignValues() {
        sliderManager =     new SliderManager();
        ctViewManager =     new ViewManager.ChooseTime(this);

        sliderManager.addSlider(PomOption.FOCUS, ctViewManager.getSliderFocus(), ctViewManager.getTxtFocusValue());
        sliderManager.addSlider(PomOption.SHORT_BREAK, ctViewManager.getSliderBreak(), ctViewManager.getTxtBreakValue());
        sliderManager.addSlider(PomOption.LONG_BREAK, ctViewManager.getSliderLongBreak(), ctViewManager.getTxtLongBreakValue());
        sliderManager.addSlider(PomOption.ROUNDS, ctViewManager.getSliderRounds(), ctViewManager.getTxtRounds());
    }

    private void setMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));
    }

    public SliderManager getSliderManager() {
        return sliderManager;
    }

    public ViewManager.ChooseTime getCtViewManager() {
        return ctViewManager;
    }
}
