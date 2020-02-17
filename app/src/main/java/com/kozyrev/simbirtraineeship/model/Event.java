package com.kozyrev.simbirtraineeship.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

@Entity(tableName = "events", foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category"))
public class Event implements Parcelable {

    @SerializedName("id")
    @PrimaryKey
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("photos")
    @TypeConverters(StringListToGsonConverter.class)
    private List<String> photos;

    @SerializedName("category")
    private int category;

    @SerializedName("startDate")
    private long startDate;

    @SerializedName("endDate")
    private long endDate = -1;

    @SerializedName("createAt")
    private long createAt;

    @SerializedName("organization")
    private String organisation;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("site")
    private String site;

    @Ignore
    public Event (){}

    @Ignore
    public Event (String name){
        this.name = name;
    }

    @Ignore
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
    }

    public Event (int id, String name, String description, List<String> photos, int category, long startDate, long endDate, long createAt, String organisation, String address, String phone, String email, String site){
        this.id = id;
        this.name = name;
        this.description = description;
        this.photos = photos;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createAt = createAt;
        this.organisation = organisation;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.site = site;
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

    public static class StringListToGsonConverter{
        @TypeConverter
        public static List<String> restoreList(String listOfString){
            return new Gson().fromJson(listOfString, new TypeToken<List<String>>() {}.getType());
        }

        @TypeConverter
        public static String saveListOfString(List<String> listOfString){
            return new Gson().toJson(listOfString);
        }
/*
        @TypeConverter
        public static Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public static Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }*/
    }
}
