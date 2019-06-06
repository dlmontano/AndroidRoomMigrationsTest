package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.HeartbeatRateMeasurementDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.HeartbeatRateMeasurement;

import java.util.Date;
import java.util.List;

public class HRMRepository extends BaseRepository<HeartbeatRateMeasurement> {

    private HeartbeatRateMeasurementDao hrmDao;

    public HRMRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        hrmDao = db.getHeartbeatRateMeasurementDao();
    }

    public LiveData<List<HeartbeatRateMeasurement>> getCurrentHRMsLiveData() {

        return hrmDao.getReactiveAllHRMs();
    }

    public LiveData<List<HeartbeatRateMeasurement>> getHRMsByPersonUUIDLiveData(String personUUID) {

        return hrmDao.getReactiveHRMsByPersonUUID(personUUID);
    }

    public LiveData<List<HeartbeatRateMeasurement>> getHRMsByPersonDocumentIdLiveData(
            String personDocumentId) {

        return hrmDao.getReactiveHRMsByPersonUUID(personDocumentId);
    }

    public LiveData<HeartbeatRateMeasurement> getHRMByPersonUUIDAndTimestampLiveData(
            String personUUID, Date timestamp) {

        return hrmDao.getReactiveHRMByPersonUUIDAndTimestamp(personUUID, timestamp);
    }

    public List<HeartbeatRateMeasurement> getCurrentHRMs() {

        return hrmDao.getAllHRMs();
    }

    public HeartbeatRateMeasurement getHRMByPersonUUIDAndTimestamp(
            String personUUID, Date timestamp) {

        return hrmDao.getHRMByPersonUUIDAndTimestamp(personUUID, timestamp);
    }

    public void save(HeartbeatRateMeasurement hrm) {

        save(hrmDao, hrm);
    }

    public void save(HeartbeatRateMeasurement... hrm) {

        save(hrmDao, hrm);
    }

    public void update(HeartbeatRateMeasurement hrm) {

        update(hrmDao, hrm);
    }

    public void delete(HeartbeatRateMeasurement hrm) {

        delete(hrmDao, hrm);
    }

    public void delete(HeartbeatRateMeasurement... hrm) {

        delete(hrmDao, hrm);
    }
}
