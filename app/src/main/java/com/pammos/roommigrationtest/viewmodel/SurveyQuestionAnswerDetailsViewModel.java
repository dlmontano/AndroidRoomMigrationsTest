package com.pammos.roommigrationtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pammos.roommigrationtest.model.SurveyEncounter;
import com.pammos.roommigrationtest.model.SurveyQuestion;
import com.pammos.roommigrationtest.model.SurveyQuestionAnswer;
import com.pammos.roommigrationtest.repository.SurveyEncounterRepository;
import com.pammos.roommigrationtest.repository.SurveyQuestionAnswerRepository;
import com.pammos.roommigrationtest.repository.SurveyQuestionRepository;

public class SurveyQuestionAnswerDetailsViewModel extends AndroidViewModel {

    private SurveyQuestionAnswerRepository sqaRepository;
    private SurveyQuestionRepository sqRepository;
    private SurveyEncounterRepository seRepository;

    public SurveyQuestionAnswerDetailsViewModel(@NonNull Application application) {

        super(application);

        sqaRepository = new SurveyQuestionAnswerRepository(application);
        sqRepository = new SurveyQuestionRepository(application);
        seRepository = new SurveyEncounterRepository(application);
    }

    public LiveData<SurveyQuestionAnswer> getSurveyQuestionAnswerById(
            long surveyQuestionAnswerId) {

        return sqaRepository.getSurveyQuestionAnswerByIdLiveData(surveyQuestionAnswerId);
    }

    public LiveData<SurveyEncounter> getSurveyEncounterById(long surveyEncounterId) {

        return seRepository.getSurveyEncounterByIdLiveData(surveyEncounterId);
    }

    public LiveData<SurveyQuestion> getSurveyQuestionById(int questionId) {

        return sqRepository.getSurveyQuestionByIdLiveData(questionId);
    }
}
