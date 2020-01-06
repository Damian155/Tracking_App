package com.dqt.thanhdu.newauthenticationfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class GPSActivity extends AppCompatActivity {
    double mLat, mLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("dulieu");
        mLat = bundle.getDouble("LAT");
        mLong = bundle.getDouble("LONG");
        Log.d("CCC", mLat + "");
        Log.d("CCC", mLong + "");
    }
}
