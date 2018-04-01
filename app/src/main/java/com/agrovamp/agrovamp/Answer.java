package com.agrovamp.agrovamp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nishat Sayyed on 01-04-2018.
 */

public class Answer implements Parcelable {
    private String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    protected Answer(Parcel in) {
        answer = in.readString();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(answer);
    }
}
