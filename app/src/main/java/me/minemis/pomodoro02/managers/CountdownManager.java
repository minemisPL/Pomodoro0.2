package me.minemis.pomodoro02.managers;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import java.util.Locale;
import java.util.Optional;

import me.minemis.pomodoro02.PomodoroCache;
import me.minemis.pomodoro02.R;
import me.minemis.pomodoro02.activities.MainActivity;

public class CountdownManager {
    private final MainActivity mainActivity;
    private final TextView txtTimer; //
    private final ProgressBar progressBar; //
    private CountDownTimer countDownTimer;
    private final PomNotificationManager pomNotificationManager;
    private long originTimeInMinutes;
    private long timeLeftInMillis;
    private double progressPart;
    private double progress;
    private boolean isRunning = false;
    private boolean canPass = true;

    public CountdownManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.pomNotificationManager = mainActivity.getPomNotificationManager();
        ViewManager.Main mainViewManager = mainActivity.getMainViewManager();
        this.txtTimer = mainViewManager.getTextViewTimer();
        this.progressBar = mainViewManager.getProgressBar();
    }

    public void newCountdown(long timeInMinutes) {
        this.timeLeftInMillis = timeInMinutes * 60 * 1000;

        updateCountdownText();

        this.originTimeInMinutes = timeInMinutes;

        this.progressPart = 10000 / ((double) originTimeInMinutes * 60);

        this.progressBar.setProgress(10000);
        this.progress = 10000;
    }

    public void continueCountDown() {
        this.timeLeftInMillis = PomodoroCache.timeLeftInMillis;

        updateCountdownText();

        this.originTimeInMinutes = PomodoroCache.originalTimeLeft * 60 * 1000;
        this.progressPart = PomodoroCache.progressPart;

        this.progress = PomodoroCache.progress;

        this.progressBar.setProgress((int) progress);

        if (PomodoroCache.isRunning) {
            this.startTimerContinue();
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
                mainActivity.getRoundManager().nextRound();
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
            canPass = true;
        }, 1000);
    }

    public void startTimerContinue() {
        updateCountdownText();

        isRunning = true;
        newCountdownTimer();
        countDownTimer.start();
    }

    public void stopTimer() {
        if (countDownTimer == null) {
            return;
        }

        countDownTimer.cancel();
        isRunning = false;
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        this.txtTimer.setText(timeLeftFormatted);
        pomNotificationManager.setTimerText(timeLeftFormatted);
        pomNotificationManager.notifyTimer();
    }

    public void reset() {
        newCountdown(this.originTimeInMinutes);
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public long getTimeLeftInMillis() {
        return timeLeftInMillis;
    }

    public double getProgress() {
        return progress;
    }

    public long getOriginTimeInMinutes() {
        return originTimeInMinutes;
    }

    public double getProgressPart() {
        return progressPart;
    }

    public void setOriginTimeInMinutes(long originTimeInMinutes) {
        this.originTimeInMinutes = originTimeInMinutes;
    }

    public void setTimeLeftInMillis(long timeLeftInMillis) {
        this.timeLeftInMillis = timeLeftInMillis;
    }

    public void setProgressPart(double progressPart) {
        this.progressPart = progressPart;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
