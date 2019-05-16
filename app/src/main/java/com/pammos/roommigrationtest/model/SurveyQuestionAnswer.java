package com.pammos.roommigrationtest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "survey_question_answers",
        foreignKeys = {@ForeignKey(entity = SurveyEncounter.class,
                childColumns = "encounter_id",
                parentColumns = "id",
                onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = SurveyQuestion.class,
                        childColumns = "question_id",
                        parentColumns = "id",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index("id"), @Index("encounter_id"), @Index("question_id")})
public class SurveyQuestionAnswer {

    //region Fields

    /**
     *
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long surveyQuestionAnswerId;

    /**
     *
     */
    @ColumnInfo(name = "encounter_id")
    private long encounterId;

    /**
     *
     */
    @ColumnInfo(name = "question_id")
    private int questionId;

    /**
     *
     */
    @ColumnInfo(name = "answer_value")
    private String answerValue;

    //endregion

    //region Constructors

    /**
     * Constructs a <code>SurveyQuestionAnswer</code> object with all the numeric fields set to
     * <code>0</code> and all the <code>Object</code> fields set to <code>null</code>.
     */
    public SurveyQuestionAnswer() {

        surveyQuestionAnswerId = 0;
        encounterId = 0;
        questionId = 0;
        answerValue = null;
    }

    /**
     * Constructs a <code>SurveyQuestionAnswer</code> object with the given parameters.
     *
     * @param surveyQuestionAnswerId
     * @param encounterId
     * @param questionId
     * @param answerValue
     */
    @Ignore
    public SurveyQuestionAnswer(long surveyQuestionAnswerId, long encounterId, int questionId,
                                String answerValue) {

        this.surveyQuestionAnswerId = surveyQuestionAnswerId;
        this.encounterId = encounterId;
        this.questionId = questionId;
        this.answerValue = answerValue;
    }

    //endregion

    //region Getters and Setters

    public long getSurveyQuestionAnswerId() {

        return surveyQuestionAnswerId;
    }

    public void setSurveyQuestionAnswerId(long surveyQuestionAnswerId) {

        this.surveyQuestionAnswerId = surveyQuestionAnswerId;
    }

    public long getEncounterId() {

        return encounterId;
    }

    public void setEncounterId(long encounterId) {

        this.encounterId = encounterId;
    }

    public int getQuestionId() {

        return questionId;
    }

    public void setQuestionId(int questionId) {

        this.questionId = questionId;
    }

    public String getAnswerValue() {

        return answerValue;
    }

    public void setAnswerValue(String answerValue) {

        this.answerValue = answerValue;
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

        SurveyQuestionAnswer that = (SurveyQuestionAnswer) o;

        return encounterId == that.encounterId &&
                questionId == that.questionId &&
                Objects.equals(answerValue, that.answerValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(encounterId, questionId, answerValue);
    }

    //endregion
}
