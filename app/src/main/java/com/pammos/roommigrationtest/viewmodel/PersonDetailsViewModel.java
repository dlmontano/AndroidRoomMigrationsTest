package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.repository.PeopleRepository;

public class PersonDetailsViewModel extends AndroidViewModel {

    private PeopleRepository peopleRepository;

    public PersonDetailsViewModel(@NonNull Application application) {

        super(application);

        peopleRepository = new PeopleRepository(application);
    }

    public LiveData<Person> getPersonByUUID(String uuid) {

        return peopleRepository.getPersonByUUIDLiveData(uuid);
    }

    public LiveData<Person> getPersonByDocumentId(String documentId) {

        return peopleRepository.getPersonByDocumentIdLiveData(documentId);
    }
}
