package com.example.studyroomsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

// 건물 선택 카드뷰
public class BuildingFragment extends Fragment {
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_building, container, false);
//    }

    // 카드뷰
    private RecyclerView.Adapter mRecycleAdapter;
    private RecyclerView.LayoutManager mRecycleLayoutManager;
    private RecyclerView mRecyclerView;
    private ArrayList<BuildingCardData> mDataset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_building, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecycleLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        mRecyclerView.setLayoutManager(mRecycleLayoutManager);

        mDataset = new ArrayList<>();
        mRecycleAdapter = new BuildingRecyclerAdapter(mDataset);
        mRecyclerView.setAdapter(mRecycleAdapter);

        mDataset.add(new BuildingCardData("#1", R.drawable.a));
        mDataset.add(new BuildingCardData("#2", R.drawable.b));
        mDataset.add(new BuildingCardData("#3", R.drawable.c));
        mDataset.add(new BuildingCardData("#4", R.drawable.d));
        mDataset.add(new BuildingCardData("#5", R.drawable.e));

        mRecyclerView.setAdapter(mRecycleAdapter);

        return view;
    }
}
