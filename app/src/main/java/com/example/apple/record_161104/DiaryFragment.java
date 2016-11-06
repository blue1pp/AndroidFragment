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
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by apple on 16/11/2.
 */

public class DiaryFragment extends Fragment {

    private static final String ARG_DIARY_ID = "diary_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Diary mDiary;
    private EditText mTitleField;
    private Button mDateButton;

    public static DiaryFragment newInstance(UUID diaryId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DIARY_ID,diaryId);

        DiaryFragment fragment = new DiaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID diaryId = (UUID)getArguments().getSerializable(ARG_DIARY_ID);
        mDiary = DiaryLab.get(getActivity()).getDiary(diaryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_diary,container,false);

        mTitleField = (EditText)v.findViewById(R.id.diary_title);
        mTitleField.setText(mDiary.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int state, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int state, int count, int after) {
                mDiary.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = (Button)v.findViewById(R.id.diary_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mDiary.getDate());
                dialog.setTargetFragment(DiaryFragment.this,REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
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
            mDiary.setDate(date1);
            updateDate();
        }
    }

    private void updateDate() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
        mDateButton.setText(dt.format(mDiary.getDate()));
    }
}
