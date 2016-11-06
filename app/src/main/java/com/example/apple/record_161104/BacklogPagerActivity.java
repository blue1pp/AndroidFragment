package com.example.apple.record_161104;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by apple on 16/11/4.
 */

public class BacklogPagerActivity extends AppCompatActivity {
    private static final String EXTRA_BACKLOG_ID = "com.example.android.record.backlog_id";

    private ViewPager mViewPager;
    private List<Backlog>mBacklogs;

    public static Intent newIntent(Context packageContext, UUID backlogId){
        Intent intent = new Intent(packageContext,BacklogPagerActivity.class);
        intent.putExtra(EXTRA_BACKLOG_ID,backlogId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlog_pager);
        UUID backlogId = (UUID)getIntent().getSerializableExtra(EXTRA_BACKLOG_ID);

        mViewPager = (ViewPager)findViewById(R.id.activity_backlog_pager_view_pager);

        mBacklogs = BacklogLab.get(this).getBacklogs();
        int index = 0;
        for (int i = 0; i < mBacklogs.size(); i++) {
            if (mBacklogs.get(i).getId().equals(backlogId)) {
                index = i;
                break;
            }
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Backlog backlog = mBacklogs.get(position);
                return BacklogFragment.newInstance(backlog.getId());
            }

            @Override
            public int getCount() {
                return mBacklogs.size();
            }
        });

        mViewPager.setCurrentItem(index);

//        for(int i = 0; i < mBacklogs.size();i++){
//            if (mBacklogs.get(i).getId().equals(backlogId)){
//                mViewPager.setCurrentItem(i);
//                break;
//            }
//        }
    }
}
