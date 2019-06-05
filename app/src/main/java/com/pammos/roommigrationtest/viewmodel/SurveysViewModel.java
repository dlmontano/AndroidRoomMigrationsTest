package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.Survey;
import com.pammos.roommigrationtest.repository.SurveyRepository;

import java.util.List;

public class SurveysViewModel extends AndroidViewModel {

    private SurveyRepository surveyRepository;

    public SurveysViewModel(@NonNull Application application) {

        super(application);

        surveyRepository = new SurveyRepository(application);
    }

    public LiveData<List<Survey>> getCurrentSurveys() {

        return surveyRepository.getCurrentSurveysLiveData();
    }
}
