package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.SurveyEncounter;
import com.pammos.roommigrationtest.repository.SurveyEncounterRepository;

import java.util.List;

public class SurveyEncountersViewModel extends AndroidViewModel {

    private SurveyEncounterRepository seRepository;

    public SurveyEncountersViewModel(@NonNull Application application) {

        super(application);

        seRepository = new SurveyEncounterRepository(application);
    }

    public LiveData<List<SurveyEncounter>> getAllSurveyEncounters() {

        return seRepository.getAllSurveyEncountersLiveData();
    }

    public LiveData<List<SurveyEncounter>> getSurveyEncountersByPersonUUID(String personUUID) {

        return seRepository.getSurveyEncountersByPersonUUIDLiveData(personUUID);
    }

    public LiveData<List<SurveyEncounter>> getSurveyEncountersByPersonUUIDAndSurveyId(
            String personUUID, int surveyId) {

        return seRepository.getSurveyEncountersByPersonUUIDAndSurveyIdLiveData(personUUID, surveyId);
    }
}
