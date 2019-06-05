package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.repository.PeopleRepository;

import java.util.List;

public class PeopleViewModel extends AndroidViewModel {

    private PeopleRepository peopleRepository;

    public PeopleViewModel(@NonNull Application application) {

        super(application);

        peopleRepository = new PeopleRepository(application);
    }

    public LiveData<List<Person>> getCurrentPeople() {

        return peopleRepository.getCurrentPeopleLiveData();
    }
}
