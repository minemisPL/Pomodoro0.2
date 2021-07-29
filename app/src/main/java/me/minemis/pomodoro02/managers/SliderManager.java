package me.minemis.pomodoro02.managers;

import android.widget.TextView;

import com.google.android.material.slider.Slider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import me.minemis.pomodoro02.activities.ChooseTimeActivity;
import me.minemis.pomodoro02.listeners.choosetime.SliderListener;

public class SliderManager {
    private final Map<PomOption, Slider> sliders = new HashMap<>();
    private final Map<PomOption, TextView> texts = new HashMap<>();
    private final Map<PomOption, Integer> originValues = new HashMap<>();

    public void addSlider(PomOption pomOption, Slider slider, TextView textView) {
        sliders.put(pomOption, slider);
        texts.put(pomOption, textView);
        originValues.put(pomOption, pomOption.getDefaultValue());
    }

    public void setSliderValue(PomOption pomOption, int value) {
        Slider slider = sliders.get(pomOption);
        TextView textView = texts.get(pomOption);

        if (slider == null || textView == null) {
            return;
        }

        slider.setValue(value);
        textView.setText(String.valueOf(value));
    }

    public void setSliderValue(Slider slider, int value) {
        for (Map.Entry<PomOption, Slider> entry : sliders.entrySet()) {
            if (entry.getValue().equals(slider)) {
                slider.setValue(value);
                TextView sliderText = texts.get(entry.getKey());

                if (sliderText == null) {
                    return;
                }
                sliderText.setText(String.valueOf(value));
                return;
            }
        }
    }

    public void assignSliderValue(PomOption pomOption, int value) {
        Slider slider = sliders.get(pomOption);
        TextView textView = texts.get(pomOption);

        if (slider == null || textView == null) {
            return;
        }

        slider.setValue(value);
        textView.setText(String.valueOf(value));
        originValues.put(pomOption, value);
    }

    public void setListeners() {
        sliders.forEach(((pomOption, slider) -> slider.addOnChangeListener(new SliderListener(ChooseTimeActivity.getInstance()))));
    }

    public void reset() {
        sliders.forEach((pomOption, slider) -> setSliderValue(pomOption, pomOption.getDefaultValue()));
    }

    public boolean checkIfChanged(PomOption currentState) {
        Optional<Slider> slider = this.getSlider(currentState);
        Optional<Integer> originalValue = this.getOriginValue(currentState);

        if ( !slider.isPresent() || !originalValue.isPresent()) {
            return false;
        }

        return originalValue.get() != slider.get().getValue();
    }

    public Optional<Integer> getOriginValue(PomOption pomOption) {
        return Optional.ofNullable(originValues.get(pomOption));
    }

    public Optional<Slider> getSlider(PomOption pomOption) {
        return Optional.ofNullable(sliders.get(pomOption));
    }

    public Optional<PomOption> getPomOptionFromSlider(Slider slider) {
        for (Map.Entry<PomOption, Slider> entry : sliders.entrySet()) {
            if (entry.getValue().equals(slider)) {
                return Optional.ofNullable(entry.getKey());
            }
        }
        return Optional.empty();
    }
}
