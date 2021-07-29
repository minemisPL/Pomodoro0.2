package me.minemis.pomodoro02.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.listeners.choosetime.ResetButtonChooseTimeListener;
import me.minemis.pomodoro02.managers.PomOption;
import me.minemis.pomodoro02.managers.RoundManager;
import me.minemis.pomodoro02.managers.SliderManager;
import me.minemis.pomodoro02.managers.ViewManager;

public class ChooseTimeActivity extends AppCompatActivity {

    private MainActivity mainActivity;
    private SliderManager sliderManager;
    private ViewManager.ChooseTime ctViewManager;
    private RoundManager roundManager;

    private static ChooseTimeActivity instance;

    public ChooseTimeActivity() {
        super();
        instance = this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_time);
        setMetrics();
        assignValues();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainActivity.getSaveManager().save();

        if (sliderManager.checkIfChanged(roundManager.getPreviousState())) {
            roundManager.resetRound();
        }
        roundManager.updateText();
        roundManager.checkIfRoundAreWrong();
    }

    private void assignValues() {
        mainActivity =      MainActivity.getInstance();
        roundManager =      mainActivity.getRoundManager();

        sliderManager =     new SliderManager();
        ctViewManager =     new ViewManager.ChooseTime(this);

        sliderManager.addSlider(PomOption.FOCUS, ctViewManager.getSliderFocus(), ctViewManager.getTxtFocusValue());
        sliderManager.addSlider(PomOption.SHORT_BREAK, ctViewManager.getSliderBreak(), ctViewManager.getTxtBreakValue());
        sliderManager.addSlider(PomOption.LONG_BREAK, ctViewManager.getSliderLongBreak(), ctViewManager.getTxtLongBreakValue());
        sliderManager.addSlider(PomOption.ROUNDS, ctViewManager.getSliderRounds(), ctViewManager.getTxtRounds());

        sliderManager.setListeners();
        ctViewManager.getResetButton().setOnClickListener(new ResetButtonChooseTimeListener(this));

        roundManager.getSettings().forEach(sliderManager::assignSliderValue);
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

    public RoundManager getRoundManager() {
        return roundManager;
    }

    public static ChooseTimeActivity getInstance() {
        return instance;
    }
}
