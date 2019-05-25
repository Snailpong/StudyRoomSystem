package com.example.studyroomsystem;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// 서비스2 : 건물별 전체 예약률 제공
public class RatioFragment extends Fragment {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    String lec;

    private DatabaseReference databaseReference, myRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_ratio, container, false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        final Button away = (Button)view.findViewById(R.id.btnAway);
        away.setVisibility(View.GONE);
        final TextView text = view.findViewById(R.id.textCurrentLec);
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("reservation").getValue(String.class) == null) {
                    text.setText("현재 예약중인 강의실이 없습니다.");
                } else {
                    lec = dataSnapshot.child("reservation").getValue(String.class);
                    text.setText("현재 예약 강의실 : " + lec);
                    away.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] nameArray = lec.split("#");
                FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).
                        child("reservation").setValue(null);
                away.setVisibility(View.GONE);
                myRef = FirebaseDatabase.getInstance().getReference("building").
                        child(nameArray[0]).child("Class" + nameArray[1]).child("capacity"); // 건물명, 강의실명
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        FirebaseDatabase.getInstance().getReference("building").
                                child(nameArray[0]).child("Class" + nameArray[1]).child("capacity")
                                .setValue(dataSnapshot.getValue(Integer.class)+1);
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        throw error.toException();
                    }
                });
            }
        });
        return view;
    }
}