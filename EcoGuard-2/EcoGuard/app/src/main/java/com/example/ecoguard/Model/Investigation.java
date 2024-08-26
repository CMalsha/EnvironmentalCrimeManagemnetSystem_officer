package com.example.ecoguard.Model;

public class Investigation {
    private final String logoNo, status, date, description;

    public Investigation(String logoNo, String status, String date, String description) {
        this.logoNo = logoNo;
        this.status = status;
        this.date = date;
        this.description = description;
    }

    public String getComplaintNo() {
        return logoNo;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getDescription(){return description;}

    public String getFormattedInvestigation() {return logoNo + " - " + status + " - " + date;}

}
