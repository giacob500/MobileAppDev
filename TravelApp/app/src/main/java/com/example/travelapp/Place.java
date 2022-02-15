package com.example.travelapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    private int nameId;
    private int iconId;
    private int infoId;

    public int getNameId() { return this.nameId; }
    public int getIconId() { return this.iconId; }
    public int getInfo() { return infoId; }

    public void setNameId(int n) { this.nameId = n; }
    public void setIconId(int i) { this.iconId = i; }
    public void setInfoId(int i) { this.infoId = i; }

    public Place(int name, int icon, int info) {
        setNameId(name);
        setIconId(icon);
        setInfoId(info);
    }

    public Place(Place p) {
        setNameId(p.nameId);
        setIconId(p.iconId);
        setInfoId(p.infoId);
    }

    public Place(Parcel in) {
        this.nameId = in.readInt();
        this.iconId = in.readInt();
        this.infoId = in.readInt();
    }

    public static final Parcelable.Creator<Place> CREATOR =
            new Parcelable.Creator<Place>(){
                public Place createFromParcel(Parcel in) {
                    return new Place(in);
                }

                public Place[] newArray(int size) {
                    return new Place[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.nameId);
        dest.writeInt(this.iconId);
        dest.writeInt(this.infoId);
    }
}
