package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.BloodPressureMeasurement;

import java.util.Date;
import java.util.List;

@Dao
public interface BloodPressureMeasurementDao extends BaseDao<BloodPressureMeasurement> {

    @Query("SELECT * FROM bp_measurements")
    LiveData<List<BloodPressureMeasurement>> getReactiveAllBPMs();

    @Query("SELECT * FROM bp_measurements" +
            " WHERE person_UUID = :personUUID" +
            " AND timestamp BETWEEN :startDate AND :endDate" +
            " ORDER BY timestamp DESC")
    LiveData<List<BloodPressureMeasurement>> getBPMsByPersonUUIDAndDateRange(
            String personUUID, Date startDate, Date endDate);

    @Query("SELECT * FROM bp_measurements" +
            " WHERE person_document_id = :personDocumentId" +
            " AND timestamp BETWEEN :startDate AND :endDate" +
            " ORDER BY timestamp DESC")
    LiveData<List<BloodPressureMeasurement>> getBPMsByDocumentPersonIdAndDateRange(
            String personDocumentId, Date startDate, Date endDate);

    @Query("SELECT * FROM bp_measurements" +
            " WHERE person_UUID = :personUUID" +
            " AND timestamp = :timestamp")
    LiveData<BloodPressureMeasurement> getReactiveBPMByPersonUUIDAndTimestamp(
            String personUUID, Date timestamp);

    @Query("SELECT timestamp FROM bp_measurements" +
            " WHERE person_UUID = :personUUID" +
            " ORDER BY timestamp DESC" +
            " LIMIT 1")
    LiveData<Date> getLatestBPMDateByPersonUUID(String personUUID);

    @Query("SELECT timestamp FROM bp_measurements" +
            " WHERE person_document_id = :personDocumentId" +
            " ORDER BY timestamp DESC" +
            " LIMIT 1")
    LiveData<Date> getLatestBPMDateByPersonDocumentId(String personDocumentId);

    @Query("SELECT COUNT(*) FROM bp_measurements" +
            " WHERE person_UUID = :personUUID")
    LiveData<Integer> getCountMeasureByPerson(String personUUID);

    @Query("SELECT * FROM bp_measurements")
    List<BloodPressureMeasurement> getAllBPMs();

    @Query("SELECT * FROM bp_measurements" +
            " WHERE person_UUID = :personUUID" +
            " AND timestamp = :timestamp")
    BloodPressureMeasurement getBPMByPersonUUIDAndTimestamp(String personUUID, Date timestamp);
}
