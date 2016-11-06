package com.example.apple.record_161104;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by apple on 16/11/2.
 */

public class BacklogLab {
    private static BacklogLab sBacklogLab;
    private List<Backlog>mBacklogs;

    public static BacklogLab get(Context context){
        if (sBacklogLab == null){
            sBacklogLab = new BacklogLab(context);
        }
        return sBacklogLab;
    }
    private BacklogLab(Context context){
        mBacklogs = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Backlog backlog = new Backlog();
            backlog.setB_Title("代办事项" + i);
            backlog.setSolved(i % 2 == 0);
            mBacklogs.add(backlog);
        }
    }
    public List<Backlog>getBacklogs(){
        return mBacklogs;
    }
    public Backlog getBacklog(UUID id){
        for (Backlog backlog : mBacklogs){
            if (backlog.getId().equals(id)){
                return backlog;
            }
        }
        return null;
    }
}
