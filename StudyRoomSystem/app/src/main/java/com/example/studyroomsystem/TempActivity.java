package com.example.studyroomsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        Button btnPersonal_info = (Button)findViewById(R.id.btnPersonal_info);
        btnPersonal_info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent in = new Intent(TempActivity.this, Personal_Info.class);
                startActivity(in);
            }
        });
    }
}
