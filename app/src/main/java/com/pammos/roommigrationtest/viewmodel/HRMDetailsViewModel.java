package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.HeartbeatRateMeasurement;
import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.repository.HRMRepository;
import com.pammos.roommigrationtest.repository.PeopleRepository;

import java.util.Date;

public class HRMDetailsViewModel extends AndroidViewModel {

    private HRMRepository hrmRepository;
    private PeopleRepository peopleRepository;

    public HRMDetailsViewModel(@NonNull Application application) {

        super(application);

        hrmRepository = new HRMRepository(application);
        peopleRepository = new PeopleRepository(application);
    }

    public LiveData<HeartbeatRateMeasurement> getHRMByPersonUUIDAndTimestamp(
            String personUUID, Date timestamp) {

        return hrmRepository.getHRMByPersonUUIDAndTimestampLiveData(personUUID, timestamp);
    }

    public LiveData<Person> getPersonByUUID(String uuid) {

        return peopleRepository.getPersonByUUIDLiveData(uuid);
    }

    public LiveData<Person> getPersonByDocumentId(String documentId) {

        return peopleRepository.getPersonByDocumentIdLiveData(documentId);
    }
}
