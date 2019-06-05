package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.SurveyQuestionCodedChoiceDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.SurveyQuestionCodedChoice;

import java.util.List;

public class SurveyQuestionCodedChoiceRepository extends BaseRepository<SurveyQuestionCodedChoice> {

    private SurveyQuestionCodedChoiceDao sqccDao;

    public SurveyQuestionCodedChoiceRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        sqccDao = db.getSurveyQuestionCodedChoiceDao();
    }

    public LiveData<List<SurveyQuestionCodedChoice>> getAllSurveyQuestionCodedChoicesLiveData() {

        return sqccDao.getAllReactiveSurveyQuestionCodedChoices();
    }

    public LiveData<SurveyQuestionCodedChoice> getSurveyQuestionCodedChoiceByIdLiveData(int sqccId) {

        return sqccDao.getReactiveSurveyQuestionCodedChoiceById(sqccId);
    }

    public LiveData<List<SurveyQuestionCodedChoice>> getSurveyQuestionCodedChoicesByQuestionIdLiveData(int questionId) {

        return sqccDao.getReactiveSurveyQuestionCodedChoiceBySurveyId(questionId);
    }

    public LiveData<List<SurveyQuestionCodedChoice>> getSurveyQuestionCodedChoiceBySurveyIdLiveData(int surveyId) {

        return sqccDao.getReactiveSurveyQuestionCodedChoiceBySurveyId(surveyId);
    }

    public List<SurveyQuestionCodedChoice> getAllSurveyQuestionCodedChoices() {

        return sqccDao.getAllSurveyQuestionCodedChoices();
    }

    public SurveyQuestionCodedChoice getSurveyQuestionCodedChoiceById(int sqccId) {

        return sqccDao.getSurveyQuestionCodedChoiceById(sqccId);
    }

    public List<SurveyQuestionCodedChoice> getSurveyQuestionCodedChoicesByQuestionId(int questionId) {

        return sqccDao.getSurveyQuestionCodedChoiceBySurveyId(questionId);
    }

    public List<SurveyQuestionCodedChoice> getSurveyQuestionCodedChoiceBySurveyId(int surveyId) {

        return sqccDao.getSurveyQuestionCodedChoiceBySurveyId(surveyId);
    }

    public void save(SurveyQuestionCodedChoice sqcc) {

        save(sqccDao, sqcc);
    }

    public void save(SurveyQuestionCodedChoice... sqccs) {

        save(sqccDao, sqccs);
    }

    public void update(SurveyQuestionCodedChoice sqcc) {

        update(sqccDao, sqcc);
    }

    public void delete(SurveyQuestionCodedChoice sqcc) {

        delete(sqccDao, sqcc);
    }

    public void delete(SurveyQuestionCodedChoice... sqccs) {

        delete(sqccDao, sqccs);
    }
}
