package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.SurveyEncounterDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.SurveyEncounter;

import java.util.List;

public class SurveyEncounterRepository extends BaseRepository<SurveyEncounter> {

    private SurveyEncounterDao seDao;

    public SurveyEncounterRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        seDao = db.getSurveyEncounterDao();
    }

    public LiveData<List<SurveyEncounter>> getAllSurveyEncountersLiveData() {

        return seDao.getAllReactiveSurveyEncounters();
    }

    public LiveData<SurveyEncounter> getSurveyEncounterByIdLiveData(long surveyEncounterId) {

        return seDao.getReactiveSurveyEncounterById(surveyEncounterId);
    }

    public LiveData<List<SurveyEncounter>> getSurveyEncountersByPersonUUIDLiveData(String personUUID) {

        return seDao.getReactiveSurveyEncountersByPersonUUID(personUUID);
    }

    public LiveData<List<SurveyEncounter>> getSurveyEncountersByPersonUUIDAndSurveyIdLiveData(
            String personUUID, int surveyId) {

        return seDao.getReactiveSurveyEncountersByPersonUUIDAndSurveyId(personUUID, surveyId);
    }

    public List<SurveyEncounter> getAllSurveyEncounters() {

        return seDao.getAllSurveyEncounters();
    }

    public SurveyEncounter getSurveyEncounterById(long surveyEncounterId) {

        return seDao.getSurveyEncounterById(surveyEncounterId);
    }

    public List<SurveyEncounter> getSurveyEncountersByPersonUUID(String personUUID) {

        return seDao.getSurveyEncountersByPersonUUID(personUUID);
    }

    public List<SurveyEncounter> getSurveyEncountersByPersonUUIDAndSurveyId(String personUUID,
                                                                            int surveyId) {

        return seDao.getSurveyEncountersByPersonUUIDAndSurveyId(personUUID, surveyId);
    }

    public void save(SurveyEncounter se) {

        save(seDao, se);
    }

    public void save(SurveyEncounter... ses) {

        save(seDao, ses);
    }

    public long saveForKey(SurveyEncounter se) {

        return seDao.saveForKey(se);
    }

    public void update(SurveyEncounter se) {

        update(seDao, se);
    }

    public void delete(SurveyEncounter se) {

        delete(seDao, se);
    }

    public void delete(SurveyEncounter... ses) {

        delete(seDao, ses);
    }
}
