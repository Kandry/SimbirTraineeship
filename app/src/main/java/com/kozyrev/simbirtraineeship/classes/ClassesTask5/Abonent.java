package com.kozyrev.simbirtraineeship.classes.ClassesTask5;

public class Abonent implements Comparable{
     private int id;
     private String lastName, firstName, patronymic;
     private String address, cardNum;
     private int debit, credit;
     private long inCityTimeSpeak, outCityTimeSpeak;

     public Abonent(int id, String lastName, String firstName, String patronymic, String address, String cardNum, int debit, int credit, long inCityTimeSpeak, long outCityTimeSpeak){
         this.id = id;
         this.lastName = lastName;
         this.firstName = firstName;
         this.patronymic = patronymic;
         this.address = address;
         this.cardNum = cardNum;
         this.debit = debit;
         this.credit = credit;
         this.inCityTimeSpeak = inCityTimeSpeak;
         this.outCityTimeSpeak = outCityTimeSpeak;
     }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getDebit() {
        return debit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public void setInCityTimeSpeak(long inCityTimeSpeak) {
        this.inCityTimeSpeak = inCityTimeSpeak;
    }

    public long getInCityTimeSpeak() {
        return inCityTimeSpeak;
    }

    public void setOutCityTimeSpeak(long outCityTimeSpeak) {
        this.outCityTimeSpeak = outCityTimeSpeak;
    }

    public long getOutCityTimeSpeak() {
        return outCityTimeSpeak;
    }

    @Override
    public String toString(){
        return "id: " + id +
                "\nlastname: " + lastName +
                "\nfirstName: " + firstName +
                "\npatronymic: " + patronymic +
                "\naddress: " + address +
                "\ncardNum: " + cardNum +
                "\ndebit: " + debit +
                "\ncredit: " + credit +
                "\ninCityTimeSpeak: " + inCityTimeSpeak +
                "\noutCityTimeSpeak: " + outCityTimeSpeak;
    }

    @Override
    public int compareTo(Object o) {
        Abonent abonent2 = (Abonent) o;

        int lastNameCompare = lastName.compareTo(abonent2.getLastName());
        int firstNameCompare = firstName.compareTo(abonent2.getFirstName());
        return (lastNameCompare != 0 ? lastNameCompare :
                                (firstNameCompare != 0 ? firstNameCompare : patronymic.compareTo(abonent2.getPatronymic())));
    }
}
