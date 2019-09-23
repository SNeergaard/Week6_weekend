package com.example.week6_weekend.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.week6_weekend.R;
import com.example.week6_weekend.view.fragments.ButtonFragment;
import com.example.week6_weekend.view.fragments.CounterFragment;

public class CounterActivity extends AppCompatActivity{
    CounterFragment counterFragment;
    ButtonFragment buttonFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        fragmentManager = getSupportFragmentManager();
        counterFragment = (CounterFragment)fragmentManager.findFragmentById(R.id.fgCounter);
//        buttonFragment = (ButtonFragment)fragmentManager.findFragmentById(R.id.fgButtons);
    }

    public void onSendData(String dataToSendToActivity) {
        Log.d("TAG", dataToSendToActivity);
    }
}
