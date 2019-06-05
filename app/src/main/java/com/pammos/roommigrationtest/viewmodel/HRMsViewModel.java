package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.HeartbeatRateMeasurement;
import com.pammos.roommigrationtest.repository.HRMRepository;

import java.util.Date;
import java.util.List;

public class HRMsViewModel extends AndroidViewModel {

    private HRMRepository hrmRepository;

    public HRMsViewModel(@NonNull Application application) {

        super(application);

        hrmRepository = new HRMRepository(application);
    }

    public LiveData<List<HeartbeatRateMeasurement>> getCurrentHRMs() {

        return hrmRepository.getCurrentHRMsLiveData();
    }
}
