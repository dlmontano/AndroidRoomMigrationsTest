package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "surveys")
public class Survey {

    //region Fields

    /**
     * An indicator for an invalid survey surveyType ID for this object.
     */
    @Ignore
    public static final int INVALID_SURVEY_TYPE_ID = 0;

    /**
     *
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private int surveyId;

    /**
     *
     */
    @ColumnInfo(name = "start_date")
    @SerializedName("StartDate")
    @Expose
    private String startDate;

    /**
     *
     */
    @ColumnInfo(name = "description")
    @SerializedName("SurveyDescription")
    @Expose
    private String surveyDescription;

    /**
     *
     */
    @SerializedName("SurveyName")
    @ColumnInfo(name = "name")
    @Expose
    private String surveyName;

    /**
     *
     */
    @SerializedName("tipo_encuesta")
    @ColumnInfo(name = "type")
    @Expose
    private int surveyType;

    /**
     *
     */
    @SerializedName("IsPublish")
    @Expose
    private boolean published;

    /**
     *
     */
    @ColumnInfo(name = "is_active")
    private boolean isActive;

    /**
     *
     */
    @ColumnInfo(name = "latest_refresh_date")
    private Date latestRefreshDate;

    //endregion

    //region Constructors

    /**
     * Constructs a <code>Survey</code> object with all the fields set to <code>null</code>,
     * except for {@link #surveyId surveyId}, which is set to <code>0</code>,
     * {@link #surveyType surveyType}, which is set to <code>INVALID_SURVEY_TYPE_ID</code>,
     * and {@link #published published}, which is set to <code>false</code>.
     */
    public Survey() {

        surveyId = 0;
        startDate = null;
        surveyDescription = null;
        surveyName = null;
        surveyType = INVALID_SURVEY_TYPE_ID;
        published = false;
        isActive = false;
        latestRefreshDate = null;
    }

    /**
     * Constructs a <code>Survey</code> object with the given parameters.
     *
     * @param surveyId
     * @param startDate
     * @param surveyDescription
     * @param surveyName
     * @param surveyType
     * @param published
     * @param isActive
     * @param latestRefreshDate
     */
    @Ignore
    public Survey(int surveyId, String startDate, String surveyDescription, String surveyName,
                  int surveyType, boolean published, boolean isActive, Date latestRefreshDate) {

        this.surveyId = surveyId;
        this.startDate = startDate;
        this.surveyDescription = surveyDescription;
        this.surveyName = surveyName;
        this.surveyType = surveyType;
        this.published = published;
        this.isActive = isActive;
        this.latestRefreshDate = latestRefreshDate;
    }

    //endregion

    //region Getters and Setters

    public int getSurveyId() {

        return surveyId;
    }

    public void setSurveyId(int surveyId) {

        this.surveyId = surveyId;
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {

        this.startDate = startDate;
    }

    public String getSurveyDescription() {

        return surveyDescription;
    }

    public void setSurveyDescription(String surveyDescription) {

        this.surveyDescription = surveyDescription;
    }

    public String getSurveyName() {

        return surveyName;
    }

    public void setSurveyName(String surveyName) {

        this.surveyName = surveyName;
    }

    public int getSurveyType() {

        return surveyType;
    }

    public void setSurveyType(int surveyType) {

        this.surveyType = surveyType;
    }

    public boolean isPublished() {

        return published;
    }

    public void setPublished(boolean published) {

        this.published = published;
    }

    public boolean isActive() {

        return isActive;
    }

    public void setActive(boolean active) {

        isActive = active;
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

        Survey survey = (Survey) o;

        return surveyId == survey.surveyId &&
                surveyType == survey.surveyType &&
                published == survey.published &&
                isActive == survey.isActive &&
                Objects.equals(startDate, survey.startDate) &&
                Objects.equals(surveyDescription, survey.surveyDescription) &&
                Objects.equals(surveyName, survey.surveyName) &&
                Objects.equals(latestRefreshDate, survey.latestRefreshDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(surveyId, startDate, surveyDescription, surveyName, surveyType,
                published, isActive, latestRefreshDate);
    }

    //endregion
}
