package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.SurveyQuestion;
import com.pammos.roommigrationtest.repository.SurveyQuestionRepository;

import java.util.List;

public class SurveyQuestionsViewModel extends AndroidViewModel {

    private SurveyQuestionRepository sqRepository;

    public SurveyQuestionsViewModel(@NonNull Application application) {

        super(application);

        sqRepository = new SurveyQuestionRepository(application);
    }

    public LiveData<List<SurveyQuestion>> getCurrentSurveyQuestions() {

        return sqRepository.getCurrentSurveyQuestionsLiveData();
    }
}
