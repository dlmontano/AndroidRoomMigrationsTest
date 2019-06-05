package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.model.PersonSurvey;
import com.pammos.roommigrationtest.model.Survey;

import java.util.List;

@Dao
public interface PersonSurveyDao extends BaseDao<PersonSurvey> {

    @Query("SELECT * FROM people_survey")
    LiveData<List<PersonSurvey>> getReactiveAllPeopleSurveys();

    @Query("SELECT * FROM people_survey WHERE survey_id = :surveyId AND person_UUID = :personUUID")
    LiveData<PersonSurvey> getReactivePersonSurveyBySurveyIdAndPersonUUID(
            int surveyId, String personUUID);

    @Query("SELECT p.* FROM people p" +
            " INNER JOIN people_survey ps ON p.UUID = ps.person_UUID" +
            " WHERE ps.survey_id = :surveyId")
    LiveData<List<Person>> getReactivePeopleBySurveyId(int surveyId);

    @Query("SELECT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " WHERE ps.person_UUID = :personUUID")
    LiveData<List<Survey>> getReactiveSurveysByPersonUUID(String personUUID);

    @Query("SELECT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " WHERE ps.person_document_id = :personDocumentId")
    LiveData<List<Survey>> getReactiveSurveysByPersonDocumentId(String personDocumentId);

    @Query("SELECT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " WHERE ps.person_UUID = :personUUID" +
            " AND s.is_active = 1" +
            " AND s.type = 1")
    LiveData<List<Survey>> getReactiveActiveSurveysByPersonUUID(String personUUID);

    @Query("SELECT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " WHERE ps.person_document_id = :personDocumentId" +
            " AND s.is_active = 1" +
            " AND s.type = 1")
    LiveData<List<Survey>> getReactiveActiveSurveysByPersonDocumentId(String personDocumentId);

    @Query("SELECT DISTINCT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " INNER JOIN survey_questions sq ON s.id = sq.survey_id" +
            " WHERE ps.person_UUID = :personUUID" +
            " AND sq.name = 'Numeric'")
    LiveData<List<Survey>> getReactiveReportableSurveysByPersonUUID(String personUUID);

    @Query("Select s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " INNER JOIN survey_questions sq ON s.id = sq.survey_id" +
            " WHERE ps.person_document_id = :personDocumentId" +
            " AND sq.name = 'Numeric'")
    LiveData<List<Survey>> getReactiveReportableSurveysByPersonDocumentId(String personDocumentId);

    @Query("SELECT * FROM people_survey")
    List<PersonSurvey> getAllPeopleSurveys();

    @Query("SELECT * FROM people_survey WHERE survey_id = :surveyId")
    List<PersonSurvey> getPersonSurveysBySurveyId(int surveyId);

    @Query("SELECT * FROM people_survey WHERE person_UUID = :personUUID")
    List<PersonSurvey> getPersonSurveysByPersonUUID(String personUUID);

    @Query("SELECT * FROM people_survey WHERE survey_id = :personDocumentId")
    List<PersonSurvey> getPersonSurveysByPersonDocumentId(String personDocumentId);

    @Query("SELECT * FROM people_survey WHERE survey_id = :surveyId AND person_UUID = :personUUID")
    PersonSurvey getPersonSurveyBySurveyIdAndPersonUUID(int surveyId, String personUUID);

    @Query("SELECT p.* FROM people p" +
            " INNER JOIN people_survey ps ON p.UUID = ps.person_UUID" +
            " WHERE ps.survey_id = :surveyId")
    List<Person> getPeopleBySurveyId(int surveyId);

    @Query("SELECT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " WHERE ps.person_UUID = :personUUID")
    List<Survey> getSurveysByPersonUUID(String personUUID);

    @Query("SELECT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " WHERE ps.person_document_id = :personDocumentId")
    List<Survey> getSurveysByDocumentId(String personDocumentId);

    @Query("SELECT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " WHERE ps.person_UUID = :personUUID" +
            " AND s.is_active = 1" +
            " AND s.type = 1")
    List<Survey> getActiveSurveysByPersonUUID(String personUUID);

    @Query("SELECT s.* FROM surveys s" +
            " INNER JOIN people_survey ps ON s.id = ps.survey_id" +
            " WHERE ps.person_document_id = :personDocumentId" +
            " AND s.is_active = 1" +
            " AND s.type = 1")
    List<Survey> getActiveSurveysByPersonDocumentId(String personDocumentId);
}
