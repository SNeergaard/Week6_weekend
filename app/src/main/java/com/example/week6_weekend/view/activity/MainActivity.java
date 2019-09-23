package com.example.week6_weekend.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.week6_weekend.R;
import com.example.week6_weekend.view.fragments.BlankFragment;
import com.pdftron.pdf.Date;
import com.pdftron.pdf.controls.DocumentActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String ID = "september";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar cal = Calendar.getInstance();
        if(cal.get(Calendar.DAY_OF_MONTH) == 22 && cal.get(Calendar.MONTH) == Calendar.SEPTEMBER){
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, ID)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("do you remember?")
                    .setContentText("the 21st night of september")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            Intent sepIntent = new Intent(this, sepActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(sepActivity.class);
            stackBuilder.addNextIntent(sepIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);

           NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
           manager.notify(0, mBuilder.build());
        }
    }

    public void LaunchPDF(View view) {
//        Intent pdfintent = new Intent(this, PdfActivity.class);
//        startActivity(pdfintent);
        DocumentActivity.openDocument(this, R.raw.millenial_apartment_hunters);
    }

    public void LaunchAlertDefault(View view) {
        new AlertDialog.Builder(view.getContext())
                .setTitle("Close Dialog")
                .setMessage("Are you sure you want to delete this dialog?")

                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("also yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void LaunchAlertCustom(View view) {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.custom);
        dialog.setTitle("custom view");

        TextView tvCustom = (TextView) dialog.findViewById(R.id.tvcustom);
        tvCustom.setText("custom dialog!");

        Button btnCustom = (Button) dialog.findViewById(R.id.btncustom);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    String[] list = {"red", "blue", "yellow"};

    public void LaunchAlertList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("List")
                .setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                dialog.dismiss();
                            case 1:
                                dialog.dismiss();
                            case 2:
                                dialog.dismiss();
                        }
                    }
                });
    }

    public void LaunchAlertFrag(View view) {
        FragmentManager fm = getSupportFragmentManager();
        BlankFragment dialog = BlankFragment.newInstance("dialog");
        dialog.show(fm, "fragment");
    }

    public void LaunchText(View view) {
        Intent phoneIntent = new Intent(MainActivity.this,TextActivity.class);
        MainActivity.this.startActivity(phoneIntent);
        MainActivity.this.finish();
    }

    public void LaunchNotification(View view) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("do you remember?")
                .setContentText("the 21st night of september");
        Intent sepIntent = new Intent(this, sepActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(sepActivity.class);
        stackBuilder.addNextIntent(sepIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, mBuilder.build());
    }

    public void LaunchCounter(View view) {
        Intent counterIntent = new Intent(MainActivity.this,CounterActivity.class);
        MainActivity.this.startActivity(counterIntent);
        MainActivity.this.finish();
    }
}
