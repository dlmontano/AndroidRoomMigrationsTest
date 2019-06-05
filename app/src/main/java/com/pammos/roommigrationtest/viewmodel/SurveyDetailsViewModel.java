package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.Survey;
import com.pammos.roommigrationtest.model.SurveyQuestion;
import com.pammos.roommigrationtest.repository.SurveyQuestionRepository;
import com.pammos.roommigrationtest.repository.SurveyRepository;

import java.util.List;

public class SurveyDetailsViewModel extends AndroidViewModel {

    private SurveyRepository surveyRepository;
    private SurveyQuestionRepository sqRepository;

    public SurveyDetailsViewModel(@NonNull Application application) {

        super(application);

        surveyRepository = new SurveyRepository(application);
        sqRepository = new SurveyQuestionRepository(application);
    }

    public LiveData<Survey> getSurveyById(int surveyId) {

        return surveyRepository.getSurveyByIdLiveData(surveyId);
    }

    public LiveData<List<SurveyQuestion>> getAllSurveyQuestionsBySurveyId(int surveyId) {

        return sqRepository.getAllSurveyQuestionsBySurveyIdLiveData(surveyId);
    }

    public LiveData<List<SurveyQuestion>> getActiveSurveyQuestionsBySurveyId(int surveyId) {

        return sqRepository.getActiveSurveyQuestionsBySurveyIdLiveData(surveyId);
    }
}
