package me.minemis.pomodoro02.managers;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import java.util.Map;

import me.minemis.pomodoro02.PomOption;
import me.minemis.pomodoro02.PomodoroCache;
import me.minemis.pomodoro02.activities.MainActivity;

public class SaveManager {
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private final Map<PomOption, Integer> settings;
    private final CountdownManager countdownManager;
    private final RoundManager roundManager;

    @SuppressLint("CommitPrefEdits")
    public SaveManager(MainActivity mainActivity, SharedPreferences preferences) {
        this.preferences = preferences;
        this.editor = preferences.edit();
        this.countdownManager = mainActivity.getCountdownManager();
        this.roundManager = mainActivity.getRoundManager();
        this.settings = this.roundManager.getSettings();
    }

    public void savePreferences() {
        settings.forEach(this::setData);
        editor.apply();
    }

    public void loadPreferences() {
        settings.forEach((option, data) -> settings.put(option, preferences.getInt(option.getId(), option.getDefaultValue())));
    }

    private void setData(PomOption option, Integer data) {
        editor.putInt(option.getId(), data);
    }

    public void saveData() {
        PomodoroCache.currentRound = roundManager.getCurrentRound();
        PomodoroCache.currentState = roundManager.getPreviousState();
        PomodoroCache.totalRounds = roundManager.getTotalRounds();

        PomodoroCache.timeLeftInMillis = countdownManager.getTimeLeftInMillis();
        PomodoroCache.originalTimeLeft = countdownManager.getOriginTimeInMinutes();
        PomodoroCache.isRunning = countdownManager.isRunning();
        PomodoroCache.progress = countdownManager.getProgress();
        PomodoroCache.progressPart = countdownManager.getProgressPart();

        PomodoroCache.isFirstRun = false;

        countdownManager.stopTimer();
    }

    public void loadData() {

        if (PomodoroCache.isFirstRun) {
            return;
        }

        roundManager.setCurrentRound(PomodoroCache.currentRound);
        roundManager.setCurrentState(PomodoroCache.currentState);
        roundManager.setTotalRounds(PomodoroCache.totalRounds);
    }
}
