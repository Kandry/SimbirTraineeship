package com.kozyrev.simbirtraineeship.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Event implements Parcelable {

    private int id;
    private String name;
    private String description;
    private List<String> photos;
    private int category;
    private long startDate;
    private long endDate = -1;
    private long createAt;

    private String organisation;
    private String address;
    private String phone;
    private String email;
    private String site;
    //private List<Integer> participantsID;

    public Event (){}

    public Event (String name){
        this.name = name;
    }

    public Event (@NotNull Parcel data) {
        this.id = data.readInt();
        this.name = data.readString();
        this.description = data.readString();
        this.photos = data.readArrayList( String.class.getClassLoader());
        this.category = data.readInt();
        this.startDate = data.readLong();
        this.endDate = data.readLong();
        this.createAt = data.readLong();

        this.organisation = data.readString();
        this.address = data.readString();
        this.phone = data.readString();
        this.email = data.readString();
        this.site = data.readString();
        //this.participantsID = data.readArrayList(Integer.class.getClassLoader());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
/*
    public List<Integer> getParticipantsID() {
        return participantsID;
    }

    public void setParticipantsID(List<Integer> participantsID) {
        this.participantsID = participantsID;
    }*/

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeList(photos);
        parcel.writeInt(category);
        parcel.writeLong(startDate);
        parcel.writeLong(endDate);
        parcel.writeLong(createAt);

        parcel.writeString(organisation);
        parcel.writeString(address);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeString(site);
        //parcel.writeList(participantsID);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Event createFromParcel(Parcel parcel) {
            return new Event(parcel);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
