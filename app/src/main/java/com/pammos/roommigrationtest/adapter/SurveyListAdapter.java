package com.pammos.roommigrationtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pammos.roommigrationtest.R;
import com.pammos.roommigrationtest.model.Survey;

import java.util.List;

public class SurveyListAdapter extends RecyclerView.Adapter<SurveyListAdapter.SurveyRowViewHolder> {

    //region Fields

    private final LayoutInflater inflater;
    private Context context;
    private final String lblYes;
    private final String lblNo;
    private List<Survey> surveysList;

    //endregion

    //region Constructor

    public SurveyListAdapter(@NonNull Context context) {

        this.context = context;

        inflater = LayoutInflater.from(context);
        lblYes = context.getString(R.string.lbl_yes);
        lblNo = context.getString(R.string.lbl_no);
    }

    //endregion

    //region Overridden lifecycle methods

    @NonNull
    @Override
    public SurveyRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.card_view_survey_row_item, parent,
                false);

        return new SurveyRowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyRowViewHolder holder, int position) {

        if (surveysList != null) {
            Survey survey = surveysList.get(position);

            holder.tvSurveyId.setText(context.getString(R.string.lbl_item_id,
                    String.valueOf(survey.getSurveyId())));

            holder.tvSurveyType.setText(context.getString(R.string.lbl_type,
                    survey.getSurveyType()));

            holder.tvSurveyIsActive.setText(survey.isActive() ?
                    context.getString(R.string.lbl_is_active, lblYes) :
                    context.getString(R.string.lbl_is_active, lblNo));

            holder.tvSurveyPublished.setText(survey.isPublished() ?
                    context.getString(R.string.lbl_published, lblYes) :
                    context.getString(R.string.lbl_published, lblNo));

            holder.tvSurveyName.setText(context.getString(R.string.lbl_item_name,
                    survey.getSurveyName()));

            holder.tvSurveyDesc.setText(context.getString(R.string.lbl_item_desc,
                    survey.getSurveyDescription()));
        }
    }

    @Override
    public int getItemCount() {

        if (surveysList != null) {

            return surveysList.size();

        } else {

            return 0;
        }
    }

    //endregion

    //region Public procedures

    public void setSurveysList(List<Survey> surveys) {

        surveysList = surveys;

        notifyDataSetChanged();
    }

    //endregion

    //region ViewHolder class

    public class SurveyRowViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvSurveyId;
        private final TextView tvSurveyType;
        private final TextView tvSurveyIsActive;
        private final TextView tvSurveyPublished;
        private final TextView tvSurveyName;
        private final TextView tvSurveyDesc;

        public SurveyRowViewHolder(@NonNull View itemView) {

            super(itemView);

            tvSurveyId = itemView.findViewById(R.id.tv_card_view_survey_id);
            tvSurveyType = itemView.findViewById(R.id.tv_card_view_survey_type);
            tvSurveyIsActive = itemView.findViewById(R.id.tv_card_view_survey_is_active);
            tvSurveyPublished = itemView.findViewById(R.id.tv_card_view_survey_published);
            tvSurveyName = itemView.findViewById(R.id.tv_card_view_survey_name);
            tvSurveyDesc = itemView.findViewById(R.id.tv_card_view_survey_desc);
        }
    }

    //endregion
}
