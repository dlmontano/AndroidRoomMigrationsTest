package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "survey_question_coded_choices",
        foreignKeys = @ForeignKey(entity = SurveyQuestion.class,
                childColumns = "question_id",
                parentColumns = "id",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index("question_id"), @Index("answer_concept_id"), @Index("survey_id")})
public class SurveyQuestionCodedChoice {

    //region Fields

    public static final int INVALID_ANSWER_CONCEPT_ID_VALUE = 0;

    /**
     *
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int surveyQuestionCodedChoiceId;

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
    @SerializedName("locale")
    @Expose
    private String locale;

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
    @SerializedName("answer_concept_id")
    @Expose
    @ColumnInfo(name = "answer_concept_id")
    private Integer answerConceptId;

    /**
     *
     */
    @NonNull
    @ColumnInfo(name = "question_id")
    @SerializedName("id")
    @Expose
    private int questionId;

    /**
     *
     */
    @ColumnInfo(name = "latest_refresh_date")
    private Date latestRefreshDate;

    //endregion

    //region Constructors

    /**
     * Constructs a <code>SurveyQuestionCodedChoice</code> object with all the numeric fields set to
     * <code>0</code> and all the <code>Object</code> fields set to <code>null</code>.
     */
    public SurveyQuestionCodedChoice() {

        surveyQuestionCodedChoiceId = 0;
        questionName = null;
        name = null;
        locale = null;
        surveyId = 0;
        conceptId = 0;
        answerConceptId = 0;
        questionId = 0;
        latestRefreshDate = null;
    }

    /**
     * Constructs a <code>SurveyQuestionCodedChoice</code> object with the given parameters.
     *
     * @param surveyQuestionCodedChoiceId
     * @param questionName
     * @param name
     * @param locale
     * @param surveyId
     * @param conceptId
     * @param answerConceptId
     * @param questionId
     * @param latestRefreshDate
     */
    @Ignore
    public SurveyQuestionCodedChoice(int surveyQuestionCodedChoiceId, String questionName,
                                     String name, String locale, int surveyId, int conceptId,
                                     @NonNull Integer answerConceptId, @NonNull Integer questionId,
                                     Date latestRefreshDate) {

        this.surveyQuestionCodedChoiceId = surveyQuestionCodedChoiceId;
        this.questionName = questionName;
        this.name = name;
        this.locale = locale;
        this.surveyId = surveyId;
        this.conceptId = conceptId;
        this.answerConceptId = answerConceptId;
        this.questionId = questionId;
        this.latestRefreshDate = latestRefreshDate;
    }

    //endregion

    //region Getters and Setters

    public int getSurveyQuestionCodedChoiceId() {

        return surveyQuestionCodedChoiceId;
    }

    public void setSurveyQuestionCodedChoiceId(int surveyQuestionCodedChoiceId) {

        this.surveyQuestionCodedChoiceId = surveyQuestionCodedChoiceId;
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

    public String getLocale() {

        return locale;
    }

    public void setLocale(String locale) {

        this.locale = locale;
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

    public Integer getAnswerConceptId() {

        return answerConceptId;
    }

    public void setAnswerConceptId(Integer answerConceptId) {

        this.answerConceptId = answerConceptId;
    }

    @NonNull
    public int getQuestionId() {

        return questionId;
    }

    public void setQuestionId(@NonNull int questionId) {

        this.questionId = questionId;
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

        SurveyQuestionCodedChoice that = (SurveyQuestionCodedChoice) o;

        return surveyQuestionCodedChoiceId == that.surveyQuestionCodedChoiceId &&
                surveyId == that.surveyId &&
                conceptId == that.conceptId &&
                answerConceptId == that.answerConceptId &&
                questionId == that.questionId &&
                Objects.equals(questionName, that.questionName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(locale, that.locale) &&
                Objects.equals(latestRefreshDate, that.latestRefreshDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(surveyQuestionCodedChoiceId, questionName, name, locale, surveyId,
                conceptId, answerConceptId, questionId, latestRefreshDate);
    }

    //endregion
}
