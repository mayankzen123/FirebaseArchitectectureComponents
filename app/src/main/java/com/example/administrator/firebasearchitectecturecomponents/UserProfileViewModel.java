package com.example.administrator.firebasearchitectecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileViewModel extends ViewModel {
    private final DatabaseReference USER_PROFILE_REF =
            FirebaseDatabase.getInstance().getReference("/UserDetails");

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(USER_PROFILE_REF);

    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData() {
        return liveData;
    }
}
