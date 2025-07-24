package com.in.report.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportRequest {
    private String fromDate; // in dd-MM-yyyy format
    private String toDate;   // in dd-MM-yyyy format
    
    // Converts string to LocalDate
    public LocalDate getFromLocalDate() {
        return LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    public LocalDate getToLocalDate() {
        return LocalDate.parse(toDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    // Standard getters and setters
    public String getFromDate() {
        return fromDate;
    }
    
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    
    public String getToDate() {
        return toDate;
    }
    
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}