package com.kozyrev.simbirtraineeship.model;

import java.util.List;

public class Event {

    private int id;
    private String name;
    private String description;
    private List<String> imagesUri;
    private List<Integer> categoriesID;
    private long startDate;
    private long endDate = -1;

    private String organizer;
    private String address;
    private List<String> phoneNumbers;
    private String email;
    private String site;
    private List<Integer> participantsID;


    public Event (String name){
        this.name = name;
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

    public List<String> getImagesUri() {
        return imagesUri;
    }

    public void setImagesUri(List<String> imagesUri) {
        this.imagesUri = imagesUri;
    }

    public List<Integer> getCategoriesID() {
        return categoriesID;
    }

    public void setCategoriesID(List<Integer> categoriesID) {
        this.categoriesID = categoriesID;
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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
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

    public List<Integer> getParticipantsID() {
        return participantsID;
    }

    public void setParticipantsID(List<Integer> participantsID) {
        this.participantsID = participantsID;
    }
}
