package com.kozyrev.simbirtraineeship.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.List;

public class User implements Parcelable {

    private int id;
    private String name;
    private int photoId = -1;
    private String photoUri;
    private Date dateBirth;
    private String profession;
    private boolean isPush = true;
    private List<User> friends;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public User(int id, int photoId, String name){
        this.id = id;
        this.photoId = photoId;
        this.name = name;
    }

    protected User(Parcel in) {
        id = in.readInt();
        photoId = in.readInt();
        photoUri = in.readString();
        name = in.readString();
        dateBirth = new Date(in.readLong());
        profession = in.readString();
        isPush = in.readByte() != 0;
        friends = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isPush() {
        return isPush;
    }

    public void setPush(boolean push) {
        isPush = push;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(photoId);
        parcel.writeString(photoUri);
        parcel.writeString(name);
        parcel.writeLong(dateBirth.getTime());
        parcel.writeString(profession);
        parcel.writeBoolean(isPush);
        parcel.writeList(friends);
    }
}
