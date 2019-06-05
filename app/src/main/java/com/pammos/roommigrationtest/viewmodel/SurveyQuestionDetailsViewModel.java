package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.Survey;
import com.pammos.roommigrationtest.model.SurveyQuestion;
import com.pammos.roommigrationtest.repository.SurveyQuestionRepository;
import com.pammos.roommigrationtest.repository.SurveyRepository;

public class SurveyQuestionDetailsViewModel extends AndroidViewModel {

    private SurveyQuestionRepository sqRepository;
    private SurveyRepository surveyRepository;

    public SurveyQuestionDetailsViewModel(@NonNull Application application) {

        super(application);

        sqRepository = new SurveyQuestionRepository(application);
        surveyRepository = new SurveyRepository(application);
    }

    public LiveData<SurveyQuestion> getSurveyQuestionById(int questionId) {

        return sqRepository.getSurveyQuestionByIdLiveData(questionId);
    }

    public LiveData<Survey> getSurveyById(int surveyId) {

        return surveyRepository.getSurveyByIdLiveData(surveyId);
    }
}
