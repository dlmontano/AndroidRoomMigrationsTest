package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.BloodPressureMeasurement;
import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.repository.BPMRepository;
import com.pammos.roommigrationtest.repository.PeopleRepository;

import java.util.Date;

public class BPMDetailsViewModel extends AndroidViewModel {

    private BPMRepository bpmRepository;
    private PeopleRepository peopleRepository;

    public BPMDetailsViewModel(@NonNull Application application) {

        super(application);

        bpmRepository = new BPMRepository(application);
        peopleRepository = new PeopleRepository(application);
    }

    public LiveData<BloodPressureMeasurement> getBPMByPersonUUIDAndTimestamp(
            String personUUID, Date timestamp) {

        return bpmRepository.getBPMByPersonUUIDAndTimestampLiveData(personUUID, timestamp);
    }

    public LiveData<Person> getPersonByUUID(String uuid) {

        return peopleRepository.getPersonByUUIDLiveData(uuid);
    }

    public LiveData<Person> getPersonByDocumentId(String documentId) {

        return peopleRepository.getPersonByDocumentIdLiveData(documentId);
    }
}
