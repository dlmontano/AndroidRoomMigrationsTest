package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.SurveyQuestionDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.SurveyQuestion;

import java.util.List;

public class SurveyQuestionRepository extends BaseRepository<SurveyQuestion> {

    private SurveyQuestionDao sqDao;

    public SurveyQuestionRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        sqDao = db.getSurveyQuestionDao();
    }

    public LiveData<List<SurveyQuestion>> getCurrentSurveyQuestionsLiveData() {

        return sqDao.getAllReactiveSurveyQuestions();
    }

    public LiveData<SurveyQuestion> getSurveyQuestionByIdLiveData(int questionId) {

        return sqDao.getReactiveSurveyQuestionById(questionId);
    }

    public LiveData<List<SurveyQuestion>> getAllSurveyQuestionsBySurveyIdLiveData(int surveyId) {

        return sqDao.getAllReactiveSurveyQuestionsBySurveyId(surveyId);
    }

    public LiveData<List<SurveyQuestion>> getActiveSurveyQuestionsBySurveyIdLiveData(int surveyId) {

        return sqDao.getReactiveActiveSurveyQuestionsBySurveyId(surveyId);
    }

    public List<SurveyQuestion> getCurrentSurveyQuestions() {

        return sqDao.getAllSurveyQuestions();
    }

    public SurveyQuestion getSurveyQuestionById(int questionId) {

        return sqDao.getSurveyQuestionById(questionId);
    }

    public List<SurveyQuestion> getAllSurveyQuestionsBySurveyId(int surveyId) {

        return sqDao.getAllSurveyQuestionsBySurveyId(surveyId);
    }

    public List<SurveyQuestion> getActiveSurveyQuestionsBySurveyId(int surveyId) {

        return sqDao.getActiveSurveyQuestionsBySurveyId(surveyId);
    }

    public void save(SurveyQuestion sq) {

        save(sqDao, sq);
    }

    public void save(SurveyQuestion... sqs) {

        save(sqDao, sqs);
    }

    public void update(SurveyQuestion sq) {

        update(sqDao, sq);
    }

    public void delete(SurveyQuestion sq) {

        delete(sqDao, sq);
    }

    public void delete(SurveyQuestion... sqs) {

        delete(sqDao, sqs);
    }
}
