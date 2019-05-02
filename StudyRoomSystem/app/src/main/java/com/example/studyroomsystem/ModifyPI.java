package com.example.studyroomsystem;

import android.media.JetPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

public class ModifyPI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pi);
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(userId);



        Button btnModifyPI = (Button) findViewById(R.id.btnfinalmodify);
        btnModifyPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etModifyName = (EditText) findViewById(R.id.etmodify_name);
                EditText etModifySchoolnum = (EditText) findViewById(R.id.etmodify_schoolnum);
                EditText etModifyPasswd = (EditText) findViewById(R.id.etmodify_password);
                EditText etModifyRepasswd = (EditText) findViewById(R.id.etmodify_repassword);

                if (etModifyPasswd.getText().toString().equals(etModifyRepasswd.getText().toString())){
                    Hashtable<String, Object> profile = new Hashtable<String, Object>();
                    if (!etModifyName.getText().toString().isEmpty()) {
                        profile.put("name",etModifyName.getText().toString());
                    }
                    if (!etModifySchoolnum.getText().toString().isEmpty()) {
                        profile.put("schoolid",etModifySchoolnum.getText().toString());
                    }
                    mDatabase.updateChildren(profile);
                    finish();
                }
                else {
                    Toast.makeText(ModifyPI.this, "비밀번호를 다시 확인해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnModifyCancel = (Button) findViewById(R.id.btnModifycancel);
        btnModifyCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
