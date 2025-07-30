package com.in.report.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.report.ReportGenerationApplication;
import com.in.report.dto.ReportRequest;
import com.in.report.service.ReportService;
import com.jtd.logger.AuditLogger;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

	private final ReportGenerationApplication reportGenerationApplication;

	private final ReportService reportService;

@Autowired
	private  AuditLogger auditLogger;

	@Value("${example.message}")
	private String dbUsername;

	public ReportController(ReportService reportService, ReportGenerationApplication reportGenerationApplication, AuditLogger auditLogger) {
		this.reportService = reportService;
		this.reportGenerationApplication = reportGenerationApplication;
		this.auditLogger =auditLogger;
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

	@GetMapping(value = "/employee", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getEmployeeReport() throws Exception {

		auditLogger.log("report-service", "ReportGeneration.getEmployeeReport", MDC.get("X-Request-Id"), null, "Inside getEmployee()");
		System.out.println("inside getEmployeeReport()");
		byte[] pdfBytes = reportService.generateEmployeeReport();
		System.out.println("Controller.getEmployeeReport() byte generated");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.inline().filename("employee-report.pdf").build());
		System.out.println("Controller.getEmployeeReport() disposition");
		return ResponseEntity.ok().headers(headers).body(pdfBytes);
	}

	@GetMapping("/config")
	public String getConfig() throws SQLException {
		return 	dbUsername;
				
	}
}