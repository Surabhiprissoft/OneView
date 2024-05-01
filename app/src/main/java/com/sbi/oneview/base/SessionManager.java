package com.sbi.oneview.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.Timer;
import java.util.TimerTask;

public class SessionManager {
    private static final long SESSION_TIMEOUT = 15 * 60 * 1000; // 15 minutes in milliseconds
    private static SessionManager instance;
    private long lastInteractionTime;
    private boolean isTimerInitialized = false;
    private Timer sessionTimer;
    private SessionExpiredListener sessionExpiredListener;

    public interface SessionExpiredListener {
        void onSessionExpired();
    }

    private SessionManager() {
        lastInteractionTime = System.currentTimeMillis();
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void initializeTimer(Context context) {
        if (!isTimerInitialized) {
            isTimerInitialized = true;
            sessionTimer = new Timer();
            sessionTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    checkAndNotifySessionExpired();
                }
            }, SESSION_TIMEOUT, SESSION_TIMEOUT);
        }
    }

    public void onUserInteraction() {
        lastInteractionTime = System.currentTimeMillis();
        checkAndNotifySessionExpired();
    }

    public void setSessionExpiredListener(SessionExpiredListener listener) {
        sessionExpiredListener = listener;
    }

    private void checkAndNotifySessionExpired() {
        if (isSessionExpired() && sessionExpiredListener != null) {
            notifySessionExpired();
        }
    }

    public boolean isSessionExpired() {
        return System.currentTimeMillis() - lastInteractionTime > SESSION_TIMEOUT;
    }

    private void notifySessionExpired() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (sessionExpiredListener != null) {
                    sessionExpiredListener.onSessionExpired();
                }
            }
        });
    }
}
