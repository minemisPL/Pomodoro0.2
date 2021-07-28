package me.minemis.pomodoro02.managers;

import java.util.function.Supplier;

import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.activities.MainActivity;

public enum PomOption {
    FOCUS(25, "focus", () -> MainActivity.getInstance().getResources().getString(R.string.focus)),
    SHORT_BREAK(5, "short_brake", () -> MainActivity.getInstance().getResources().getString(R.string.short_brake)),
    LONG_BREAK(10, "long_brake", () -> MainActivity.getInstance().getResources().getString(R.string.long_brake)),
    ROUNDS(4, "rounds",() -> MainActivity.getInstance().getResources().getString(R.string.rounds));

    private final int defaultValue;
    private final Supplier<String> supplier;
    private final String id;

    PomOption(int defaultValue, String id, Supplier<String> supplier) {
        this.defaultValue = defaultValue;
        this.supplier = supplier;
        this.id = id;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public String getStringValue() {
        return supplier.get();
    }

    public String getId() {
        return id;
    }
}
