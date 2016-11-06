package com.example.apple.record_161104;

import android.support.v4.app.Fragment;

/**
 * Created by apple on 16/11/4.
 */

public class DiaryListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new DiaryListFragment();
    }
    @Override
    protected int getLayoutResId(){
        return R.layout.activity_twopane;
    }
}
