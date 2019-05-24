package com.pammos.roommigrationtest.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pammos.roommigrationtest.dao.PersonDao;
import com.pammos.roommigrationtest.database.CheckAppRoomDatabase;
import com.pammos.roommigrationtest.model.Person;

import java.util.List;

public class PeopleRepository extends BaseRepository<Person> {

    private PersonDao personDao;

    public PeopleRepository(Application application) {
        CheckAppRoomDatabase db = CheckAppRoomDatabase.getDatabase(application);

        personDao = db.getPersonDao();
    }

    public LiveData<List<Person>> getCurrentPeopleLiveData() {

        return personDao.getAllReactivePeople();
    }

    public LiveData<Person> getPersonByUUIDLiveData(String uuid) {

        return personDao.getReactivePersonByUUID(uuid);
    }

    public LiveData<Person> getPersonByDocumentIdLiveData(String documentId) {

        return personDao.getReactivePersonByDocumentId(documentId);
    }

    public List<Person> getCurrentPeople() {

        return personDao.getAllPeople();
    }

    public Person getPersonByUUID(String uuid) {

        return personDao.getPersonByUUID(uuid);
    }

    public Person getPersonByDocumentId(String documentId) {

        return personDao.getPersonByDocumentId(documentId);
    }

    public void save(Person person) {

        save(personDao, person);
    }

    public void save(Person... people) {

        save(personDao, people);
    }

    public void update(Person person) {

        update(personDao, person);
    }

    public void delete(Person person) {

        delete(personDao, person);
    }

    public void delete(Person... people) {

        delete(personDao, people);
    }
}
