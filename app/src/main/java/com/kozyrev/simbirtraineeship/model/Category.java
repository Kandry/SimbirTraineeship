package com.kozyrev.simbirtraineeship.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    private int id;
    private String name;
    private boolean isActive;

    public Category(String name){
        this.name = name;
    }

    public Category (Parcel data){
        this.id = data.readInt();
        this.name = data.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.isActive = data.readBoolean();
        } else {
            this.isActive = true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(isActive);
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel parcel) {
            return new Category(parcel);
        }

        @Override
        public Object[] newArray(int size) {
            return new Category[size];
        }
    };
}
