package com.example.covid24.view.ui.details_screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;


import androidx.annotation.NonNull;

import com.example.covid24.R;
import com.example.covid24.view.callback.OnCalendarDateChangeListener;

import java.util.Date;

public class MyCustomCalendar extends Dialog implements View.OnClickListener {

    private CalendarView calendarView;
    private Button BTN_OK, BTN_CANCEL;
    private OnCalendarDateChangeListener callBack;
    private String date;


    MyCustomCalendar(@NonNull Activity activity, OnCalendarDateChangeListener callBack) {
        super(activity);
        this.callBack = callBack;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        BTN_OK = findViewById(R.id.BTN_OK);
        BTN_OK.setOnClickListener(this);
        BTN_CANCEL = findViewById(R.id.BTN_CANCEL);
        BTN_CANCEL.setOnClickListener(this);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setMaxDate(new Date().getTime());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                MyCustomCalendar.this.date = year + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BTN_OK:
                callBack.OnDateChange(date);
                break;
            case R.id.BTN_CANCEL:
                break;
        }
        dismiss();
    }
}
