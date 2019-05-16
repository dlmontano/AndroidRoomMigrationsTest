package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "bp_measurements",
        primaryKeys = {"person_UUID", "timestamp"},
        foreignKeys = {@ForeignKey(entity = Person.class,
                parentColumns = "UUID",
                childColumns = "person_UUID",
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index("person_UUID"),
                @Index("person_document_id")})
public class BloodPressureMeasurement {

    //region Fields

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
    @ColumnInfo(name = "person_UUID")
    private String personUUID;

    /**
     *
     */
    @NonNull
    private Date timestamp;

    /**
     *
     */
    @ColumnInfo(name = "systolic_pressure")
    private int systolicPressure;

    /**
     *
     */
    @ColumnInfo(name = "diastolic_pressure")
    private int diastolicPressure;

    //endregion

    //region Constructors

    /**
     * Constructs a <code>BloodPressureMeasurement</code> object with all the numeric fields set to
     * <code>0</code>, and all the <code>Object</code> fields set to <code>null</code>.
     */
    public BloodPressureMeasurement() {

        personDocumentId = null;
        personUUID = null;
        timestamp = null;
        systolicPressure = 0;
        diastolicPressure = 0;
    }

    /**
     * Constructs a <code>BloodPressureMeasurement</code> object with the given parameters.
     *
     * @param personDocumentId
     * @param personUUID
     * @param timestamp
     * @param systolicPressure
     * @param diastolicPressure
     */
    @Ignore
    public BloodPressureMeasurement(@NonNull String personDocumentId, @NonNull String personUUID,
                                    @NonNull Date timestamp, int systolicPressure,
                                    int diastolicPressure) {

        this.personDocumentId = personDocumentId;
        this.personUUID = personUUID;
        this.timestamp = timestamp;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
    }

    //endregion

    //region Getters and Setters

    public String getPersonDocumentId() {

        return personDocumentId;
    }

    public void setPersonDocumentId(String personDocumentId) {

        this.personDocumentId = personDocumentId;
    }

    public String getPersonUUID() {

        return personUUID;
    }

    public void setPersonUUID(String personUUID) {

        this.personUUID = personUUID;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;
    }

    public int getSystolicPressure() {

        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {

        this.systolicPressure = systolicPressure;
    }

    public int getDiastolicPressure() {

        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {

        this.diastolicPressure = diastolicPressure;
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

        BloodPressureMeasurement that = (BloodPressureMeasurement) o;

        return systolicPressure == that.systolicPressure &&
                diastolicPressure == that.diastolicPressure &&
                Objects.equals(personDocumentId, that.personDocumentId) &&
                Objects.equals(personUUID, that.personUUID) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(personDocumentId, personUUID, timestamp, systolicPressure,
                diastolicPressure);
    }

    //endregion
}
