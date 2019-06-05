package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.PersonSurvey;
import com.pammos.roommigrationtest.repository.PersonSurveyRepository;

import java.util.List;

public class PeopleSurveysViewModel extends AndroidViewModel {

    private PersonSurveyRepository psRepository;

    public PeopleSurveysViewModel(@NonNull Application application) {

        super(application);

        psRepository = new PersonSurveyRepository(application);
    }

    public LiveData<List<PersonSurvey>> getCurrentPeopleSurveys() {

        return psRepository.getCurrentPeopleSurveysLiveData();
    }

    public LiveData<PersonSurvey> getPersonSurveyBySurveyIdAndPersonUUID(
            int surveyId, String personUUID) {

        return psRepository.getPersonSurveyBySurveyIdAndPersonUUIDLiveData(surveyId, personUUID);
    }
}
