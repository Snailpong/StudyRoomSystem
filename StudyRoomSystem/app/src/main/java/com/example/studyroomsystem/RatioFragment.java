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

// 서비스2 : 건물별 전체 예약률 제공
public class RatioFragment extends Fragment {
    private FirebaseAuth firebaseAuth;

    private TextView textViewUserName;
    private TextView textViewUserSchoolid;
    private DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("users");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_ratio, container, false);
        return view;
    }
}