package com.example.administrator.firebasearchitectecturecomponents;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";
    TextView tvName;

    private final DatabaseReference ref =
            FirebaseDatabase.getInstance().getReference("/UserDetails");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.tv_name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(listner);
    }

    @Override
    protected void onStop() {
        ref.removeEventListener(listner);
        super.onStop();
    }

    private ValueEventListener listner = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String name = dataSnapshot.child("name").getValue(String.class);
            tvName.setText(name);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.d(LOG_TAG, databaseError.getDetails());
        }
    };
}
