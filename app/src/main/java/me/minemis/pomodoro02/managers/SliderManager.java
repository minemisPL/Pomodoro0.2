package me.minemis.pomodoro02.managers;

import android.widget.TextView;

import com.google.android.material.slider.Slider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SliderManager {
    private final Map<PomOption, Slider> sliderMap = new HashMap<>();
    private final Map<PomOption, TextView> textMap = new HashMap<>();

    public void addSlider(PomOption pomOption, Slider slider, TextView textView) {
        sliderMap.put(pomOption, slider);
        textMap.put(pomOption, textView);
    }

    public Optional<Slider> getSlider(PomOption pomOption) {
        return Optional.ofNullable(sliderMap.get(pomOption));
    }

    public void setSliderValue(PomOption pomOption, int value) {
        Slider slider = sliderMap.get(pomOption);
        TextView textView = textMap.get(pomOption);

        if (slider == null || textView == null) {
            return;
        }

        slider.setValue(value);
        textView.setText(value);
    }

    public void reset() {
        sliderMap.forEach((key, value) -> setSliderValue(key, key.getDefaultValue()));
    }
}
