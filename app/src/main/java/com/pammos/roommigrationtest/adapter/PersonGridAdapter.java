package com.pammos.roommigrationtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pammos.roommigrationtest.R;
import com.pammos.roommigrationtest.model.Person;

import java.util.Date;
import java.util.List;

public class PersonGridAdapter
        extends RecyclerView.Adapter<PersonGridAdapter.PersonCellViewHolder> {

    //region Fields

    private final LayoutInflater inflater;
    private Context context;
    private final String lblFemale;
    private final String lblMale;
    private List<Person> peopleList;

    //endregion

    //region Constructor

    public PersonGridAdapter(@NonNull Context context) {

        this.context = context;

        inflater = LayoutInflater.from(context);
        lblFemale = context.getString(R.string.lbl_female);
        lblMale = context.getString(R.string.lbl_male);
    }

    //endregion

    //region Overridden lifecycle methods

    @NonNull
    @Override
    public PersonCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_view_person_row_item, parent, false);

        return new PersonCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonCellViewHolder holder, int position) {

        if (peopleList != null) {
            Person person = peopleList.get(position);
            Date personBirthDate = person.getBirthDate();

            holder.tvUUID.setText(context.getString(R.string.lbl_user_uuid, person.getUUID()));
            holder.tvDocumentId.setText(context.getString(R.string.lbl_user_document_id,
                    person.getDocumentId()));

            holder.tvFirstName.setText(context.getString(R.string.lbl_first_name,
                    person.getFirstName()));

            holder.tvFirstLastName.setText(context.getString(R.string.lbl_first_last_name,
                    person.getFirstLastName()));

            holder.tvBirthDate.setText(context.getString(R.string.lbl_birth_date,
                    String.valueOf(personBirthDate.getYear()),
                    String.valueOf(personBirthDate.getMonth() + 1),
                    String.valueOf(personBirthDate.getDay())));

            holder.tvGender.setText(person.getGender() == 1 ?
                    context.getString(R.string.lbl_gender, lblMale) :
                    context.getString(R.string.lbl_gender, lblFemale));
        }
    }

    @Override
    public int getItemCount() {

        if (peopleList != null) {

            return peopleList.size();

        } else {

            return 0;
        }
    }

    //endregion

    //region Public procedures

    public void setPeopleList(List<Person> people) {

        peopleList = people;

        notifyDataSetChanged();
    }

    //region ViewHolder class

    public class PersonCellViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvUUID;
        private final TextView tvDocumentId;
        private final TextView tvFirstName;
        private final TextView tvFirstLastName;
        private final TextView tvBirthDate;
        private final TextView tvGender;

        public PersonCellViewHolder(@NonNull View itemView) {

            super(itemView);

            tvUUID = itemView.findViewById(R.id.tv_card_view_person_cell_uuid);
            tvDocumentId = itemView.findViewById(R.id.tv_card_view_person_cell_document_id);
            tvFirstName = itemView.findViewById(R.id.tv_card_view_person_cell_first_name);
            tvFirstLastName = itemView.findViewById(R.id.tv_card_view_person_cell_first_last_name);
            tvBirthDate = itemView.findViewById(R.id.tv_card_view_person_cell_birth_date);
            tvGender = itemView.findViewById(R.id.tv_card_view_person_cell_gender);
        }
    }

    //endregion
}
