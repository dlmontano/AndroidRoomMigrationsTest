package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.Survey;

import java.util.List;

public interface SurveyDao extends BaseDao<Survey> {

    @Query("SELECT * FROM surveys WHERE is_active = 1")
    LiveData<List<Survey>> getAllReactiveSurveys();

    @Query("SELECT * FROM surveys WHERE is_active = 1 AND type > 1")
    LiveData<List<Survey>> getAllReactiveActiveDeviceSurveys();

    @Query("SELECT * FROM surveys WHERE id = :surveyId")
    LiveData<Survey> getReactiveSurveyById(int surveyId);

    @Query("SELECT * FROM surveys WHERE is_active = 1")
    List<Survey> getAllSurveys();

    @Query("SELECT * FROM surveys WHERE is_active = 1 AND type > 1")
    List<Survey> getAllActiveDeviceSurveys();

    @Query("SELECT COUNT(*) FROM surveys WHERE is_active = 1")
    LiveData<Integer> getCountSurveys();

    @Query("SELECT * FROM surveys WHERE id = :surveyId")
    Survey getSurveyById(int surveyId);

    @Query("SELECT * FROM surveys WHERE id = :surveyId")
    Survey hasSurvey(int surveyId);

    @Query("UPDATE surveys SET is_active = 0 AND type = 1")
    void deactivateAllNonDeviceSurveys();
}
