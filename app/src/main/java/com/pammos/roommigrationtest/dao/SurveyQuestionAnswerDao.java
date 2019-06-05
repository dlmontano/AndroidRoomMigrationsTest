package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.SurveyQuestionAnswer;

import java.util.Date;
import java.util.List;

@Dao
public interface SurveyQuestionAnswerDao extends BaseDao<SurveyQuestionAnswer> {

    @Query("SELECT * FROM survey_question_answers")
    LiveData<List<SurveyQuestionAnswer>> getAllReactiveSurveyQuestionAnswers();

    @Query("SELECT * FROM survey_question_answers WHERE encounter_id = :encounterId")
    LiveData<List<SurveyQuestionAnswer>> getReactiveSurveyQuestionAnswersByEncounterId(long encounterId);

    @Query("SELECT * FROM survey_question_answers WHERE question_id = :questionId")
    LiveData<List<SurveyQuestionAnswer>> getReactiveSurveyQuestionAnswersByQuestionId(int questionId);

    @Query("SELECT sqa.* FROM survey_question_answers sqa" +
            " JOIN survey_encounters se ON sqa.encounter_id = se.id" +
            " JOIN surveys s ON se.survey_id = s.id" +
            " JOIN survey_questions sq ON sqa.question_id = sq.id" +
            " JOIN people_survey ps on s.id = ps.survey_id" +
            " WHERE s.id = :surveyId" +
            " AND ps.person_UUID = :personUUID" +
            " AND sq.name = 'Numeric'" +
            " AND se.timestamp BETWEEN :startTime AND :endTime")
    LiveData<List<SurveyQuestionAnswer>> getReactiveReportableSurveyQuestionAnswersByPersonUUIDAndSurveyIdAndDateRange(
            String personUUID, int surveyId, Date startTime, Date endTime);

    @Query("SELECT * FROM survey_question_answers WHERE id = :surveyQuestionAnswerId")
    LiveData<SurveyQuestionAnswer> getReactiveSurveyQuestionAnswerById(long surveyQuestionAnswerId);

    @Query("SELECT * FROM survey_question_answers")
    List<SurveyQuestionAnswer> getAllSurveyQuestionAnswers();

    @Query("SELECT * FROM survey_question_answers WHERE encounter_id = :encounterId")
    List<SurveyQuestionAnswer> getSurveyQuestionAnswersByEncounterId(long encounterId);

    @Query("SELECT * FROM survey_question_answers WHERE question_id = :questionId")
    List<SurveyQuestionAnswer> getSurveyQuestionAnswersByQuestionId(int questionId);

    @Query("SELECT * FROM survey_question_answers WHERE id = :surveyQuestionAnswerId")
    SurveyQuestionAnswer getSurveyQuestionAnswerById(long surveyQuestionAnswerId);
}
