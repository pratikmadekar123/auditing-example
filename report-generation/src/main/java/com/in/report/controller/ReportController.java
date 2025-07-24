package com.in.report.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.report.dto.ReportRequest;
import com.in.report.service.ReportService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    private final ReportService reportService;
    
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    @PostMapping("/amlock")
    public ResponseEntity<byte[]> generateAmlockReport(@RequestBody ReportRequest request) {
        try {
            byte[] excelBytes = reportService.generateAmlockReport(
                request.getFromDate(), 
                request.getToDate()
            );
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "amlock_report.xlsx");
            
            return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
                
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                .body(("Error generating report: " + e.getMessage()).getBytes());
        }
    }
}