package com.example.studyroomsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReservationActivity extends AppCompatActivity {

    public static int StudyRoomPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Intent in = getIntent();
        StudyRoomPosition = in.getIntExtra("StudyRoomPosition",0);

        Button btnReservation = findViewById(R.id.btnreservation);
        btnReservation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(ReservationActivity.this, StudyRoomPosition + "Select", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
