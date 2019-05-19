package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.HeartbeatRateMeasurement;

import java.util.Date;
import java.util.List;

public interface HeartbeatRateMeasurementDao extends BaseDao<HeartbeatRateMeasurement> {

    @Query("SELECT * FROM hr_measurements ORDER BY timestamp DESC")
    LiveData<List<HeartbeatRateMeasurement>> getAllHRMs();

    @Query("SELECT * FROM hr_measurements" +
            " WHERE person_UUID = :personUUID" +
            " AND timestamp BETWEEN :startDate AND :endDate" +
            " ORDER BY timestamp DESC")
    LiveData<List<HeartbeatRateMeasurement>> getHRMsByPersonUUIDAndDateRange(
            String personUUID, Date startDate, Date endDate);

    @Query("SELECT * FROM hr_measurements" +
            " WHERE person_document_id = :personDocumentId" +
            " AND timestamp BETWEEN :startDate AND :endDate" +
            " ORDER BY timestamp DESC")
    LiveData<List<HeartbeatRateMeasurement>> getHRMsByPersonDocumentIdAndDateRange(
            String personDocumentId, Date startDate, Date endDate);

    @Query("SELECT timestamp FROM hr_measurements" +
            " WHERE person_UUID = :personUUID" +
            " ORDER BY timestamp DESC" +
            " LIMIT 1")
    LiveData<Date> getLatestHRMDateByPersonUUID(String personUUID);

    @Query("SELECT timestamp FROM hr_measurements" +
            " WHERE person_document_id = :personDocumentId" +
            " ORDER BY timestamp DESC" +
            " LIMIT 1")
    LiveData<Date> getLatestHRMDateByPersonDocumentId(String personDocumentId);


    @Query("SELECT COUNT(*) FROM hr_measurements" +
            " WHERE person_UUID = :personUUID")
    LiveData<Integer> getCountMeasureByPerson(String personUUID);
}
