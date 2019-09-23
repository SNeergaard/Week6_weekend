package com.example.week6_weekend.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.week6_weekend.R;
import com.example.week6_weekend.model.ChronometerHelper;
import com.example.week6_weekend.view.activity.CounterActivity;

import java.util.Timer;
import java.util.TimerTask;


public class CounterFragment extends Fragment {
    Timer timer;
    TimerTask timerTask;

    final Handler handler = new Handler();
    int starttime = 0;

    TextView tvCounter;

    private static CounterFragment instance = null;

    public CounterFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    public static CounterFragment getInstance(){
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_counter, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCounter = view.findViewById(R.id.tvCounter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void startTimer(){
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 1000, 1000);
    }

    public void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    public  void initializeTimerTask(){
        final long begin = System.currentTimeMillis();
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        long millis = System.currentTimeMillis() - begin - starttime;
                        int seconds = (int) (millis/1000);
                        int minutes = seconds / 60;
                        seconds = seconds % 60;
                        tvCounter.setText(String.format("%d:%02d", minutes, seconds));
                    }
                });
            }
        };
    }

}


