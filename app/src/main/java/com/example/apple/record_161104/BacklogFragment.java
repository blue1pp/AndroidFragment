package com.example.apple.record_161104;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by apple on 16/11/2.
 */

public class BacklogFragment extends Fragment {

    private static final String ARG_BACKLOG_ID = "backlog_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Backlog mBacklog;
    private EditText mB_TitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static BacklogFragment newInstance(UUID backlogId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_BACKLOG_ID,backlogId);

        BacklogFragment fragment = new BacklogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID backlogId = (UUID)getArguments().getSerializable(ARG_BACKLOG_ID);
        mBacklog = BacklogLab.get(getActivity()).getBacklog(backlogId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_backlog,container,false);

        mB_TitleField = (EditText)v.findViewById(R.id.backlog_title);
        mB_TitleField.setText(mBacklog.getTitle());
        mB_TitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int state, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int state, int count, int after) {
                mBacklog.setB_Title(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = (Button)v.findViewById(R.id.backlog_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mBacklog.getDate());
                dialog.setTargetFragment(BacklogFragment.this,REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.list_item_backlog_solved_check_box);
        mSolvedCheckBox.setChecked(mBacklog.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBacklog.setSolved(isChecked);

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent date){
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_DATE){
            Date date1 = (Date)date.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mBacklog.setDate(date1);
            updateDate();
        }
    }

    private void updateDate() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
        mDateButton.setText(dt.format(mBacklog.getDate()));
    }
}
