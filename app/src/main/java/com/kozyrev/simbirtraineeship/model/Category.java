package com.kozyrev.simbirtraineeship.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "categories")
public class Category implements Parcelable {

    @SerializedName("id")
    @PrimaryKey
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("name_en")
    private String nameEn;

    @SerializedName("image")
    private String image;

    @Ignore
    public Category(String name){
        this.name = name;
    }

    @Ignore
    public Category (Parcel data){
        this.id = data.readInt();
        this.name = data.readString();
        this.nameEn = data.readString();
        this.image = data.readString();
    }

    public Category (int id, String name, String nameEn, String image){
        this.id = id;
        this.name = name;
        this.nameEn = nameEn;
        this.image = image;
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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(nameEn);
        parcel.writeString(image);
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
