package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "survey_questions",
        foreignKeys = {@ForeignKey(entity = Survey.class,
                parentColumns = "id",
                childColumns = "survey_id",
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index("id"), @Index("survey_id")})
public class SurveyQuestion {

    //region Fields

    /**
     *
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("QuestionId_id")
    @Expose
    private int questionId;

    /**
     *
     */
    @ColumnInfo(name = "question_name")
    @SerializedName("QuestionName")
    @Expose
    private String questionName;

    /**
     *
     */
    @SerializedName("name")
    @Expose
    private String name;

    /**
     *
     */
    @SerializedName("SurveyId_id")
    @Expose
    @ColumnInfo(name = "survey_id")
    private int surveyId;

    /**
     *
     */
    @SerializedName("ConceptID_id")
    @Expose
    @ColumnInfo(name = "concept_id")
    private int conceptId;

    /**
     *
     */
    @SerializedName("ConceptClassID_id")
    @Expose
    @ColumnInfo(name = "concept_class_id")
    private int conceptClassId;

    /**
     *
     */
    @SerializedName("ConceptDataTypeID_id")
    @Expose
    @ColumnInfo(name = "concept_data_id")
    private int conceptDataTypeId;

    /**
     *
     */
    @SerializedName("IsMandtory")
    @Expose
    @ColumnInfo(name = "is_mandatory")
    private int isMandatory;

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
     * Constructs a <code>SurveyQuestion</code> object with all the numeric fields set to
     * <code>0</code>, all the <code>Object</code> fields set to <code>null</code>, and the
     * {@link #isActive} field set to <code>false</code>.
     */
    public SurveyQuestion() {

        questionId = 0;
        questionName = null;
        name = null;
        surveyId = 0;
        conceptId = 0;
        conceptClassId = 0;
        conceptDataTypeId = 0;
        isMandatory = 0;
        isActive = false;
        latestRefreshDate = null;
    }

    /**
     * Constructs a <code>SurveyQuestion</code> object with the given parameters.
     *
     * @param questionId
     * @param questionName
     * @param name
     * @param surveyId
     * @param conceptId
     * @param conceptClassId
     * @param conceptDataTypeId
     * @param isMandatory
     * @param isActive
     * @param latestRefreshDate
     */
    @Ignore
    public SurveyQuestion(int questionId, String questionName, String name, int surveyId,
                          int conceptId, int conceptClassId, int conceptDataTypeId,
                          int isMandatory, boolean isActive, Date latestRefreshDate) {

        this.questionId = questionId;
        this.questionName = questionName;
        this.name = name;
        this.surveyId = surveyId;
        this.conceptId = conceptId;
        this.conceptClassId = conceptClassId;
        this.conceptDataTypeId = conceptDataTypeId;
        this.isMandatory = isMandatory;
        this.isActive = isActive;
        this.latestRefreshDate = latestRefreshDate;
    }

    //endregion

    //region Getters and Setters

    public int getQuestionId() {

        return questionId;
    }

    public void setQuestionId(int questionId) {

        this.questionId = questionId;
    }

    public String getQuestionName() {

        return questionName;
    }

    public void setQuestionName(String questionName) {

        this.questionName = questionName;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getSurveyId() {

        return surveyId;
    }

    public void setSurveyId(int surveyId) {

        this.surveyId = surveyId;
    }

    public int getConceptId() {

        return conceptId;
    }

    public void setConceptId(int conceptId) {

        this.conceptId = conceptId;
    }

    public int getConceptClassId() {

        return conceptClassId;
    }

    public void setConceptClassId(int conceptClassId) {

        this.conceptClassId = conceptClassId;
    }

    public int getConceptDataTypeId() {

        return conceptDataTypeId;
    }

    public void setConceptDataTypeId(int conceptDataTypeId) {

        this.conceptDataTypeId = conceptDataTypeId;
    }

    public int getIsMandatory() {

        return isMandatory;
    }

    public void setIsMandatory(int isMandatory) {

        this.isMandatory = isMandatory;
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

        SurveyQuestion that = (SurveyQuestion) o;

        return questionId == that.questionId &&
                surveyId == that.surveyId &&
                conceptId == that.conceptId &&
                conceptClassId == that.conceptClassId &&
                conceptDataTypeId == that.conceptDataTypeId &&
                isMandatory == that.isMandatory &&
                isActive == that.isActive &&
                Objects.equals(questionName, that.questionName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(latestRefreshDate, that.latestRefreshDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionId, questionName, name, surveyId, conceptId, conceptClassId,
                conceptDataTypeId, isMandatory, isActive, latestRefreshDate);
    }

    //endregion
}
