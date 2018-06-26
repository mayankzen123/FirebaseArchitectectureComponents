package com.example.administrator.firebasearchitectecturecomponents;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileViewModel extends ViewModel {
    private final DatabaseReference USER_PROFILE_REF =
            FirebaseDatabase.getInstance().getReference("/UserDetails");

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(USER_PROFILE_REF);
    private final MediatorLiveData<FirebaseDataBean> hotStockLiveData = new MediatorLiveData<>();

    public UserProfileViewModel() {
        hotStockLiveData.addSource(liveData, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            hotStockLiveData.postValue(dataSnapshot.getValue(FirebaseDataBean.class));
                        }
                    }).start();
                } else {
                    hotStockLiveData.setValue(null);
                }
            }
        });
    }

    @NonNull
    public LiveData<FirebaseDataBean> getDataSnapshotLiveData() {
        return hotStockLiveData;
    }
}
