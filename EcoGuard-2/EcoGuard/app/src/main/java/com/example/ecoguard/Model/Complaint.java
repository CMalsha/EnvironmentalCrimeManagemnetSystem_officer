package com.example.ecoguard.Model;

public class Complaint {
    private final String date, title, description;

    public Complaint(String date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public String getDate() {
        return date;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getFormattedComplaint() {
        return date + " - " + title;
    }
}
