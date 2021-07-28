package me.minemis.pomodoro02.managers;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.activities.ChooseTimeActivity;
import me.minemis.pomodoro02.activities.MainActivity;
import me.minemis.pomodoro02.listeners.main.NextButtonListener;
import me.minemis.pomodoro02.listeners.main.ResetButtonMainListener;
import me.minemis.pomodoro02.listeners.main.StartPauseButtonListener;
import me.minemis.pomodoro02.listeners.main.TextTimerListener;

public class ViewManager {

    public static class Main {
        private final MainActivity mainActivity;
        private final TextView textViewTimer;
        private final ImageButton btnStartPause;
        private final ImageButton btnReset;
        private final ImageButton btnNext;
        private final ProgressBar progressBar;
        private TextView text1, text2, text3;
        private final TextView txtCurrentState;
        private final TextView txtWhichRound;
        private final TextView txtTotalRounds;

        public Main(MainActivity mainActivity) {
            this.mainActivity = mainActivity;

            textViewTimer =     mainActivity.findViewById(R.id.txt_timer);
            btnStartPause =     mainActivity.findViewById(R.id.btn_start);
            btnReset =          mainActivity.findViewById(R.id.btn_reset);
            progressBar =       mainActivity.findViewById(R.id.progress_bar);
            btnNext =           mainActivity.findViewById(R.id.btn_next_phase);
            txtCurrentState =   mainActivity.findViewById(R.id.txt_current_state);
            txtWhichRound =     mainActivity.findViewById(R.id.txt_which_round);
            txtTotalRounds =    mainActivity.findViewById(R.id.txt_total_value);

            text1 =             mainActivity.findViewById(R.id.textView);
            text2 =             mainActivity.findViewById(R.id.textView2);
            text3 =             mainActivity.findViewById(R.id.textView3);
        }

        public void assignListeners() {
            btnStartPause       .setOnClickListener(new StartPauseButtonListener(mainActivity));
            btnNext             .setOnClickListener(new NextButtonListener(mainActivity));
            btnReset            .setOnClickListener(new ResetButtonMainListener(mainActivity));
            textViewTimer       .setOnClickListener(new TextTimerListener(mainActivity));
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void setPlayButtonAsArrow() {
            btnStartPause.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_play_arrow));
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void setPlayButtonAsPause() {
            btnStartPause.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_pause));
        }

        public TextView getTextViewTimer() {
            return textViewTimer;
        }

        public ImageButton getBtnStartPause() {
            return btnStartPause;
        }

        public ImageButton getBtnReset() {
            return btnReset;
        }

        public ImageButton getBtnNext() {
            return btnNext;
        }

        public ProgressBar getProgressBar() {
            return progressBar;
        }

        public TextView getText1() {
            return text1;
        }

        public TextView getText2() {
            return text2;
        }

        public TextView getText3() {
            return text3;
        }

        public TextView getTxtCurrentState() {
            return txtCurrentState;
        }

        public TextView getTxtWhichRound() {
            return txtWhichRound;
        }

        public TextView getTxtTotalRounds() {
            return txtTotalRounds;
        }
    }

    public static class ChooseTime {

        private final Slider sliderFocus;
        private final Slider sliderBreak;
        private final Slider sliderLongBreak;
        private final Slider sliderRounds;
        private final TextView txtFocusValue;
        private final TextView txtBreakValue;
        private final TextView txtLongBreakValue;
        private final TextView txtRounds;
        private final Button resetButton;

        public ChooseTime(ChooseTimeActivity chooseTimeActivity) {
            sliderFocus =       chooseTimeActivity.findViewById(R.id.slider_work);
            sliderBreak =       chooseTimeActivity.findViewById(R.id.slider_break);
            sliderLongBreak =   chooseTimeActivity.findViewById(R.id.slider_long_brake);
            sliderRounds =      chooseTimeActivity.findViewById(R.id.slider_rounds);

            txtFocusValue =     chooseTimeActivity.findViewById(R.id.txt_slider_work_value);
            txtBreakValue =     chooseTimeActivity.findViewById(R.id.txt_slider_brake);
            txtLongBreakValue = chooseTimeActivity.findViewById(R.id.txt_slider_long_brake);
            txtRounds =         chooseTimeActivity.findViewById(R.id.txt_slider_rounds);

            resetButton =       chooseTimeActivity.findViewById(R.id.btn_reset_choose_time);
        }

        public Slider getSliderFocus() {
            return sliderFocus;
        }

        public Slider getSliderBreak() {
            return sliderBreak;
        }

        public Slider getSliderLongBreak() {
            return sliderLongBreak;
        }

        public Slider getSliderRounds() {
            return sliderRounds;
        }

        public TextView getTxtFocusValue() {
            return txtFocusValue;
        }

        public TextView getTxtBreakValue() {
            return txtBreakValue;
        }

        public TextView getTxtLongBreakValue() {
            return txtLongBreakValue;
        }

        public TextView getTxtRounds() {
            return txtRounds;
        }

        public Button getResetButton() {
            return resetButton;
        }
    }
}
