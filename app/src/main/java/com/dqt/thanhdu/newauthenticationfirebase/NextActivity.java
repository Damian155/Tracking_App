package com.dqt.thanhdu.newauthenticationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NextActivity extends AppCompatActivity {
    LinearLayout gpsLay,buzLay;
    ImageButton logout_btn;
    private static final String PREFS_NAME = "PresFile";
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference();
//    Double lat = 0.0;
//    Double log = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

//        myRef.child("Location").child("Latitude").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                lat = dataSnapshot.getValue(Double.class);
//                Log.d("BBB", lat + "");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        myRef.child("Location").child("Longtitude").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                log = dataSnapshot.getValue(Double.class);
//                Log.d("BBB", log + "");
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        buzLay = (LinearLayout) findViewById(R.id.buzLay);
        gpsLay = (LinearLayout) findViewById(R.id.gpsLay);
        logout_btn = (ImageButton) findViewById(R.id.logout_btn);
        buzLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextActivity.this, DeviceList.class);
                startActivity(intent);
            }
        });
        gpsLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(NextActivity.this, Maps.class);
                //Intent intent1 = new Intent(NextActivity.this, GPSActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putDouble("LAT", lat);
//                bundle.putDouble("LONG", log);
//                intent1.putExtra("dulieu", bundle);
                startActivity(intent1);
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NextActivity.this);
                builder.setMessage("Are you sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent2 = new Intent(NextActivity.this,MainActivity.class);
                                startActivity(intent2);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
}
