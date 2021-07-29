package me.minemis.pomodoro02.managers;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import java.util.Map;

import me.minemis.pomodoro02.activities.MainActivity;

public class SaveManager {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Map<PomOption, Integer> settings;

    @SuppressLint("CommitPrefEdits")
    public SaveManager(MainActivity mainActivity, SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.settings = mainActivity.getRoundManager().getSettings();
    }

    public void save() {
        settings.forEach(this::putValueToEditor);
        editor.apply();
    }

    public void load() {
        settings.forEach((setting, integer) -> setRMValues(setting));
    }

    private void setRMValues(PomOption pomOption) {
        settings.put(pomOption, sharedPreferences.getInt(pomOption.getId(), pomOption.getDefaultValue()));
    }

    private void putValueToEditor(PomOption pomOption, Integer value) {
        editor.putInt(pomOption.getId(), value);
    }
}
