package com.example.studyroomsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

// 강의실 개수(INT 값)의 배열을 하나 두고
// 인텐트로 넘어오는 Building position값으로 해당 강의실 개수 만큼 카드뷰 출력
public class StudyRoomActivity extends AppCompatActivity {

    private RecyclerView.Adapter mRecycleAdapter;
    private RecyclerView.LayoutManager mRecycleLayoutManager;
    private RecyclerView mRecyclerView;
    private ArrayList<StudyRoomCardData> mDataset;

    public static int BuildingPosition;
    public static int StartPosition = 0;

    static int[] StudyRoomCount = new int[]{
            // building1
            5,
            // building2
            5,
            // building3
            5,
            // building4
            5,
            // building5
            5
    };
    static String[] BuildingName = new String[]{
            // building1
            "컴공관",
            // building2
            "기계관",
            // building3
            "항공관",
            // building4
            "재료관",
            // building5
            "건설관"
    };
    static int[] StudyRoomImgset = new int[]{
            // building1
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,

            // building2
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.a,

            // building3
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.a,
            R.drawable.b,

            // building4
            R.drawable.d,
            R.drawable.e,
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,

            // building5
            R.drawable.e,
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyroom);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecycleLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mRecycleLayoutManager);

        mDataset = new ArrayList<>();
        mRecycleAdapter = new StudyRoomRecyclerAdapter(mDataset);
        mRecyclerView.setAdapter(mRecycleAdapter);

        Intent in = getIntent();
        BuildingPosition = in.getIntExtra("BuildingPosition",0);

        // 이미지 시작위치 구하기
        for(int studyRoomCounter = 0; studyRoomCounter < StudyRoomCount[BuildingPosition]; studyRoomCounter++){
            StartPosition += StudyRoomCount[BuildingPosition];
        }
        // 카드뷰 추가
        for(int studyRoomCounter = 0; studyRoomCounter < StudyRoomCount[BuildingPosition]; studyRoomCounter++){
            mDataset.add(new StudyRoomCardData(BuildingName[BuildingPosition], StudyRoomImgset[StartPosition + studyRoomCounter]));
        }

        mRecyclerView.setAdapter(mRecycleAdapter);
    }
}