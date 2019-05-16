package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "people_survey", primaryKeys = {"person_UUID", "survey_id"},
        foreignKeys = {@ForeignKey(entity = Person.class,
                parentColumns = "UUID",
                childColumns = "person_UUID",
                onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Survey.class,
                        parentColumns = "id",
                        childColumns = "survey_id",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index("person_UUID"), @Index("person_document_id"), @Index("survey_id")})
public class PersonSurvey {

    //region Fields

    /**
     *
     */
    @NonNull
    @ColumnInfo(name = "person_UUID")
    private String personUUID;

    /**
     *
     */
    @NonNull
    @ColumnInfo(name = "person_document_id")
    private String personDocumentId;

    /**
     *
     */
    @NonNull
    @ColumnInfo(name = "survey_id")
    private int surveyId;

    //endregion

    //region Constructors

    /**
     * Constructs a <code>PersonSurvey</code> object with all the fields set to <code>null</code>,
     * except for {@link #surveyId surveyId}, which is set to <code>0</code>.
     */
    public PersonSurvey() {

        personUUID = null;
        personDocumentId = null;
        surveyId = 0;
    }

    /**
     * Constructs a <code>PersonSurvey</code> object with the given parameters.
     *
     * @param personUUID
     * @param personDocumentId
     * @param surveyId
     */
    @Ignore
    public PersonSurvey(@NonNull String personUUID, @NonNull String personDocumentId,
                        @NonNull int surveyId) {

        this.personUUID = personUUID;
        this.personDocumentId = personDocumentId;
        this.surveyId = surveyId;
    }

    //endregion

    //region Getters and Setters

    public String getPersonUUID() {

        return personUUID;
    }

    public void setPersonUUID(String personUUID) {

        this.personUUID = personUUID;
    }

    public String getPersonDocumentId() {

        return personDocumentId;
    }

    public void setPersonDocumentId(String personDocumentId) {

        this.personDocumentId = personDocumentId;
    }

    public int getSurveyId() {

        return surveyId;
    }

    public void setSurveyId(int surveyId) {

        this.surveyId = surveyId;
    }

    //endregion

    //region Overridden methods

    @Override
    public boolean equals(Object o) {

        if (this == o) {

            return true;
        }

        if (o == null || getClass() != o.getClass()) {

            return false;
        }

        PersonSurvey that = (PersonSurvey) o;

        return surveyId == that.surveyId &&
                Objects.equals(personUUID, that.personUUID) &&
                Objects.equals(personDocumentId, that.personDocumentId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(personUUID, personDocumentId, surveyId);
    }

    //endregion
}
