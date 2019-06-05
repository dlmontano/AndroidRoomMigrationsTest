package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.SurveyQuestionAnswerDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.SurveyQuestionAnswer;

import java.util.List;

public class SurveyQuestionAnswerRepository extends BaseRepository<SurveyQuestionAnswer> {

    private SurveyQuestionAnswerDao sqaDao;

    public SurveyQuestionAnswerRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        sqaDao = db.getSurveyQuestionAnswerDao();
    }

    public LiveData<List<SurveyQuestionAnswer>> getAllSurveyQuestionAnswersLiveData() {

        return sqaDao.getAllReactiveSurveyQuestionAnswers();
    }

    public LiveData<SurveyQuestionAnswer> getSurveyQuestionAnswerByIdLiveData(
            long surveyQuestionAnswerId) {

        return sqaDao.getReactiveSurveyQuestionAnswerById(surveyQuestionAnswerId);
    }

    public LiveData<List<SurveyQuestionAnswer>> getSurveyQuestionAnswersByEncounterIdLiveData(
            long encounterId) {

        return sqaDao.getReactiveSurveyQuestionAnswersByEncounterId(encounterId);
    }

    public LiveData<List<SurveyQuestionAnswer>> getSurveyQuestionAnswersByQuestionIdLiveData(
            int questionId) {

        return sqaDao.getReactiveSurveyQuestionAnswersByQuestionId(questionId);
    }

    public List<SurveyQuestionAnswer> getAllSurveyQuestionAnswers() {

        return sqaDao.getAllSurveyQuestionAnswers();
    }

    public SurveyQuestionAnswer getSurveyQuestionAnswerById(long surveyQuestionAnswerId) {

        return sqaDao.getSurveyQuestionAnswerById(surveyQuestionAnswerId);
    }

    public List<SurveyQuestionAnswer> getSurveyQuestionAnswersByEncounterId(long encounterId) {

        return sqaDao.getSurveyQuestionAnswersByEncounterId(encounterId);
    }

    public List<SurveyQuestionAnswer> getSurveyQuestionAnswersByQuestionId(int questionId) {

        return sqaDao.getSurveyQuestionAnswersByQuestionId(questionId);
    }

    public void save(SurveyQuestionAnswer sqa) {

        save(sqaDao, sqa);
    }

    public void save(SurveyQuestionAnswer... sqas) {

        save(sqaDao, sqas);
    }

    public void update(SurveyQuestionAnswer sqa) {

        update(sqaDao, sqa);
    }

    public void delete(SurveyQuestionAnswer sqa) {

        delete(sqaDao, sqa);
    }

    public void delete(SurveyQuestionAnswer... sqas) {

        delete(sqaDao, sqas);
    }
}
