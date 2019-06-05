package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.BloodPressureMeasurement;
import com.pammos.roommigrationtest.repository.BPMRepository;

import java.util.Date;
import java.util.List;

public class BPMsViewModel extends AndroidViewModel {

    private BPMRepository bpmRepository;

    public BPMsViewModel(@NonNull Application application) {

        super(application);

        bpmRepository = new BPMRepository(application);
    }

    public LiveData<List<BloodPressureMeasurement>> getCurrentBPMs() {

        return bpmRepository.getCurrentBPMsLiveData();
    }
}
