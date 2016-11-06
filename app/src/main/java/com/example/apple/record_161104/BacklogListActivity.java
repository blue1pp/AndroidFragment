package com.example.apple.record_161104;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by apple on 16/11/3.
 */

public class BacklogListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new BacklogListFragment();
    }

    @Override
    protected int getLayoutResId(){
        return R.layout.activity_twopane;
    }

//    @Override
//    public FragmentManager getSupportFragmentManager() {
//        super.getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container,Backlog).commit();
//    }
}

