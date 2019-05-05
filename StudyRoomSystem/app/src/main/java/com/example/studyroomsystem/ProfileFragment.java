package com.example.studyroomsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

// 서비스3 : 개인정보 확인, 수정, 회원가입 탈퇴
public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userId = user.getUid();
        DatabaseReference myRef;
        DataSnapshot dataSnapshot;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users").child(userId);
        textViewUserName = (TextView) view.findViewById(R.id.textViewUserName);
        textViewUserSchoolid = (TextView) view.findViewById(R.id.textViewUserSchoolid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()) {
                    if (item.getKey().equals("name"))
                        name = item.getValue(String.class);

                    if (item.getKey().equals("schoolid"))
                        schoolid = item.getValue(String.class);
                }
                textViewUserName.setText("이름: "+name);
                textViewUserSchoolid.setText("학번: "+schoolid);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                throw error.toException();
            }
        });
        Button btnModify = (Button) view.findViewById(R.id.btnModify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ModifyPI.class);
                startActivity(in);
            }
        });
        return view;
    }
}
