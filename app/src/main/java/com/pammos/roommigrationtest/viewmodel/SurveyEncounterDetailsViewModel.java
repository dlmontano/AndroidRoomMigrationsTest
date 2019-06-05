package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.model.Survey;
import com.pammos.roommigrationtest.model.SurveyEncounter;
import com.pammos.roommigrationtest.model.SurveyQuestionAnswer;
import com.pammos.roommigrationtest.repository.PeopleRepository;
import com.pammos.roommigrationtest.repository.SurveyEncounterRepository;
import com.pammos.roommigrationtest.repository.SurveyQuestionAnswerRepository;
import com.pammos.roommigrationtest.repository.SurveyRepository;

import java.util.List;

public class SurveyEncounterDetailsViewModel extends AndroidViewModel {

    private SurveyEncounterRepository seRepository;
    private PeopleRepository peopleRepository;
    private SurveyRepository surveyRepository;
    private SurveyQuestionAnswerRepository sqaRepository;

    public SurveyEncounterDetailsViewModel(@NonNull Application application) {

        super(application);

        seRepository = new SurveyEncounterRepository(application);
        peopleRepository = new PeopleRepository(application);
        surveyRepository = new SurveyRepository(application);
        sqaRepository = new SurveyQuestionAnswerRepository(application);
    }

    public LiveData<SurveyEncounter> getSurveyEncounterById(long surveyEncounterId) {

        return seRepository.getSurveyEncounterByIdLiveData(surveyEncounterId);
    }

    public LiveData<Person> getPersonByUUID(String uuid) {

        return peopleRepository.getPersonByUUIDLiveData(uuid);
    }

    public LiveData<Survey> getSurveyById(int surveyId) {

        return surveyRepository.getSurveyByIdLiveData(surveyId);
    }

    public LiveData<List<SurveyQuestionAnswer>> getSurveyQuestionAnswersByEncounterId(
            long encounterId) {

        return sqaRepository.getSurveyQuestionAnswersByEncounterIdLiveData(encounterId);
    }
}
