package com.pammos.roommigrationtest;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.pammos.roommigrationtest.dao.BloodPressureMeasurementDao;
import com.pammos.roommigrationtest.dao.HeartbeatRateMeasurementDao;
import com.pammos.roommigrationtest.dao.PersonDao;
import com.pammos.roommigrationtest.dao.PersonSurveyDao;
import com.pammos.roommigrationtest.dao.SurveyDao;
import com.pammos.roommigrationtest.dao.SurveyEncounterDao;
import com.pammos.roommigrationtest.dao.SurveyQuestionAnswerDao;
import com.pammos.roommigrationtest.dao.SurveyQuestionCodedChoiceDao;
import com.pammos.roommigrationtest.dao.SurveyQuestionDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.BloodPressureMeasurement;
import com.pammos.roommigrationtest.model.HeartbeatRateMeasurement;
import com.pammos.roommigrationtest.model.Person;
import com.pammos.roommigrationtest.model.PersonSurvey;
import com.pammos.roommigrationtest.model.Survey;
import com.pammos.roommigrationtest.model.SurveyEncounter;
import com.pammos.roommigrationtest.model.SurveyQuestion;
import com.pammos.roommigrationtest.model.SurveyQuestionAnswer;
import com.pammos.roommigrationtest.model.SurveyQuestionCodedChoice;
import com.pammos.roommigrationtest.util.LiveDataTestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class CheckAppDatabaseTest {

    private PersonDao personDao;
    private SurveyDao surveyDao;
    private BloodPressureMeasurementDao bloodPressureMeasurementDao;
    private HeartbeatRateMeasurementDao heartbeatRateMeasurementDao;
    private SurveyQuestionDao surveyQuestionDao;
    private SurveyQuestionCodedChoiceDao surveyQuestionCodedChoiceDao;
    private SurveyEncounterDao surveyEncounterDao;
    private SurveyQuestionAnswerDao surveyQuestionAnswerDao;
    private PersonSurveyDao personSurveyDao;
    private CheckAppRoomDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();

        db = Room.inMemoryDatabaseBuilder(context, CheckAppRoomDatabase.class).build();
        personDao = db.getPersonDao();
        surveyDao = db.getSurveyDao();
        bloodPressureMeasurementDao = db.getBloodPressureMeasurementDao();
        heartbeatRateMeasurementDao = db.getHeartbeatRateMeasurementDao();
        surveyQuestionDao = db.getSurveyQuestionDao();
        surveyQuestionCodedChoiceDao = db.getSurveyQuestionCodedChoiceDao();
        surveyEncounterDao = db.getSurveyEncounterDao();
        surveyQuestionAnswerDao = db.getSurveyQuestionAnswerDao();
        personSurveyDao = db.getPersonSurveyDao();
    }

    @After
    public void closeDb() throws IOException {

        db.close();
    }

    //region Tests

    //region Person

    @Test
    public void savePersonAndReadFromList() {

        Person person = createPerson("1111");

        personDao.save(person);

        try {
            List<Person> people = LiveDataTestUtils.getValue(personDao.getAllReactivePeople());

            List<Person> people2 = personDao.getAllPeople();

            assertThat(people, hasItem(person));
            assertThat(people2, hasItem(person));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void savePersonAndReadFromDbByUUID() {

        db.clearAllTables();

        Person person = createPerson("1111");

        personDao.save(person);

        try {
            Person readPerson = LiveDataTestUtils.getValue(
                    personDao.getReactivePersonByUUID(person.getUUID()));

            Person readPerson2 = personDao.getPersonByUUID(person.getUUID());

            assertThat(readPerson, equalTo(person));
            assertThat(readPerson2, equalTo(person));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void savePersonAndReadFromDbByDocumentId() {

        db.clearAllTables();

        Person person = createPerson("1111");

        personDao.save(person);

        try {
            Person readPerson = LiveDataTestUtils.getValue(
                    personDao.getReactivePersonByDocumentId(person.getDocumentId()));

            Person readPerson2 = personDao.getPersonByDocumentId(person.getDocumentId());

            assertThat(readPerson, equalTo(person));
            assertThat(readPerson2, equalTo(person));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //endregion

    //region Survey

    @Test
    public void saveSurveyAndReadFromList() {

        db.clearAllTables();

        Survey survey = createSurvey(1);

        surveyDao.save(survey);

        try {
            List<Survey> surveys = LiveDataTestUtils.getValue(surveyDao.getAllReactiveSurveys());
            List<Survey> surveys2 = surveyDao.getAllSurveys();

            assertThat(surveys, hasItem(survey));
            assertThat(surveys2, hasItem(survey));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void saveSurveyAndReadFromDb() {

        db.clearAllTables();

        Survey survey = createSurvey(1);

        surveyDao.save(survey);

        try {
            Survey readSurvey = LiveDataTestUtils.getValue(
                    surveyDao.getReactiveSurveyById(survey.getSurveyId()));

            Survey readSurvey2 = surveyDao.getSurveyById(survey.getSurveyId());

            assertThat(readSurvey, equalTo(survey));
            assertThat(readSurvey2, equalTo(survey));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //endregion

    //region BloodPressureMeasurement

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void saveBPMWithoutPerson() {

        db.clearAllTables();

        BloodPressureMeasurement bpm = createBloodPressureMeasurement("1111");

        bloodPressureMeasurementDao.save(bpm);
    }

    @Test
    public void savePersonAndBPMAndReadFromList() {

        db.clearAllTables();

        Person person = createPerson("1111");
        BloodPressureMeasurement bpm = createBloodPressureMeasurement(person.getUUID());

        personDao.save(person);
        bloodPressureMeasurementDao.save(bpm);

        try {
            List<BloodPressureMeasurement> bpmList = LiveDataTestUtils.getValue(
                    bloodPressureMeasurementDao.getAllBPMs());

            assertThat(bpmList, hasItem(bpm));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void savePersonAndBPMAndCheckDates() {

        db.clearAllTables();

        Person person = createPerson("1111");
        BloodPressureMeasurement bpm = createBloodPressureMeasurement(person.getUUID());

        personDao.save(person);
        bloodPressureMeasurementDao.save(bpm);

        try {
            Date latestBPMDate = LiveDataTestUtils.getValue(
                    bloodPressureMeasurementDao.getLatestBPMDateByPersonUUID(person.getUUID()));

            assertThat(latestBPMDate, equalTo(bpm.getTimestamp()));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //endregion

    //region HeartbeatRateMeasurement

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void saveHRMWithoutPerson() {

        db.clearAllTables();

        HeartbeatRateMeasurement hrm = createHeartbeatRateMeasurement("1111");

        heartbeatRateMeasurementDao.save(hrm);
    }

    @Test
    public void savePersonAndHRMAndReadFromList() {

        db.clearAllTables();

        Person person = createPerson("1111");
        HeartbeatRateMeasurement hrm = createHeartbeatRateMeasurement(person.getUUID());

        personDao.save(person);
        heartbeatRateMeasurementDao.save(hrm);

        try {
            List<HeartbeatRateMeasurement> hrmList = LiveDataTestUtils.getValue(
                    heartbeatRateMeasurementDao.getAllHRMs());

            assertThat(hrmList, hasItem(hrm));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void savePersonAndHRMAndCheckDates() {

        db.clearAllTables();

        Person person = createPerson("1111");
        HeartbeatRateMeasurement hrm = createHeartbeatRateMeasurement(person.getUUID());

        personDao.save(person);
        heartbeatRateMeasurementDao.save(hrm);

        try {
            Date latestBPMDate = LiveDataTestUtils.getValue(
                    heartbeatRateMeasurementDao.getLatestHRMDateByPersonUUID(person.getUUID()));

            assertThat(latestBPMDate, equalTo(hrm.getTimestamp()));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //endregion

    //region SurveyQuestion

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void saveSurveyQuestionWithoutSurvey() {

        db.clearAllTables();

        SurveyQuestion sq = createSurveyQuestion(1, 1);

        surveyQuestionDao.save(sq);
    }

    @Test
    public void saveSurveyAndSurveyQuestionAndReadFromLists() {

        db.clearAllTables();

        Survey survey = createSurvey(1);
        SurveyQuestion sq = createSurveyQuestion(1, survey.getSurveyId());

        surveyDao.save(survey);
        surveyQuestionDao.save(sq);

        try {
            List<SurveyQuestion> sqList = LiveDataTestUtils.getValue(
                    surveyQuestionDao.getAllReactiveSurveyQuestions());

            List<SurveyQuestion> sqBySurveyIdList = LiveDataTestUtils.getValue(
                    surveyQuestionDao.getReactiveActiveSurveyQuestionsBySurveyId(survey.getSurveyId()));

            List<SurveyQuestion> sqList2 = surveyQuestionDao.getAllSurveyQuestions();

            List<SurveyQuestion> sqBySurveyIdList2 = surveyQuestionDao
                    .getActiveSurveyQuestionsBySurveyId(survey.getSurveyId());

            assertThat(sqList, hasItem(sq));
            assertThat(sqBySurveyIdList, hasItem(sq));
            assertThat(sqList2, hasItem(sq));
            assertThat(sqBySurveyIdList2, hasItem(sq));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void saveSurveyAndSurveyQuestionAndReadFromDb() {

        db.clearAllTables();

        Survey survey = createSurvey(1);
        SurveyQuestion sq = createSurveyQuestion(1, survey.getSurveyId());

        surveyDao.save(survey);
        surveyQuestionDao.save(sq);

        try {
            SurveyQuestion readSq = LiveDataTestUtils.getValue(
                    surveyQuestionDao.getReactiveSurveyQuestionById(sq.getQuestionId()));

            SurveyQuestion readSq2 = surveyQuestionDao.getSurveyQuestionById(sq.getQuestionId());

            assertThat(readSq, equalTo(sq));
            assertThat(readSq2, equalTo(sq));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //endregion

    //region SurveyQuestionCodedChoice

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void saveSurveyQuestionCodedChoiceWithoutSurveyNorSurveyQuestion() {

        db.clearAllTables();

        SurveyQuestionCodedChoice sqcc = createSurveyQuestionCodedChoice(1, 1);

        surveyQuestionCodedChoiceDao.save(sqcc);
    }

    @Test
    public void saveSurveyAndSurveyQuestionAndSurveyQuestionCodedChoiceAndReadFromList() {

        db.clearAllTables();

        Survey survey = createSurvey(1);
        SurveyQuestion sq = createSurveyQuestion(1, survey.getSurveyId());
        SurveyQuestionCodedChoice sqcc = createSurveyQuestionCodedChoice(1,
                sq.getQuestionId());

        surveyDao.save(survey);
        surveyQuestionDao.save(sq);
        surveyQuestionCodedChoiceDao.save(sqcc);

        try {
            List<SurveyQuestionCodedChoice> sqccList = LiveDataTestUtils
                    .getValue(surveyQuestionCodedChoiceDao
                            .getReactiveSurveyQuestionCodedChoicesByQuestionId(sq.getQuestionId()));

            List<SurveyQuestionCodedChoice> sqccList2 = surveyQuestionCodedChoiceDao
                    .getSurveyQuestionCodedChoicesByQuestionId(sq.getQuestionId());

            assertThat(sqccList, hasItem(sqcc));
            assertThat(sqccList2, hasItem(sqcc));

        } catch (InterruptedException ie) {

            ie.printStackTrace();
        }
    }

    @Test
    public void saveSurveyAndSurveyQuestionAndSurveyQuestionCodedChoiceAndReadFromDb() {

        db.clearAllTables();

        Survey survey = createSurvey(1);
        SurveyQuestion sq = createSurveyQuestion(1, survey.getSurveyId());
        SurveyQuestionCodedChoice sqcc = createSurveyQuestionCodedChoice(1,
                sq.getQuestionId());

        surveyDao.save(survey);
        surveyQuestionDao.save(sq);
        surveyQuestionCodedChoiceDao.save(sqcc);

        try {
            SurveyQuestionCodedChoice readSqcc = LiveDataTestUtils.getValue(
                    surveyQuestionCodedChoiceDao.getReactiveSurveyQuestionCodedChoiceById(
                            sqcc.getSurveyQuestionCodedChoiceId()));

            SurveyQuestionCodedChoice readSqcc2 = surveyQuestionCodedChoiceDao
                    .getSurveyQuestionCodedChoiceById(sqcc.getSurveyQuestionCodedChoiceId());

            assertThat(readSqcc, equalTo(sqcc));
            assertThat(readSqcc2, equalTo(sqcc));

        } catch (InterruptedException ie) {

            ie.printStackTrace();
        }
    }

    //endregion

    //region SurveyEncounter

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void saveSurveyEncounterWithoutSurveyNorPerson() {

        db.clearAllTables();

        SurveyEncounter se = createSurveyEncounter(1, "1111");

        surveyEncounterDao.save(se);
    }

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void savePersonAndSurveyEncounterWithoutSurvey() {

        db.clearAllTables();

        Person person = createPerson("1111");
        SurveyEncounter se = createSurveyEncounter(1, person.getUUID());

        personDao.save(person);
        surveyEncounterDao.save(se);
    }

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void saveSurveyAndSurveyEncounterWithoutPerson() {

        db.clearAllTables();

        Survey survey = createSurvey(1);
        SurveyEncounter se = createSurveyEncounter(survey.getSurveyId(), "1111");

        surveyDao.save(survey);
        surveyEncounterDao.save(se);
    }

    @Test
    public void savePersonAndSurveyAndSurveyEncounterAndReadFromLists() {

        db.clearAllTables();

        Person person = createPerson("1111");
        Survey survey = createSurvey(1);
        SurveyEncounter se = createSurveyEncounter(survey.getSurveyId(), person.getUUID());

        personDao.save(person);
        surveyDao.save(survey);
        surveyEncounterDao.save(se);

        try {
            List<SurveyEncounter> seListByPersonUUID = LiveDataTestUtils.getValue(
                    surveyEncounterDao.getReactiveSurveyEncountersByPersonUUID(person.getUUID()));

            List<SurveyEncounter> seListByPersonUUIDAndSurveyId = LiveDataTestUtils.getValue(
                    surveyEncounterDao.getReactiveSurveyEncountersByPersonUUIDAndSurveyId(person.getUUID(),
                            survey.getSurveyId()));

            List<SurveyEncounter> seUnsyncedListByPersonUUID = LiveDataTestUtils.getValue(
                    surveyEncounterDao.getReactiveUnsynchronizedSurveyEncountersByPersonUUID(
                            person.getUUID()));

            List<SurveyEncounter> seUnsyncedListByPersonUUID2 = surveyEncounterDao
                    .getUnsynchronizedSurveyEncountersByPersonUUID(person.getUUID());

            assertThat(seListByPersonUUID, hasItem(se));
            assertThat(seListByPersonUUIDAndSurveyId, hasItem(se));
            assertThat(seUnsyncedListByPersonUUID, hasItem(se));
            assertThat(seUnsyncedListByPersonUUID2, hasItem(se));

        } catch (InterruptedException ie) {

            ie.printStackTrace();
        }
    }

    @Test
    public void savePersonAndSurveyAndSurveyEncounterAndReadFromDb() {

        db.clearAllTables();

        Person person = createPerson("1111");
        Survey survey = createSurvey(1);
        SurveyEncounter se = createSurveyEncounter(survey.getSurveyId(), person.getUUID());

        personDao.save(person);
        surveyDao.save(survey);
        surveyEncounterDao.save(se);

        try {
            SurveyEncounter readSe = LiveDataTestUtils.getValue(
                    surveyEncounterDao.getReactiveSurveyEncounterById(1));

            SurveyEncounter readSe2 = surveyEncounterDao.getSurveyEncounterById(1);

            assertThat(readSe, equalTo(se));
            assertThat(readSe2, equalTo(se));

        } catch (InterruptedException ie) {

            ie.printStackTrace();
        }
    }

    //endregion

    //region SurveyQuestionAnswer

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void saveSurveyQuestionAnswerWithoutSurveyNorSurveyQuestionNorSurveyEncounterNorPerson() {

        db.clearAllTables();

        SurveyQuestionAnswer sqa = createSurveyQuestionAnswer(1, 1);

        surveyQuestionAnswerDao.save(sqa);
    }

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void savePersonAndSurveyAndSurveyQuestionAndSurveyQuestionAnswerWithoutSurveyEncounter() {

        db.clearAllTables();

        Person person = createPerson("1111");
        Survey survey = createSurvey(1);
        SurveyQuestion sq = createSurveyQuestion(1, survey.getSurveyId());
        SurveyQuestionAnswer sqa = createSurveyQuestionAnswer(1, 1);

        personDao.save(person);
        surveyDao.save(survey);
        surveyQuestionDao.save(sq);
        surveyQuestionAnswerDao.save(sqa);
    }

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void savePersonAndSurveyAndSurveyEncounterAndSurveyQuestionAnswerWithoutSurveyQuestion() {

        db.clearAllTables();

        Person person = createPerson("1111");
        Survey survey = createSurvey(1);
        SurveyEncounter se = createSurveyEncounter(survey.getSurveyId(), person.getUUID());
        SurveyQuestionAnswer sqa = createSurveyQuestionAnswer(1, 1);

        personDao.save(person);
        surveyDao.save(survey);
        surveyEncounterDao.save(se);
        surveyQuestionAnswerDao.save(sqa);
    }

    @Test
    public void savePersonAndSurveyAndAndSurveyQuestionAndSurveyEncounterAndSurveyQuestionAnswerAndReadFromLists() {

        db.clearAllTables();

        Person person = createPerson("1111");
        Survey survey = createSurvey(1);
        SurveyQuestion sq = createSurveyQuestion(1, survey.getSurveyId());
        SurveyEncounter se = createSurveyEncounter(survey.getSurveyId(), person.getUUID());
        SurveyQuestionAnswer sqa = createSurveyQuestionAnswer(1, sq.getQuestionId());

        personDao.save(person);
        surveyDao.save(survey);
        surveyQuestionDao.save(sq);
        surveyEncounterDao.save(se);
        surveyQuestionAnswerDao.save(sqa);

        try {
            List<SurveyQuestionAnswer> sqaListByQuestionId = LiveDataTestUtils.getValue(
                    surveyQuestionAnswerDao.getReactiveSurveyQuestionAnswersByQuestionId(
                            sq.getQuestionId()));

            List<SurveyQuestionAnswer> sqaListByEncounterId = LiveDataTestUtils.getValue(
                    surveyQuestionAnswerDao.getReactiveSurveyQuestionAnswersByEncounterId(1));

            List<SurveyQuestionAnswer> sqaListByQuestionId2 = surveyQuestionAnswerDao
                    .getSurveyQuestionAnswersByQuestionId(sq.getQuestionId());

            List<SurveyQuestionAnswer> sqaListByEncounterId2 = surveyQuestionAnswerDao
                    .getSurveyQuestionAnswersByEncounterId(1);

            assertThat(sqaListByQuestionId, hasItem(sqa));
            assertThat(sqaListByEncounterId, hasItem(sqa));
            assertThat(sqaListByQuestionId2, hasItem(sqa));
            assertThat(sqaListByEncounterId2, hasItem(sqa));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void savePersonAndSurveyAndAndSurveyQuestionAndSurveyEncounterAndSurveyQuestionAnswerAndReadFromDb() {

        db.clearAllTables();

        Person person = createPerson("1111");
        Survey survey = createSurvey(1);
        SurveyQuestion sq = createSurveyQuestion(1, survey.getSurveyId());
        SurveyEncounter se = createSurveyEncounter(survey.getSurveyId(), person.getUUID());
        SurveyQuestionAnswer sqa = createSurveyQuestionAnswer(1, sq.getQuestionId());

        personDao.save(person);
        surveyDao.save(survey);
        surveyQuestionDao.save(sq);
        surveyEncounterDao.save(se);
        surveyQuestionAnswerDao.save(sqa);

        try {
            SurveyQuestionAnswer readSqa = LiveDataTestUtils.getValue(surveyQuestionAnswerDao
                    .getReactiveSurveyQuestionAnswerById(1));

            SurveyQuestionAnswer readSqa2 = surveyQuestionAnswerDao
                    .getSurveyQuestionAnswerById(1);

            assertThat(readSqa, equalTo(sqa));
            assertThat(readSqa2, equalTo(sqa));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //endregion

    //region PersonSurvey

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void savePersonSurveyWithoutPersonNorSurvey() {

        db.clearAllTables();

        PersonSurvey ps = createPersonSurvey("1111", 1);

        personSurveyDao.save(ps);
    }

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void savePersonAndPersonSurveyWithoutSurvey() {

        db.clearAllTables();

        Person person = createPerson("1111");
        PersonSurvey ps = createPersonSurvey(person.getUUID(), 1);

        personDao.save(person);
        personSurveyDao.save(ps);
    }

    @Test(expected = android.database.sqlite.SQLiteConstraintException.class)
    public void saveSurveyAndPersonSurveyWithoutPerson() {

        db.clearAllTables();

        Survey survey = createSurvey(1);
        PersonSurvey ps = createPersonSurvey("1111", survey.getSurveyId());

        surveyDao.save(survey);
        personSurveyDao.save(ps);
    }

    @Test
    public void savePersonAndSurveyAndPersonSurveyAndReadFromLists() {

        db.clearAllTables();

        Person person = createPerson("1111");
        Survey survey = createSurvey(1);
        PersonSurvey ps = createPersonSurvey(person.getUUID(), survey.getSurveyId());

        personDao.save(person);
        surveyDao.save(survey);
        personSurveyDao.save(ps);

        try {
            List<Person> personList = LiveDataTestUtils.getValue(
                    personSurveyDao.getReactivePeopleBySurveyId(survey.getSurveyId()));

            List<Survey> surveyListByPersonUUID = LiveDataTestUtils.getValue(
                    personSurveyDao.getReactiveActiveSurveysByPersonUUID(person.getUUID()));

            List<Survey> surveyListByPersonDocumentId = LiveDataTestUtils.getValue(
                    personSurveyDao.getReactiveActiveSurveysByPersonDocumentId(person.getDocumentId()));

            List<Person> personList2 = personSurveyDao.getPeopleBySurveyId(survey.getSurveyId());

            List<Survey> surveyListByPersonUUID2 = personSurveyDao
                    .getActiveSurveysByPersonUUID(person.getUUID());

            List<Survey> surveyListByPersonDocumentId2 = personSurveyDao
                    .getActiveSurveysByPersonDocumentId(person.getDocumentId());

            assertThat(personList, hasItem(person));
            assertThat(surveyListByPersonUUID, hasItem(survey));
            assertThat(surveyListByPersonDocumentId, hasItem(survey));
            assertThat(personList2, hasItem(person));
            assertThat(surveyListByPersonUUID2, hasItem(survey));
            assertThat(surveyListByPersonDocumentId2, hasItem(survey));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void savePersonAndInactiveSurveyAndPersonSurveyAndReadFromLists() {

        db.clearAllTables();

        Person person = createPerson("1111");
        Survey survey = createSurvey(1);
        PersonSurvey ps = createPersonSurvey(person.getUUID(), survey.getSurveyId());

        survey.setActive(false);

        personDao.save(person);
        surveyDao.save(survey);
        personSurveyDao.save(ps);

        try {
            List<Person> personList = LiveDataTestUtils.getValue(
                    personSurveyDao.getReactivePeopleBySurveyId(survey.getSurveyId()));

            List<Survey> surveyListByPersonUUID = LiveDataTestUtils.getValue(
                    personSurveyDao.getReactiveActiveSurveysByPersonUUID(person.getUUID()));

            List<Survey> surveyListByPersonDocumentId = LiveDataTestUtils.getValue(
                    personSurveyDao.getReactiveActiveSurveysByPersonDocumentId(person.getDocumentId()));

            List<Person> personList2 = personSurveyDao.getPeopleBySurveyId(survey.getSurveyId());

            List<Survey> surveyListByPersonUUID2 = personSurveyDao
                    .getActiveSurveysByPersonUUID(person.getUUID());

            List<Survey> surveyListByPersonDocumentId2 = personSurveyDao
                    .getActiveSurveysByPersonDocumentId(person.getDocumentId());

            assertThat(personList, hasItem(person));
            assertThat(surveyListByPersonUUID, not(hasItem(survey)));
            assertThat(surveyListByPersonDocumentId, not(hasItem(survey)));
            assertThat(personList2, hasItem(person));
            assertThat(surveyListByPersonUUID2, not(hasItem(survey)));
            assertThat(surveyListByPersonDocumentId2, not(hasItem(survey)));

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //endregion

    //endregion

    //region Model objects' creators

    private Person createPerson(String UUID) {

        Person createdPerson = new Person();

        createdPerson.setUUID(UUID);
        createdPerson.setDocumentId(UUID);
        createdPerson.setFirstName("FirstName");
        createdPerson.setSecondName("SecondName");
        createdPerson.setFirstLastName("FirstLastName");
        createdPerson.setBirthDate(new Date());
        createdPerson.setGender(1);
        createdPerson.setHasToChangePassword(false);
        createdPerson.setToken("TOKEN");
        createdPerson.setLatestRefreshDate(new Date());

        return createdPerson;
    }

    private Survey createSurvey(int surveyId) {

        Survey createdSurvey = new Survey();

        createdSurvey.setSurveyId(surveyId);
        createdSurvey.setStartDate("2019-01-01 00:00:00.000");
        createdSurvey.setSurveyDescription("Survey Description");
        createdSurvey.setSurveyName("Survey Name");
        createdSurvey.setSurveyType(1);
        createdSurvey.setPublished(true);
        createdSurvey.setActive(true);
        createdSurvey.setLatestRefreshDate(new Date());

        return createdSurvey;
    }

    private BloodPressureMeasurement createBloodPressureMeasurement(String personUUID) {

        BloodPressureMeasurement createdBpm = new BloodPressureMeasurement();

        createdBpm.setPersonUUID(personUUID);
        createdBpm.setPersonDocumentId(personUUID);
        createdBpm.setTimestamp(new Date());
        createdBpm.setSystolicPressure(120);
        createdBpm.setDiastolicPressure(80);

        return createdBpm;
    }

    private HeartbeatRateMeasurement createHeartbeatRateMeasurement(String personUUID) {

        HeartbeatRateMeasurement createdHrm = new HeartbeatRateMeasurement();

        createdHrm.setPersonUUID(personUUID);
        createdHrm.setPersonDocumentId(personUUID);
        createdHrm.setTimestamp(new Date());
        createdHrm.setHeartbeatRate(70);

        return createdHrm;
    }

    private SurveyQuestion createSurveyQuestion(int questionId, int surveyId) {

        SurveyQuestion createdSurveyQuestion = new SurveyQuestion();

        createdSurveyQuestion.setQuestionId(questionId);
        createdSurveyQuestion.setQuestionName("Question Name");
        createdSurveyQuestion.setName("Name");
        createdSurveyQuestion.setSurveyId(surveyId);
        createdSurveyQuestion.setConceptId(1);
        createdSurveyQuestion.setConceptClassId(1);
        createdSurveyQuestion.setConceptDataTypeId(1);
        createdSurveyQuestion.setIsMandatory(1);
        createdSurveyQuestion.setActive(true);
        createdSurveyQuestion.setLatestRefreshDate(new Date());

        return createdSurveyQuestion;
    }

    private SurveyQuestionCodedChoice createSurveyQuestionCodedChoice(int sqccId, int questionId) {

        SurveyQuestionCodedChoice createdSqcc = new SurveyQuestionCodedChoice();

        createdSqcc.setSurveyQuestionCodedChoiceId(sqccId);
        createdSqcc.setQuestionName("Question Name");
        createdSqcc.setName("Name");
        createdSqcc.setLocale("Locale");
        createdSqcc.setSurveyId(1);
        createdSqcc.setQuestionId(questionId);
        createdSqcc.setLatestRefreshDate(new Date());

        return createdSqcc;
    }

    private SurveyEncounter createSurveyEncounter(int surveyId, String personUUID) {

        SurveyEncounter createdSurveyEncounter = new SurveyEncounter();

        createdSurveyEncounter.setSurveyId(surveyId);
        createdSurveyEncounter.setTimestamp(new Date());
        createdSurveyEncounter.setType(1);
        createdSurveyEncounter.setSynchronized(false);
        createdSurveyEncounter.setName("Name");
        createdSurveyEncounter.setPersonUUID(personUUID);

        return createdSurveyEncounter;
    }

    private SurveyQuestionAnswer createSurveyQuestionAnswer(int encounterId, int questionId) {

        SurveyQuestionAnswer createdSurveyQuestionAnswer = new SurveyQuestionAnswer();

        createdSurveyQuestionAnswer.setEncounterId(encounterId);
        createdSurveyQuestionAnswer.setQuestionId(questionId);
        createdSurveyQuestionAnswer.setAnswerValue("Answer");

        return createdSurveyQuestionAnswer;
    }

    private PersonSurvey createPersonSurvey(String personUUID, int surveyId) {

        PersonSurvey createdPersonSurvey = new PersonSurvey();

        createdPersonSurvey.setPersonUUID(personUUID);
        createdPersonSurvey.setPersonDocumentId(personUUID);
        createdPersonSurvey.setSurveyId(surveyId);

        return createdPersonSurvey;
    }

    //endregion
}
