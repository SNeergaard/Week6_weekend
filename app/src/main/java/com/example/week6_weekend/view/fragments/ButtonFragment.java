package com.example.week6_weekend.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.week6_weekend.R;

public class ButtonFragment extends Fragment {
    FragmentManager fm = getFragmentManager();
    Button btnStart;
    Button btnStop;

    public ButtonFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_button, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnStart).setOnClickListener(mListener);
        view.findViewById(R.id.btnStop).setOnClickListener(mListener);
    }

    private final View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnStart:
                    CounterFragment.getInstance().startTimer();
                case R.id.btnStop:
                    CounterFragment fragv = (CounterFragment) fm.findFragmentById(R.id.fgCounter);
                    fragv.stopTimer();
            }
        }
    };
}