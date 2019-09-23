package com.example.week6_weekend.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.week6_weekend.R;
import com.example.week6_weekend.view.activity.MainActivity;
import com.example.week6_weekend.view.activity.SplashActivity;

public class BlankFragment extends DialogFragment {

    private ImageView ivFrag;

    public static BlankFragment newInstance(String title) {
        BlankFragment frag = new BlankFragment();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        ivFrag = view.findViewById(R.id.ivFragment);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 3000);

        return view;
    }
}
