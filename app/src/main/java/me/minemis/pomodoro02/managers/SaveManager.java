package me.minemis.pomodoro02.managers;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import java.util.Map;

import me.minemis.pomodoro02.PomOption;
import me.minemis.pomodoro02.activities.MainActivity;

public class SaveManager {
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;
    private final Map<PomOption, Integer> settings;

    @SuppressLint("CommitPrefEdits")
    public SaveManager(MainActivity mainActivity, SharedPreferences preferences) {
        this.preferences = preferences;
        this.editor = preferences.edit();
        this.settings = mainActivity.getRoundManager().getSettings();
    }

    public void save() {
        settings.forEach(this::setData);
        editor.apply();
    }

    public void load() {
        settings.forEach((option, data) -> settings.put(option, preferences.getInt(option.getId(), option.getDefaultValue())));
    }

    private void setData(PomOption option, Integer data) {
        editor.putInt(option.getId(), data);
    }
}
