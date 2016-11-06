package com.example.apple.record_161104;

import java.util.Date;
import java.util.UUID;

/**
 * Created by apple on 16/11/2.
 */

public class Backlog {
    private UUID mB_Id;
    private String mB_Title;
    private Date mB_Date;
    private boolean mSolved;

    public Backlog(){
        mB_Id = UUID.randomUUID();
        mB_Date = new Date();
    }
    public UUID getId() {
        return mB_Id;
    }
    public String getTitle() {
        return mB_Title;
    }
    public void setB_Title(String title) {
        mB_Title = title;
    }

    public Date getDate() {
        return mB_Date;
    }

    public void setDate(Date date) {
        mB_Date = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
