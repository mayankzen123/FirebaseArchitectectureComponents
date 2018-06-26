package com.example.administrator.firebasearchitectecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
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
    TextView tvName;
    UserProfileViewModel userProfileViewModel;
    LiveData<DataSnapshot> liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.tv_name);
        userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        liveData = userProfileViewModel.getDataSnapshotLiveData();
        liveData.observe(this, this::changedData);
    }

    private void changedData(DataSnapshot data) {
        if (data != null) {
            String name = data.child("name").getValue(String.class);
            tvName.setText(name);
        }
    }
}
