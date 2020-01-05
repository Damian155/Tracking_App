package com.dqt.thanhdu.newauthenticationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NextActivity extends AppCompatActivity {
    Button blue_buzz_btn,wifi_gps_btn,logout_btn;
    private static final String PREFS_NAME = "PresFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        blue_buzz_btn = (Button) findViewById(R.id.buzzer_btn);
        wifi_gps_btn = (Button) findViewById(R.id.gps_btn);
        logout_btn = (Button) findViewById(R.id.logout_btn);
        blue_buzz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextActivity.this,DeviceList.class);
                startActivity(intent);
            }
        });
        wifi_gps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(NextActivity.this, GPSActivity.class);
                startActivity(intent1);
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                preferences.edit().clear().apply();
                finish();
            }
        });
    }
}
