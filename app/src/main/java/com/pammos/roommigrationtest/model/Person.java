package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "people", indices = {@Index("UUID"), @Index("document_id")})
public class Person {

    //region Fields

    /**
     * An indicator for an invalid gender ID for this object.
     */
    @Ignore
    public static final int INVALID_GENDER_ID = 0;

    /**
     * An indicator for an invalid measurement group ID for this object.
     */
    @Ignore
    public static final int INVALID_MEASUREMENT_GROUP_ID = 0;

    /**
     * The person's Universally Unique Identifier.
     */
    @SerializedName("UUID")
    @NonNull
    @PrimaryKey
    @Expose
    private String UUID;

    /**
     * The person's document identification number.
     */
    @SerializedName("cedula")
    @NonNull
    @ColumnInfo(name = "document_id")
    @Expose
    private String documentId;

    /**
     * The person's first name.
     */
    @SerializedName("First_FirstName")
    @NonNull
    @ColumnInfo(name = "first_name")
    @Expose
    private String firstName;

    /**
     * The person's second name.
     */
    @ColumnInfo(name = "second_name")
    @Expose
    private String secondName;

    /**
     * The person's first last name.
     */
    @SerializedName("First_LastName")
    @ColumnInfo(name = "first_last_name")
    @Expose
    private String firstLastName;

    /**
     * The person's {@link Date birth date}.
     */
    @SerializedName("fecha_nacimiento")
    @ColumnInfo(name = "birth_date")
    @Expose
    private Date birthDate;

    /**
     * The person's gender.
     */
    @SerializedName("genero")
    @Expose
    private int gender;

    /**
     * The person's indicator for a password change.
     */
    @SerializedName("cambio_contraseña")
    @ColumnInfo(name = "has_to_change_password")
    @Expose
    private boolean hasToChangePassword;

    /**
     * The person's
     */
    @SerializedName("grupo_medicion_id")
    @Expose
    @Ignore
    private int measurementGroupId;

    /**
     * The person's token.
     */
    @SerializedName("token")
    @Expose
    private String token;

    /**
     *
     */
    @ColumnInfo(name = "latest_refresh_date")
    private Date latestRefreshDate;

    //endregion

    //region Constructors

    /**
     * Constructs a <code>Person</code> object with all the fields set to <code>null</code>,
     * except for {@link #gender gender}, which is set to <code>INVALID_GENDER_ID</code>, and
     * {@link #hasToChangePassword hasToChangePassword}, which is set to <code>false</code>.
     */
    @Ignore
    public Person() {

        UUID = null;
        documentId = null;
        firstName = null;
        secondName = null;
        firstLastName = null;
        birthDate = null;
        gender = INVALID_GENDER_ID;
        hasToChangePassword = false;
        measurementGroupId = INVALID_MEASUREMENT_GROUP_ID;
        token = null;
        latestRefreshDate = null;
    }

    /**
     * Constructs a <code>Person</code> object with the given parameters.
     *
     * @param UUID
     * @param documentId
     * @param firstName
     * @param secondName
     * @param firstLastName
     * @param birthDate
     * @param gender
     * @param hasToChangePassword
     * @param token
     * @param latestRefreshDate
     */
    public Person(@NonNull String UUID, @NonNull String documentId, @NonNull String firstName,
                  String secondName, String firstLastName, Date birthDate, int gender,
                  boolean hasToChangePassword, String token, Date latestRefreshDate) {

        this.UUID = UUID;
        this.documentId = documentId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastName = firstLastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.hasToChangePassword = hasToChangePassword;
        this.token = token;
        this.latestRefreshDate = latestRefreshDate;
    }

    @Ignore
    public Person(@NonNull String UUID, @NonNull String documentId, @NonNull String firstName,
                  String secondName, String firstLastName, Date birthDate, int gender,
                  boolean hasToChangePassword, int measurementGroupId, String token,
                  Date latestRefreshDate) {

        this.UUID = UUID;
        this.documentId = documentId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastName = firstLastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.hasToChangePassword = hasToChangePassword;
        this.measurementGroupId = measurementGroupId;
        this.token = token;
        this.latestRefreshDate = latestRefreshDate;
    }

    //endregion

    //region Getters and Setters

    public String getUUID() {

        return UUID;
    }

    public void setUUID(String UUID) {

        this.UUID = UUID;
    }

    public String getDocumentId() {

        return documentId;
    }

    public void setDocumentId(String documentId) {

        this.documentId = documentId;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getSecondName() {

        return secondName;
    }

    public void setSecondName(String secondName) {

        this.secondName = secondName;
    }

    public String getFirstLastName() {

        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {

        this.firstLastName = firstLastName;
    }

    public Date getBirthDate() {

        return birthDate;
    }

    public void setBirthDate(Date birthDate) {

        this.birthDate = birthDate;
    }

    public int getGender() {

        return gender;
    }

    public void setGender(int gender) {

        this.gender = gender;
    }

    public boolean isHasToChangePassword() {

        return hasToChangePassword;
    }

    public void setHasToChangePassword(boolean hasToChangePassword) {

        this.hasToChangePassword = hasToChangePassword;
    }

    public int getMeasurementGroupId() {

        return measurementGroupId;
    }

    public void setMeasurementGroupId(int measurementGroupId) {

        this.measurementGroupId = measurementGroupId;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    public Date getLatestRefreshDate() {

        return latestRefreshDate;
    }

    public void setLatestRefreshDate(Date latestRefreshDate) {

        this.latestRefreshDate = latestRefreshDate;
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

        Person person = (Person) o;

        return gender == person.gender &&
                hasToChangePassword == person.hasToChangePassword &&
                Objects.equals(UUID, person.UUID) &&
                Objects.equals(documentId, person.documentId) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(secondName, person.secondName) &&
                Objects.equals(firstLastName, person.firstLastName) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(token, person.token) &&
                Objects.equals(latestRefreshDate, person.latestRefreshDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(UUID, documentId, firstName, secondName, firstLastName, birthDate,
                gender, hasToChangePassword, token, latestRefreshDate);
    }


    //endregion
}
