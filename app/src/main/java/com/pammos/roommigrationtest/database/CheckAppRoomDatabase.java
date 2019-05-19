package com.pammos.roommigrationtest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.pammos.roommigrationtest.dao.BloodPressureMeasurementDao;
import com.pammos.roommigrationtest.dao.HeartbeatRateMeasurementDao;
import com.pammos.roommigrationtest.dao.PersonDao;
import com.pammos.roommigrationtest.dao.PersonSurveyDao;
import com.pammos.roommigrationtest.dao.SurveyDao;
import com.pammos.roommigrationtest.dao.SurveyEncounterDao;
import com.pammos.roommigrationtest.dao.SurveyQuestionAnswerDao;
import com.pammos.roommigrationtest.dao.SurveyQuestionCodedChoiceDao;
import com.pammos.roommigrationtest.dao.SurveyQuestionDao;
import com.pammos.roommigrationtest.dao.converter.Converters;
import com.pammos.roommigrationtest.model.BloodPressureMeasurement;
import com.pammos.roommigrationtest.model.HeartbeatRateMeasurement;
import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.model.PersonSurvey;
import com.pammos.roommigrationtest.model.Survey;
import com.pammos.roommigrationtest.model.SurveyEncounter;
import com.pammos.roommigrationtest.model.SurveyQuestion;
import com.pammos.roommigrationtest.model.SurveyQuestionAnswer;
import com.pammos.roommigrationtest.model.SurveyQuestionCodedChoice;

@Database(entities = {Person.class, Survey.class, BloodPressureMeasurement.class,
        HeartbeatRateMeasurement.class, SurveyQuestion.class, SurveyQuestionCodedChoice.class,
        SurveyEncounter.class, SurveyQuestionAnswer.class, PersonSurvey.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class CheckAppRoomDatabase extends RoomDatabase {

    public abstract PersonDao getPersonDao();
    public abstract SurveyDao getSurveyDao();
    public abstract BloodPressureMeasurementDao getBloodPressureMeasurementDao();
    public abstract HeartbeatRateMeasurementDao getHeartbeatRateMeasurementDao();
    public abstract SurveyQuestionDao getSurveyQuestionDao();
    public abstract SurveyQuestionCodedChoiceDao getSurveyQuestionCodedChoiceDao();
    public abstract SurveyEncounterDao getSurveyEncounterDao();
    public abstract SurveyQuestionAnswerDao getSurveyQuestionAnswerDao();
    public abstract PersonSurveyDao getPersonSurveyDao();

    private static volatile CheckAppRoomDatabase INSTANCE;

    public static CheckAppRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (CheckAppRoomDatabase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CheckAppRoomDatabase.class, "check_app_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
