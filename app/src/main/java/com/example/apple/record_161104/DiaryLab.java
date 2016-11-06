package com.example.apple.record_161104;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by apple on 16/11/3.
 */

public class DiaryLab {
    private static DiaryLab sDiaryLab;
    private List<Diary> mDiarys;

    public static DiaryLab get(Context context){
        if (sDiaryLab == null){
            sDiaryLab = new DiaryLab(context);
        }
        return sDiaryLab;
    }
    private DiaryLab(Context context){
        mDiarys = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Diary diary = new Diary();
            diary.setTitle("日记 "+ i);
            mDiarys.add(diary);
        }
    }

    public void addDiary(Diary d){
        mDiarys.add(d);
    }

    public List<Diary>getDiarys(){
        return mDiarys;
    }
    public Diary getDiary(UUID id){
        for (Diary diary : mDiarys){
            if (diary.getId().equals(id)){
                return diary;
            }
        }
        return null;
    }
}
