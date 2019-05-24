package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.BloodPressureMeasurementDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.BloodPressureMeasurement;

import java.util.Date;
import java.util.List;

public class BPMRepository extends BaseRepository<BloodPressureMeasurement> {

    private BloodPressureMeasurementDao bpmDao;

    public BPMRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        bpmDao = db.getBloodPressureMeasurementDao();
    }

    public LiveData<List<BloodPressureMeasurement>> getCurrentBPMsLiveData() {

        return bpmDao.getReactiveAllBPMs();
    }

    public LiveData<BloodPressureMeasurement> getBPMByPersonUUIDAndTimestampLiveData(
            String personUUID, Date timestamp) {

        return bpmDao.getReactiveBPMByPersonUUIDAndTimestamp(personUUID, timestamp);
    }

    public List<BloodPressureMeasurement> getCurrentPeopleSurveys() {

        return bpmDao.getAllBPMs();
    }

    public BloodPressureMeasurement getBPMByPersonUUIDAndTimestamp(
            String personUUID, Date timestamp) {

        return bpmDao.getBPMByPersonUUIDAndTimestamp(personUUID, timestamp);
    }

    public void save(BloodPressureMeasurement bpm) {

        save(bpmDao, bpm);
    }

    public void save(BloodPressureMeasurement... bpms) {

        save(bpmDao, bpms);
    }

    public void update(BloodPressureMeasurement bpm) {

        update(bpmDao, bpm);
    }

    public void delete(BloodPressureMeasurement bpm) {

        delete(bpmDao, bpm);
    }

    public void delete(BloodPressureMeasurement... bpms) {

        delete(bpmDao, bpms);
    }
}
