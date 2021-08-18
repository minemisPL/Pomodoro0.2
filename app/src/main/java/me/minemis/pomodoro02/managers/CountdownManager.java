package me.minemis.pomodoro02.managers;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import java.util.Locale;
import java.util.Optional;

import me.minemis.pomodoro02.App;
import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.activities.MainActivity;

public class CountdownManager {
    private final App app;
    private final PomNotificationManager pomNotificationManager;
    private ViewManager.Main mainViewManager;
    private TextView txtTimer;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private long originTimeInMinutes;
    private long timeLeftInMillis;
    private double progressPart;
    private double progress;
    private boolean isRunning = false;
    private boolean canPass = true;

    public CountdownManager() {
        this.app = App.getInstance();
        this.pomNotificationManager = app.getPomNotificationManager();
        assignViews();
    }

    private void assignViews() {
        this.mainViewManager = MainActivity.getInstance().getMainViewManager();

        this.txtTimer = mainViewManager.getTextViewTimer();
        this.progressBar = mainViewManager.getProgressBar();
    }

    public void newCountdown(long timeInMinutes, boolean resetProgress) {

        this.assignViews();
        this.timeLeftInMillis = timeInMinutes * 60 * 1000;

        if (!this.isRunning) {
            this.updateCountdownText();
        } else {
            this.mainViewManager.setPlayButtonAsPause();
        }

        this.originTimeInMinutes = timeInMinutes;

        if (resetProgress) {
            this.progressPart = 10000 / ((double) originTimeInMinutes * 60);

            this.progressBar.setProgress(10000);
            this.progress = 10000;
        }
    }

    private void newCountdownTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
                progress = progress - progressPart;

                progressBar.setProgress((int) progress);
            }

            @Override
            public void onFinish() {
                app.getRoundManager().nextRound();
                startTimer();
            }
        };
    }

    public void startTimer() {

        updateCountdownText();

        if (!canPass) {
            return;
        }

        canPass = false;
        isRunning = true;

        new Handler().postDelayed(() -> {
            updateCountdownText();
            newCountdownTimer();
            countDownTimer.start();
            mainViewManager.setPlayButtonAsPause();
            canPass = true;
        }, 1000);
    }

    public void stopTimer() {
        if (countDownTimer == null) {
            return;
        }

        countDownTimer.cancel();
        isRunning = false;
        this.mainViewManager.setPlayButtonAsArrow();
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        this.txtTimer.setText(timeLeftFormatted);
        this.pomNotificationManager.setTimerText(timeLeftFormatted);
        this.pomNotificationManager.notifyTimer();
    }

    public void reset() {
        this.stopTimer();
        this.newCountdown(this.originTimeInMinutes, true);
        this.isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
