package me.minemis.pomodoro02.managers;

import android.widget.TextView;

import com.google.android.material.slider.Slider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import me.minemis.pomodoro02.activities.ChooseTimeActivity;
import me.minemis.pomodoro02.listeners.choosetime.SliderListener;

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

    public Optional<PomOption> getPomOptionFromSlider(Slider slider) {
        for (Map.Entry<PomOption, Slider> entry : sliderMap.entrySet()) {
            if (entry.getValue().equals(slider)) {
                return Optional.ofNullable(entry.getKey());
            }
        }
        return Optional.empty();
    }

    public void setSliderValue(PomOption pomOption, int value) {
        Slider slider = sliderMap.get(pomOption);
        TextView textView = textMap.get(pomOption);

        if (slider == null || textView == null) {
            return;
        }

        slider.setValue(value);
        textView.setText(String.valueOf(value));
    }

    public void setSliderValue(Slider slider, int value) {
        for (Map.Entry<PomOption, Slider> entry : sliderMap.entrySet()) {
            if (entry.getValue().equals(slider)) {
                slider.setValue(value);
                TextView sliderText = textMap.get(entry.getKey());

                if (sliderText == null) {
                    return;
                }
                sliderText.setText(String.valueOf(value));
                return;
            }
        }
    }

    public void setListeners() {
        sliderMap.forEach(((pomOption, slider) -> slider.addOnChangeListener(new SliderListener(ChooseTimeActivity.getInstance()))));
    }

    public void reset() {
        sliderMap.forEach((key, value) -> setSliderValue(key, key.getDefaultValue()));
    }
}
