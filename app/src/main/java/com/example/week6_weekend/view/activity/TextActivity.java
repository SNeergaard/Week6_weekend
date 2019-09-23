package com.example.week6_weekend.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week6_weekend.R;

public class TextActivity extends AppCompatActivity {
    public static final int PERMISSIONS = 0;

    private EditText etPhoneNumber;
    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etMessage = findViewById(R.id.etMessage);
    }

    public void SendMessage(View view) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS);
        try {
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(etPhoneNumber.getText().toString(), null, etMessage.getText().toString(), null, null);
            Toast.makeText(TextActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(TextActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
    }
}

