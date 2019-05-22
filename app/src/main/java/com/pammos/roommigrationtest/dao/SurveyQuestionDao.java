package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.SurveyQuestion;

import java.util.List;

@Dao
public interface SurveyQuestionDao extends BaseDao<SurveyQuestion> {

    @Query("SELECT * FROM survey_questions WHERE is_active = 1")
    LiveData<List<SurveyQuestion>> getAllReactiveSurveyQuestions();

    @Query("SELECT * FROM survey_questions WHERE survey_id = :surveyId AND is_active = 1")
    LiveData<List<SurveyQuestion>> getReactiveActiveSurveyQuestionsBySurveyId(int surveyId);

    @Query("SELECT * FROM survey_questions WHERE survey_id = :surveyId")
    LiveData<List<SurveyQuestion>> getAllReactiveSurveyQuestionsBySurveyId(int surveyId);

    @Query("SELECT * FROM survey_questions WHERE id = :questionId")
    LiveData<SurveyQuestion> getReactiveSurveyQuestionById(int questionId);

    @Query("SELECT * FROM survey_questions")
    List<SurveyQuestion> getAllSurveyQuestions();

    @Query("SELECT * FROM survey_questions WHERE survey_id = :surveyId AND is_active = 1")
    List<SurveyQuestion> getActiveSurveyQuestionsBySurveyId(int surveyId);

    @Query("SELECT * FROM survey_questions WHERE survey_id = :surveyId")
    List<SurveyQuestion> getAllSurveyQuestionsBySurveyId(int surveyId);

    @Query("SELECT * FROM survey_questions WHERE id = :questionId")
    SurveyQuestion getSurveyQuestionById(int questionId);

    @Query("SELECT * FROM survey_questions WHERE id = :questionId and survey_id = :surveyId")
    SurveyQuestion hasSurveyQuestion(int questionId, int surveyId);
}
