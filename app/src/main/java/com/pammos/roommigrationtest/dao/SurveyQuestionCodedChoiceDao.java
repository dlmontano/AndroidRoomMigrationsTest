package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.SurveyQuestionCodedChoice;

import java.util.List;

@Dao
public interface SurveyQuestionCodedChoiceDao extends BaseDao<SurveyQuestionCodedChoice> {

    @Query("SELECT * FROM survey_question_coded_choices WHERE question_id = :questionId")
    LiveData<List<SurveyQuestionCodedChoice>> getReactiveSurveyQuestionCodedChoicesByQuestionId(int questionId);

    @Query("SELECT * FROM survey_question_coded_choices WHERE id = :sqccId")
    LiveData<SurveyQuestionCodedChoice> getReactiveSurveyQuestionCodedChoiceById(int sqccId);

    @Query("SELECT * FROM survey_question_coded_choices WHERE survey_id = :surveyId")
    LiveData<List<SurveyQuestionCodedChoice>> getReactiveSurveyQuestionCodedChoiceBySurveyId(int surveyId);

    @Query("SELECT * FROM survey_question_coded_choices WHERE question_id = :questionId")
    List<SurveyQuestionCodedChoice> getSurveyQuestionCodedChoicesByQuestionId(int questionId);

    @Query("SELECT * FROM survey_question_coded_choices WHERE id = :sqccId")
    SurveyQuestionCodedChoice getSurveyQuestionCodedChoiceById(int sqccId);

    @Query("SELECT * FROM survey_question_coded_choices WHERE survey_id = :surveyId")
    List<SurveyQuestionCodedChoice> getSurveyQuestionCodedChoiceBySurveyId(int surveyId);
}
