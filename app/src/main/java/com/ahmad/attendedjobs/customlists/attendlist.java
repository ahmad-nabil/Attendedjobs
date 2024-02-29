package com.ahmad.attendedjobs.customlists;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class attendlist implements Parcelable {
    String title,introduction,info;

    int img;
int uniqueIdentifier;
    public attendlist(String title, String introduction, String info, int img,int uniqueIdentifier) {
        this.title = title;
        this.introduction = introduction;
        this.info = info;
        this.img = img;
        this.uniqueIdentifier=uniqueIdentifier;
    }

    public int getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(int uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    protected attendlist(Parcel in) {
        title = in.readString();
        introduction = in.readString();
        info = in.readString();
        img = in.readInt();
        uniqueIdentifier=in.readInt();
    }

    public static final Creator<attendlist> CREATOR = new Creator<attendlist>() {
        @Override
        public attendlist createFromParcel(Parcel in) {
            return new attendlist(in);
        }

        @Override
        public attendlist[] newArray(int size) {
            return new attendlist[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        parcel.writeString(introduction);
        parcel.writeString(info);
        parcel.writeInt(img);
        parcel.writeInt(uniqueIdentifier);
    }

}
