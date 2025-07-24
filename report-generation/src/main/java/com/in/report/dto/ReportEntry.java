package com.in.report.dto;

public class ReportEntry {
    private Integer sNo;
    private String wid;
    private String frameName;
    private String details;
    private String listName;
    private Long entityId;
    
    // Constructor
    public ReportEntry(Integer sNo, String wid, String frameName, 
                      String details, String listName, Long entityId) {
        this.sNo = sNo;
        this.wid = wid;
        this.frameName = frameName;
        this.details = details;
        this.listName = listName;
        this.entityId = entityId;
    }
    
    // Getters
    public Integer getSNo() { return sNo; }
    public String getWid() { return wid; }
    public String getFrameName() { return frameName; }
    public String getDetails() { return details; }
    public String getListName() { return listName; }
    public Long getEntityId() { return entityId; }
}