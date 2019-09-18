package com.hades.example.android.lib.mock;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * A dummy item representing a piece of content.
 */
public class DummyItem implements Serializable, Parcelable {
    public final int id;
    public final String colo2;
    public final int col3;

    public DummyItem(int _id, String content, int details) {
        this.id = _id;
        this.colo2 = content;
        this.col3 = details;
    }

    protected DummyItem(Parcel in) {
        id = in.readInt();
        colo2 = in.readString();
        col3 = in.readInt();
    }

    public int getId() {
        return id;
    }

    public String getColo2() {
        return colo2;
    }

    public int getCol3() {
        return col3;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", colo2='" + colo2 + '\'' +
                ", col3='" + col3 + '\'' +
                '}';
    }


    public static final Creator<DummyItem> CREATOR = new Creator<DummyItem>() {
        @Override
        public DummyItem createFromParcel(Parcel in) {
            return new DummyItem(in);
        }

        @Override
        public DummyItem[] newArray(int size) {
            return new DummyItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(colo2);
        dest.writeInt(col3);
    }
}
