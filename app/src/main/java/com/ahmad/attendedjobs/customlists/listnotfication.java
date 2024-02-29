package com.ahmad.attendedjobs.customlists;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class listnotfication implements Parcelable {
    String title;
    String intro;
    int img;

    public listnotfication(String title, String intro, int img) {
        this.title = title;
        this.intro = intro;
        this.img = img;
    }

    protected listnotfication(Parcel in) {
        title = in.readString();
        intro = in.readString();
        img = in.readInt();
    }

    public static final Creator<listnotfication> CREATOR = new Creator<listnotfication>() {
        @Override
        public listnotfication createFromParcel(Parcel in) {
            return new listnotfication(in);
        }

        @Override
        public listnotfication[] newArray(int size) {
            return new listnotfication[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(intro);
        parcel.writeInt(img);
    }
}
