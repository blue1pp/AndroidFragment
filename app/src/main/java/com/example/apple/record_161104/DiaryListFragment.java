package com.example.apple.record_161104;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by apple on 16/11/4.
 */

public class DiaryListFragment extends Fragment {

    private RecyclerView mDiaryRecyclerView;
    private DiaryAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_diary_list,container,false);
        mDiaryRecyclerView = (RecyclerView)view.findViewById(R.id.diary_recycler_view);
        mDiaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_record_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_new_backlog:
                Diary diary = new Diary();
                DiaryLab.get(getActivity()).addDiary(diary);
                Intent intent = DiaryPagerActivity.newIntent(getActivity(),diary.getId());
                startActivity(intent);
                return true;
//            case R.id.menu_item_show_subtitle:
//                updateSubtitle();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private void updateSubtitle(){
//        DiaryLab diaryLab = DiaryLab.get(getActivity());
//        int diaryCount = diaryLab.getDiarys().size();
//        String subtitle = getString(R.string.subtitle_format,diaryCount);
//
//        AppCompatActivity activity = (AppCompatActivity)getActivity();
//        activity.getSupportActionBar().setSubtitle(subtitle);
//    }

    private void updateUI(){
        DiaryLab diaryLab = DiaryLab.get(getActivity());
        List<Diary>diaries = diaryLab.getDiarys();
        if (mAdapter == null){
            mAdapter = new DiaryAdapter(diaries);
            mDiaryRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }

    }

    private class DiaryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Diary mDiary;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public DiaryHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView.findViewById(R.id.list_item_diary_title_text_view);
            mDateTextView = (TextView)itemView.findViewById(R.id.list_item_diary_date_text_view);
        }

        public void bindDiary(Diary diary){
            mDiary = diary;
            mTitleTextView.setText(mDiary.getTitle());
            SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd");
            mDateTextView.setText(dt.format(mDiary.getDate()));
        }

        @Override
        public void onClick(View v){
            Intent intent = DiaryPagerActivity.newIntent(getActivity(),mDiary.getId());
            startActivity(intent);
        }
    }

    private class DiaryAdapter extends RecyclerView.Adapter<DiaryHolder>{
        private List<Diary>mDiaries;
        public DiaryAdapter(List<Diary>diaries){
            mDiaries = diaries;
        }

        @Override
        public DiaryHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_diary,parent,false);
            return new DiaryHolder(view);
        }
        @Override
        public void onBindViewHolder(DiaryHolder holder,int position){
            Diary diary = mDiaries.get(position);
            holder.bindDiary(diary);
        }
        @Override
        public int getItemCount(){
            return mDiaries.size();
        }
    }
}
