package cz.osu.reservation.models;

import java.sql.Time;

public class Visit {

    private int id;
    private String name;
    private String lastName;
    private int insurance_code;
    private String phone;
    private String email;
    private String description;
    private String descriptionReason;
    private int year;
    private int month;
    private int day;
    private Time hour;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getInsurance_code() {
        return insurance_code;
    }

    public void setInsurance_code(int insurance_code) {
        this.insurance_code = insurance_code;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionReason() {
        return descriptionReason;
    }

    public void setDescriptionReason(String descriptionReason) {
        this.descriptionReason = descriptionReason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }
}
