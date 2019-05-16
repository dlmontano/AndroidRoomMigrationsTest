package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "survey_encounters",
        foreignKeys = {@ForeignKey(entity = Person.class,
                childColumns = "person_UUID",
                parentColumns = "UUID",
                onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Survey.class,
                        childColumns = "survey_id",
                        parentColumns = "id",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index("person_UUID"), @Index("survey_id")})
public class SurveyEncounter {

    //region Fields

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long encounterId;

    @ColumnInfo(name = "survey_id")
    private int surveyId;

    private Date timestamp;

    private int type;

    @ColumnInfo(name = "is_synchronized")
    private boolean isSynchronized;

    private String name;

    @ColumnInfo(name = "person_UUID")
    @NonNull
    private String personUUID;

    //endregion

    //region Constructors

    /**
     * Constructs a <code>SurveyEncounter</code> object with all the numeric fields set to
     * <code>0</code>, all the <code>Object</code> fields set to <code>null</code> and the
     * isSynchronized field set to <code>false</code>.
     */
    public SurveyEncounter() {

        encounterId = 0l;
        surveyId = 0;
        timestamp = null;
        type = 0;
        isSynchronized = false;
        name = null;
        personUUID = null;
    }

    /**
     * Constructs a <code>SurveyEncounter</code> object with the given parameters.
     *
     * @param encounterId
     * @param surveyId
     * @param timestamp
     * @param type
     * @param isSynchronized
     * @param name
     * @param personUUID
     */
    @Ignore
    public SurveyEncounter(long encounterId, int surveyId, Date timestamp, int type,
                           boolean isSynchronized, String name, @NonNull String personUUID) {

        this.encounterId = encounterId;
        this.surveyId = surveyId;
        this.timestamp = timestamp;
        this.type = type;
        this.isSynchronized = isSynchronized;
        this.name = name;
        this.personUUID = personUUID;
    }

    //endregion

    //region Getters and Setters

    public long getEncounterId() {

        return encounterId;
    }

    public void setEncounterId(long encounterId) {

        this.encounterId = encounterId;
    }

    public int getSurveyId() {

        return surveyId;
    }

    public void setSurveyId(int surveyId) {

        this.surveyId = surveyId;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public boolean isSynchronized() {

        return isSynchronized;
    }

    public void setSynchronized(boolean aSynchronized) {

        isSynchronized = aSynchronized;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @NonNull
    public String getPersonUUID() {

        return personUUID;
    }

    public void setPersonUUID(@NonNull String personUUID) {

        this.personUUID = personUUID;
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

        SurveyEncounter that = (SurveyEncounter) o;

        return surveyId == that.surveyId &&
                type == that.type &&
                isSynchronized == that.isSynchronized &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(name, that.name) &&
                Objects.equals(personUUID, that.personUUID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(surveyId, timestamp, type, isSynchronized, name, personUUID);
    }

    //endregion
}
