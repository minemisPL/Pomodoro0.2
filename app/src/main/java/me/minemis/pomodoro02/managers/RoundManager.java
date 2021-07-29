package me.minemis.pomodoro02.managers;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import me.minemis.pomodoro02.activities.MainActivity;

public class RoundManager {

    private final Map<PomOption, Integer> settings = new HashMap<>();
    private final CountdownManager countdownManager;
    private final ViewManager.Main mainViewManager;
    private PomOption currentState = PomOption.FOCUS;
    private PomOption previousState = PomOption.SHORT_BREAK;
    private int currentRound = 0;
    private int totalRounds = 0;
    private final TextView txtWhichRound;
    private final TextView txtTotalRounds;
    private final TextView txtState;

    public RoundManager(MainActivity mainActivity) {
        this.countdownManager = mainActivity.getCountdownManager();
        mainViewManager = mainActivity.getMainViewManager();
        this.txtWhichRound = mainViewManager.getTxtWhichRound();
        this.txtTotalRounds = mainViewManager.getTxtTotalRounds();
        this.txtState = mainViewManager.getTxtCurrentState();
        assignSettings(PomOption.FOCUS, PomOption.SHORT_BREAK, PomOption.LONG_BREAK, PomOption.ROUNDS);
        updateText();
    }

    public void nextRound() {
        this.countdownManager.newCountdown(getPomOptionValue(currentState));

        updateStateText();

        previousState = currentState;

        if (currentState == PomOption.FOCUS) {
            currentRound++;
            totalRounds++;

            if (currentRound > getPomOptionValue(PomOption.ROUNDS)) {
                currentRound = 1;
            }

            updateText();

            Integer rounds = getPomOptionValue(PomOption.ROUNDS);

            currentState = currentRound == rounds ? PomOption.LONG_BREAK : PomOption.SHORT_BREAK;
            return;
        }
        currentState = PomOption.FOCUS;
    }

    public void resetRound() {
        this.countdownManager.stopTimer();
        this.countdownManager.newCountdown(getPomOptionValue(previousState));
        this.mainViewManager.setPlayButtonAsArrow();
    }

    @SuppressLint("SetTextI18n")
    public void updateText() {
        txtWhichRound.setText(currentRound + "/" + getPomOptionValue(PomOption.ROUNDS));
        txtTotalRounds.setText(String.valueOf(totalRounds));
    }

    private void updateStateText() {
        txtState.setText(currentState.getStringValue());
    }

    private Integer getPomOptionValue(PomOption pomOption) {
        Integer value = settings.get(pomOption);

        if (value == null) {
            return pomOption.getDefaultValue();
        }
        return value;
    }

    private void assignSettings(PomOption... options) {
        for (PomOption option : options) {
            settings.put(option, option.getDefaultValue());
        }
    }

    public void reset() {
        currentRound = 0;
        currentState = PomOption.FOCUS;
        nextRound();
    }

    public void setPomOptionValue(PomOption pomOption, int value) {
        settings.put(pomOption, value);
    }

    public Map<PomOption, Integer> getSettings() {
        return settings;
    }

    public PomOption getPreviousState() {
        return previousState;
    }

    public void checkIfRoundAreWrong() {
        if (currentRound >= getPomOptionValue(PomOption.ROUNDS)) {
            reset();
        }
    }
}
