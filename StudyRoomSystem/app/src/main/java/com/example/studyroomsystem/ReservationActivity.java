package com.example.studyroomsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ReservationActivity extends AppCompatActivity {

    public static String RoomName;
    public static String[] NameArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Intent in = getIntent();
        RoomName = in.getStringExtra("RoomName");
        NameArray = RoomName.split("#");

        TextView BuildingName = findViewById(R.id.buildingname);
        BuildingName.setText(NameArray[0]);

        TextView StudyRoomName = findViewById(R.id.studyroomname);
        StudyRoomName.setText(NameArray[1]);

        Button btnReservation = findViewById(R.id.btnreservation);
        btnReservation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(ReservationActivity.this, "예약 완료", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
