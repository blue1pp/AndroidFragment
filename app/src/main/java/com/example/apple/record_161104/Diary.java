package com.example.apple.record_161104;

import java.util.Date;
import java.util.UUID;

/**
 * Created by apple on 16/11/2.
 */

public class Diary {
    private UUID mId;
    private String mTitle;
    private Date mDate;

    public Diary(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        mTitle = Title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
