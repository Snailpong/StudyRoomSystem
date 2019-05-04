package com.example.studyroomsystem;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_user_reservation:
                break;
            case R.id.btn_user_info:
                break;
            case R.id.btn_alarm_message:
                break;
            case R.id.btn_logout:
                mAuth.signOut();
                SharedPreferences pref = getSharedPreferences( "pref" , MODE_PRIVATE);
                SharedPreferences.Editor ed = pref.edit();
                ed.remove("id");
                ed.remove("pw");
                ed.commit();
                break;
            case R.id.btn_getout:
                break;
        }
    }
}
