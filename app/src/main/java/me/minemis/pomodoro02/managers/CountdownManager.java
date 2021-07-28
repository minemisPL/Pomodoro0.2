package me.minemis.pomodoro02.managers;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Locale;

import me.minemis.pomodoro02.activities.MainActivity;

public class CountdownManager {
    private final MainActivity mainActivity;
    private final TextView txtTimer;
    private final ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private long originTimeInMinutes;
    private double progressPart;
    private double progress;
    private boolean isRunning = false;

    public CountdownManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        ViewManager.Main mainViewManager = mainActivity.getMainViewManager();
        this.txtTimer = mainViewManager.getTextViewTimer();
        this.progressBar = mainViewManager.getProgressBar();
        this.newCountdown(PomOption.FOCUS.getDefaultValue());

    }

    public void newCountdown(long timeInMinutes) {
        updateCountdownText(timeInMinutes * 60 * 1000);

        this.originTimeInMinutes = timeInMinutes;

        this.progressPart = 10000 / ((double) originTimeInMinutes * 60);

        this.progressBar.setProgress(10000);

        countDownTimer = new CountDownTimer(originTimeInMinutes * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                updateCountdownText(millisUntilFinished);
                progress = progress - progressPart;
                progressBar.setProgress((int) progress);
            }

            @Override
            public void onFinish() {
                //mainActivity.getRoundManager().nextRound();
            }
        };
    }

    public void startTimer() {
        isRunning = true;
        new Handler().postDelayed(() -> {
            countDownTimer.start();
        }, 1000);
    }

    public void stopTimer() {
        countDownTimer.cancel();
        isRunning = false;
    }

    private void updateCountdownText(long timeLeftInMillis) {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtTimer.setText(timeLeftFormatted);
    }

    public void reset() {
        newCountdown(this.originTimeInMinutes);
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
