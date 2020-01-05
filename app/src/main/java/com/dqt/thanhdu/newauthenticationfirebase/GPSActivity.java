package com.dqt.thanhdu.newauthenticationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GPSActivity extends AppCompatActivity {
    EditText editLat,editLong,editGPS;
    Button getGPSbtn;
    String Long,Lat;
    private DatabaseReference data,dataLat,dataLong;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        editLat = (EditText) findViewById(R.id.editLat);
        editLong = (EditText) findViewById(R.id.editLong);
        editGPS = (EditText) findViewById(R.id.editGPS);
        getGPSbtn = (Button) findViewById(R.id.gps_btn);
        database = FirebaseDatabase.getInstance();
        data = database.getReference("new-authentication-firebase");
        dataLat = data.child("Location").child("Latitude");
        dataLong = data.child("Location").child("Longtitude");
        dataLat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Lat = dataSnapshot.getValue(String.class);
                editLat.setText(Lat);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        dataLong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long = dataSnapshot.getValue(String.class);
                editLong.setText(Long);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        getGPSbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gpsLink = "https://maps.google.com/?q="+Lat+","+Long;
                editGPS.setText(Html.fromHtml(gpsLink));
                editGPS.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }
}
