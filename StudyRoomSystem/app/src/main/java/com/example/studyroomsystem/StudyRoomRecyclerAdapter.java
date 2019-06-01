package com.example.studyroomsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


class StudyRoomCardData {
    public String text;
    public int img;

    public StudyRoomCardData(String text, int img) {
        this.text = text;
        this.img = img;
    }
}

public class StudyRoomRecyclerAdapter extends RecyclerView.Adapter<StudyRoomRecyclerAdapter.ViewHolder> {
    private ArrayList<StudyRoomCardData> mDataset;
    int curr, capa;
    private FirebaseAuth firebaseAuth;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageCard;
        public TextView textCard;

        public ViewHolder(View v) {
            super(v);
            imageCard = (ImageView) v.findViewById(R.id.imageCard);
            textCard = (TextView) v.findViewById(R.id.textCard);
        }
    }

    public StudyRoomRecyclerAdapter(ArrayList<StudyRoomCardData> dataset) {
        mDataset = dataset;
    }

    @Override
    public StudyRoomRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.imageCard.setImageResource(mDataset.get(position).img);
        final String text = mDataset.get(position).text;
        String[] NameArray = text.split("#");
        holder.textCard.setText(text);


        DatabaseReference myRef;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("building").child(NameArray[0]).child("Class"+NameArray[1]);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                capa = dataSnapshot.child("capacity").getValue(Integer.class);
                curr = dataSnapshot.child("current").getValue(Integer.class);
                int count =  capa - curr;
                holder.textCard.setText(text + "                          예약 가능한 자리수 " + String.valueOf(count));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


        holder.imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String userId = user.getUid();
                DatabaseReference myRef;

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                myRef = database.getReference("users").child(userId);

                final Context context = v.getContext();
                final Intent in = new Intent(context, ReservationActivity.class);
                in.putExtra("RoomName", mDataset.get(position).text);

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("reservation").getValue(String.class) == null) {
                            context.startActivity(in);
                            ((Activity)context).finish();
                        }
                        else {
                            Toast.makeText(context, "예약이 이미 존재합니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}