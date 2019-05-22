package com.pammos.roommigrationtest.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.pammos.roommigrationtest.model.Person;

import java.util.List;

@Dao
public interface PersonDao extends BaseDao<Person> {

    @Query("Select * FROM people")
    LiveData<List<Person>> getAllReactivePeople();

    @Query("SELECT * FROM people WHERE UUID = :personUUID")
    LiveData<Person> getReactivePersonByUUID(String personUUID);

    @Query("SELECT * FROM people WHERE document_id = :documentId")
    LiveData<Person> getReactivePersonByDocumentId(String documentId);

    @Query("Select * FROM people")
    List<Person> getAllPeople();

    @Query("SELECT * FROM people WHERE UUID = :personUUID")
    Person getPersonByUUID(String personUUID);

    @Query("SELECT * FROM people WHERE document_id = :documentId")
    Person getPersonByDocumentId(String documentId);

    @Query("SELECT * FROM people WHERE document_id = :documentId")
    Person hasPerson(String documentId);
}
