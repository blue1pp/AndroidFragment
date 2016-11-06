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
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by apple on 16/11/4.
 */

public class BacklogListFragment extends Fragment {

    private RecyclerView mBacklogRecyclerView;
    private BacklogAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_backlog_list,container,false);
        mBacklogRecyclerView = (RecyclerView)view.findViewById(R.id.backlog_recycler_view);
        mBacklogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();//this is comment
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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.menu_item_new_backlog:
//                Backlog backlog = new Backlog();
//                backlog.get(getActivity()).addBacklog(backlog);
//                Intent intent = BacklogPagerActivity.newIntent(getActivity(),backlog.getId());
//                startActivity(intent);
//                return true;
//            case R.id.menu_item_show_subtitle:
//                updateSubtitle();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    private void updateSubtitle(){
//        DiaryLab diaryLab = DiaryLab.get(getActivity());
//        int diaryCount = diaryLab.getDiarys().size();
//        String subtitle = getString(R.string.subtitle_format,backlogCount);
//
//        AppCompatActivity activity = (AppCompatActivity)getActivity();
//        activity.getSupportActionBar().setSubtitle(subtitle);
//    }

    private void updateUI(){
        BacklogLab backlogLab = BacklogLab.get(getActivity());
        List<Backlog>backlogs = backlogLab.getBacklogs();
        if (mAdapter == null){
            mAdapter = new BacklogAdapter(backlogs);
            mBacklogRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }

    }

    private class BacklogHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Backlog mBacklog;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public BacklogHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView.findViewById(R.id.list_item_backlog_title_text_view);
            mDateTextView = (TextView)itemView.findViewById(R.id.list_item_backlog_date_text_view);
            mSolvedCheckBox = (CheckBox)itemView.findViewById(R.id.list_item_backlog_solved_check_box);
        }

        public void bindBacklog(Backlog backlog){
            mBacklog = backlog;
            mTitleTextView.setText(mBacklog.getTitle());
            SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd E");
            mDateTextView.setText(dt.format(mBacklog.getDate()));
            mSolvedCheckBox.setChecked(mBacklog.isSolved());
        }

        @Override
        public void onClick(View v){
            Intent intent = BacklogPagerActivity.newIntent(getActivity(),mBacklog.getId());
            startActivity(intent);
        }
    }

    private class BacklogAdapter extends RecyclerView.Adapter<BacklogHolder>{
        private List<Backlog>mBacklogs;
        public BacklogAdapter(List<Backlog>backlogs){
            mBacklogs = backlogs;
        }

        @Override
        public BacklogHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_backlog,parent,false);
            return new BacklogHolder(view);
        }
        @Override
        public void onBindViewHolder(BacklogHolder holder,int position){
            Backlog backlog = mBacklogs.get(position);
            holder.bindBacklog(backlog);
        }
        @Override
        public int getItemCount(){
            return mBacklogs.size();
        }
    }
}
