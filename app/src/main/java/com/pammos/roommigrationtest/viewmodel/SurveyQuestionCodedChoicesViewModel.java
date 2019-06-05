package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.SurveyQuestionCodedChoice;
import com.pammos.roommigrationtest.repository.SurveyQuestionCodedChoiceRepository;

import java.util.List;

public class SurveyQuestionCodedChoicesViewModel extends AndroidViewModel {

    private SurveyQuestionCodedChoiceRepository sqccRepository;

    public SurveyQuestionCodedChoicesViewModel(@NonNull Application application) {

        super(application);

        sqccRepository = new SurveyQuestionCodedChoiceRepository(application);
    }

    public LiveData<List<SurveyQuestionCodedChoice>> getAllSurveyQuestionCodedChoicesLiveData() {

        return sqccRepository.getAllSurveyQuestionCodedChoicesLiveData();
    }

    public LiveData<List<SurveyQuestionCodedChoice>> getSurveyQuestionCodedChoicesByQuestionIdLiveData(int questionId) {

        return sqccRepository.getSurveyQuestionCodedChoicesByQuestionIdLiveData(questionId);
    }

    public LiveData<List<SurveyQuestionCodedChoice>> getSurveyQuestionCodedChoiceBySurveyIdLiveData(int surveyId) {

        return sqccRepository.getSurveyQuestionCodedChoiceBySurveyIdLiveData(surveyId);
    }
}
