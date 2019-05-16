package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "hr_measurements",
        primaryKeys = {"person_UUID", "timestamp"},
        foreignKeys = {@ForeignKey(entity = Person.class,
                parentColumns = "UUID",
                childColumns = "person_UUID",
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index("person_UUID"),
                @Index("person_document_id")})
public class HeartbeatRateMeasurement {

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
    @ColumnInfo(name = "heartbeat_rate")
    private int heartbeatRate;

    //endregion

    //region Constructors

    /**
     * Constructs a <code>HeartbeatRateMeasurement</code> object with all the numeric fields set to
     * <code>0</code>, and all the <code>Object</code> fields set to <code>null</code>.
     */
    public HeartbeatRateMeasurement() {

        personDocumentId = null;
        personUUID = null;
        timestamp = null;
        heartbeatRate = 0;
    }

    /**
     * Constructs a <code>HeartbeatRateMeasurement</code> object with the given parameters.
     *
     * @param personDocumentId
     * @param personUUID
     * @param timestamp
     * @param heartbeatRate
     */
    @Ignore
    public HeartbeatRateMeasurement(@NonNull String personDocumentId, @NonNull String personUUID,
                                    @NonNull Date timestamp, int heartbeatRate) {

        this.personDocumentId = personDocumentId;
        this.personUUID = personUUID;
        this.timestamp = timestamp;
        this.heartbeatRate = heartbeatRate;
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

    public int getHeartbeatRate() {

        return heartbeatRate;
    }

    public void setHeartbeatRate(int heartbeatRate) {

        this.heartbeatRate = heartbeatRate;
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


        HeartbeatRateMeasurement that = (HeartbeatRateMeasurement) o;

        return heartbeatRate == that.heartbeatRate &&
                Objects.equals(personDocumentId, that.personDocumentId) &&
                Objects.equals(personUUID, that.personUUID) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(personDocumentId, personUUID, timestamp, heartbeatRate);
    }

    //endregion
}
