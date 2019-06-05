package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.SurveyEncounter;

import java.util.Date;
import java.util.List;

@Dao
public interface SurveyEncounterDao extends BaseDao<SurveyEncounter> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long saveForKey(SurveyEncounter surveyEncounter);

    @Query("SELECT * FROM survey_encounters")
    LiveData<List<SurveyEncounter>> getAllReactiveSurveyEncounters();

    @Query("SELECT * FROM survey_encounters WHERE person_UUID = :personUUID")
    LiveData<List<SurveyEncounter>> getReactiveSurveyEncountersByPersonUUID(String personUUID);

    @Query("SELECT timestamp" +
            " FROM survey_encounters" +
            " WHERE person_UUID = :personUUID" +
            " AND survey_id = :surveyId" +
            " ORDER BY timestamp ASC" +
            " LIMIT 1")
    LiveData<Date> getReactiveFirstSurveyEncounterDateByPersonUUIDAndSurveyId(String personUUID,
                                                                              int surveyId);

    @Query("SELECT *" +
            " FROM survey_encounters" +
            " WHERE person_UUID = :personUUID" +
            " AND survey_id = :surveyId")
    LiveData<List<SurveyEncounter>> getReactiveSurveyEncountersByPersonUUIDAndSurveyId(
            String personUUID, int surveyId);

    @Query("SELECT *" +
            " FROM survey_encounters" +
            " WHERE person_UUID = :personUUID" +
            " AND survey_id = :surveyId" +
            " AND timestamp BETWEEN :startTime AND :endTime")
    LiveData<List<SurveyEncounter>> getReactiveSurveyEncountersByPersonUUIDAndSurveyIdAndDateRange(
            String personUUID, int surveyId, Date startTime, Date endTime);

    @Query("SELECT *" +
            " FROM survey_encounters" +
            " WHERE is_synchronized = 0" +
            " AND person_UUID = :personUUID")
    LiveData<List<SurveyEncounter>> getReactiveUnsynchronizedSurveyEncountersByPersonUUID(
            String personUUID);

    @Query("SELECT * FROM survey_encounters WHERE id = :surveyEncounterId")
    LiveData<SurveyEncounter> getReactiveSurveyEncounterById(long surveyEncounterId);

    @Query("SELECT * FROM survey_encounters")
    List<SurveyEncounter> getAllSurveyEncounters();

    @Query("SELECT *" +
            " FROM survey_encounters" +
            " WHERE is_synchronized = 0" +
            " AND person_UUID = :personUUID")
    List<SurveyEncounter> getUnsynchronizedSurveyEncountersByPersonUUID(String personUUID);

    @Query("SELECT * FROM survey_encounters WHERE id = :surveyEncounterId")
    SurveyEncounter getSurveyEncounterById(long surveyEncounterId);

    @Query("SELECT * FROM survey_encounters WHERE person_UUID = :personUUID")
    List<SurveyEncounter> getSurveyEncountersByPersonUUID(String personUUID);

    @Query("SELECT *" +
            " FROM survey_encounters" +
            " WHERE person_UUID = :personUUID" +
            " AND survey_id = :surveyId")
    List<SurveyEncounter> getSurveyEncountersByPersonUUIDAndSurveyId(String personUUID,
                                                                     int surveyId);
}
