package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.model.PersonSurvey;
import com.pammos.roommigrationtest.model.Survey;
import com.pammos.roommigrationtest.repository.PeopleRepository;
import com.pammos.roommigrationtest.repository.PersonSurveyRepository;
import com.pammos.roommigrationtest.repository.SurveyRepository;

public class PersonSurveyDetailsViewModel extends AndroidViewModel {

    private PersonSurveyRepository psRepository;
    private PeopleRepository peopleRepository;
    private SurveyRepository surveyRepository;

    public PersonSurveyDetailsViewModel(@NonNull Application application) {

        super(application);

        psRepository = new PersonSurveyRepository(application);
        peopleRepository = new PeopleRepository(application);
        surveyRepository = new SurveyRepository(application);
    }

    public LiveData<PersonSurvey> getPersonSurveyBySurveyIdAndPersonUUID(
            int surveyId, String personUUID) {

        return psRepository.getPersonSurveyBySurveyIdAndPersonUUIDLiveData(surveyId, personUUID);
    }

    public LiveData<Person> getPersonByUUID(String uuid) {

        return peopleRepository.getPersonByUUIDLiveData(uuid);
    }

    public LiveData<Person> getPersonByDocumentId(String documentId) {

        return peopleRepository.getPersonByDocumentIdLiveData(documentId);
    }

    public LiveData<Survey> getSurveyById(int surveyId) {

        return surveyRepository.getSurveyByIdLiveData(surveyId);
    }
}
