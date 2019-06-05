package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.SurveyQuestion;
import com.pammos.roommigrationtest.model.SurveyQuestionCodedChoice;
import com.pammos.roommigrationtest.repository.SurveyQuestionCodedChoiceRepository;
import com.pammos.roommigrationtest.repository.SurveyQuestionRepository;

public class SurveyQuestionCodedChoiceDetailsViewModel extends AndroidViewModel {

    private SurveyQuestionCodedChoiceRepository sqccRepository;
    private SurveyQuestionRepository sqRepository;

    public SurveyQuestionCodedChoiceDetailsViewModel(@NonNull Application application) {

        super(application);

        sqccRepository = new SurveyQuestionCodedChoiceRepository(application);
        sqRepository = new SurveyQuestionRepository(application);
    }

    public LiveData<SurveyQuestionCodedChoice> getSurveyQuestionCodedChoiceByIdLiveData(int sqccId) {

        return sqccRepository.getSurveyQuestionCodedChoiceByIdLiveData(sqccId);
    }

    public LiveData<SurveyQuestion> getSurveyQuestionById(int questionId) {

        return sqRepository.getSurveyQuestionByIdLiveData(questionId);
    }
}
