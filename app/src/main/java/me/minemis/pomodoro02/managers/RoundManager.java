package me.minemis.pomodoro02.managers;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import me.minemis.pomodoro02.activities.MainActivity;

public class RoundManager {

    private final Map<PomOption, Integer> settings = new HashMap<>();
    private final CountdownManager countdownManager;
    private PomOption currentState = PomOption.FOCUS;
    private int currentRound = 1;
    private int totalRounds = 1;
    private final TextView txtWhichRound;
    private final TextView txtTotalRounds;

    public RoundManager(MainActivity mainActivity) {
        this.countdownManager = mainActivity.getCountdownManager();
        ViewManager.Main mainViewManager = mainActivity.getMainViewManager();
        this.txtWhichRound = mainViewManager.getTxtWhichRound();
        this.txtTotalRounds = mainViewManager.getTxtTotalRounds();
        updateText();
    }

    public void nextRound() {
        this.countdownManager.newCountdown(getPomOptionValue(currentState));

        if (currentRound > getPomOptionValue(PomOption.ROUNDS)) {
            currentRound = 1;
        }

        if (currentState == PomOption.FOCUS) {
            currentRound++;
            totalRounds++;

            updateText();

            Integer rounds = getPomOptionValue(PomOption.ROUNDS);

            currentState = currentRound == rounds ? PomOption.LONG_BREAK : PomOption.SHORT_BREAK;
            return;
        }
        currentState = PomOption.FOCUS;
    }

    @SuppressLint("SetTextI18n")
    private void updateText() {
        txtWhichRound.setText(currentRound + "/" + getPomOptionValue(PomOption.ROUNDS));
        txtTotalRounds.setText(String.valueOf(totalRounds));
    }

    private Integer getPomOptionValue(PomOption pomOption) {
        Integer value = settings.get(currentState);

        if (value == null) {
            return pomOption.getDefaultValue();
        }
        return value;
    }

    public void reset() {
        currentRound = 1;
        currentState = PomOption.FOCUS;
        nextRound();
    }

    public void setPomOptionValue(PomOption pomOption, int value) {
        settings.put(pomOption, value);
    }

    public Map<PomOption, Integer> getSettings() {
        return settings;
    }
}
