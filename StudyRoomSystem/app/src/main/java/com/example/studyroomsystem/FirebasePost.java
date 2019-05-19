package com.example.studyroomsystem;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FirebasePost {
    public String building;
    public String classNumber;
    public Long capacity;
    public Long current;

    public FirebasePost(){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public FirebasePost(String building, String classNumber, Long capacity, Long current) {
        this.building = building;
        this.classNumber = classNumber;
        this.capacity = capacity;
        this.current = current;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("building", building);
        result.put("classNumber", classNumber);
        result.put("capacity", capacity);
        result.put("current", current);
        return result;
    }
}