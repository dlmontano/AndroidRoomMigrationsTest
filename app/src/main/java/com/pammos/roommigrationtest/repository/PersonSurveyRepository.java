package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.PersonSurveyDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.PersonSurvey;

import java.util.List;

public class PersonSurveyRepository extends BaseRepository<PersonSurvey> {

    private PersonSurveyDao psDao;

    public PersonSurveyRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        psDao = db.getPersonSurveyDao();
    }

    public LiveData<List<PersonSurvey>> getCurrentPeopleSurveysLiveData() {

        return psDao.getReactiveAllPeopleSurveys();
    }

    public LiveData<PersonSurvey> getPersonSurveyBySurveyIdAndPersonUUIDLiveData(
            int surveyId, String personUUID) {

        return psDao.getReactivePersonSurveyBySurveyIdAndPersonUUID(surveyId, personUUID);
    }

    public List<PersonSurvey> getCurrentPeopleSurveys() {

        return psDao.getAllPeopleSurveys();
    }

    public PersonSurvey getPersonSurveyBySurveyIdAndPersonUUID(
            int surveyId, String personUUID) {

        return psDao.getPersonSurveyBySurveyIdAndPersonUUID(surveyId, personUUID);
    }

    public void save(PersonSurvey ps) {

        save(psDao, ps);
    }

    public void save(PersonSurvey... pss) {

        save(psDao, pss);
    }

    public void update(PersonSurvey ps) {

        update(psDao, ps);
    }

    public void delete(PersonSurvey ps) {

        delete(psDao, ps);
    }

    public void delete(PersonSurvey... pss) {

        delete(psDao, pss);
    }
}
