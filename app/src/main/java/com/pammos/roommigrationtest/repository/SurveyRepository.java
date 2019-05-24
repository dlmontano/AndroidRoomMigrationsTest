package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.SurveyDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.Survey;

import java.util.List;

public class SurveyRepository extends BaseRepository<Survey> {

    private SurveyDao surveyDao;

    public SurveyRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        surveyDao = db.getSurveyDao();
    }

    public LiveData<List<Survey>> getCurrentSurveysLiveData() {

        return surveyDao.getAllReactiveSurveys();
    }

    public LiveData<Survey> getSurveyByIdLiveData(int surveyId) {

        return surveyDao.getReactiveSurveyById(surveyId);
    }

    public List<Survey> getCurrentSurveys() {

        return surveyDao.getAllSurveys();
    }

    public Survey getSurveyById(int surveyId) {

        return surveyDao.getSurveyById(surveyId);
    }

    public void save(Survey survey) {

        save(surveyDao, survey);
    }

    public void save(Survey... surveys) {

        save(surveyDao, surveys);
    }

    public void update(Survey survey) {

        update(surveyDao, survey);
    }

    public void delete(Survey survey) {

        delete(surveyDao, survey);
    }

    public void delete(Survey... surveys) {

        delete(surveyDao, surveys);
    }
}
