package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.SurveyQuestionAnswer;
import com.pammos.roommigrationtest.repository.SurveyQuestionAnswerRepository;

import java.util.List;

public class SurveyQuestionAnswersViewModel extends AndroidViewModel {

    private SurveyQuestionAnswerRepository sqaRepository;

    public SurveyQuestionAnswersViewModel(@NonNull Application application) {

        super(application);

        sqaRepository = new SurveyQuestionAnswerRepository(application);
    }

    public LiveData<List<SurveyQuestionAnswer>> getAllSurveyQuestionAnswers() {

        return sqaRepository.getAllSurveyQuestionAnswersLiveData();
    }

    public LiveData<List<SurveyQuestionAnswer>> getSurveyQuestionAnswersByQuestionId(
            int questionId) {

        return sqaRepository.getSurveyQuestionAnswersByQuestionIdLiveData(questionId);
    }
}
