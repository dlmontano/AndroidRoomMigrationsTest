package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.BloodPressureMeasurement;
import com.pammos.roommigrationtest.model.HeartbeatRateMeasurement;
import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.model.Survey;
import com.pammos.roommigrationtest.repository.BPMRepository;
import com.pammos.roommigrationtest.repository.HRMRepository;
import com.pammos.roommigrationtest.repository.PeopleRepository;
import com.pammos.roommigrationtest.repository.PersonSurveyRepository;

import java.util.List;

public class PersonDetailsViewModel extends AndroidViewModel {

    private PeopleRepository peopleRepository;
    private PersonSurveyRepository psRepository;
    private BPMRepository bpmRepository;
    private HRMRepository hrmRepository;

    public PersonDetailsViewModel(@NonNull Application application) {

        super(application);

        peopleRepository = new PeopleRepository(application);
        psRepository = new PersonSurveyRepository(application);
        bpmRepository = new BPMRepository(application);
        hrmRepository = new HRMRepository(application);
    }

    public LiveData<Person> getPersonByUUID(String uuid) {

        return peopleRepository.getPersonByUUIDLiveData(uuid);
    }

    public LiveData<Person> getPersonByDocumentId(String documentId) {

        return peopleRepository.getPersonByDocumentIdLiveData(documentId);
    }

    public LiveData<List<Survey>> getSurveysByPersonUUID(String personUUID) {

        return psRepository.getSurveysByPersonUUIDLiveData(personUUID);
    }

    public LiveData<List<BloodPressureMeasurement>> getBPMsByPersonUUID(String personUUID) {

        return bpmRepository.getBPMsByPersonUUIDLiveData(personUUID);
    }

    public LiveData<List<HeartbeatRateMeasurement>> getHRMsByPersonUUID(String personUUID) {

        return hrmRepository.getHRMsByPersonUUIDLiveData(personUUID);
    }
}
