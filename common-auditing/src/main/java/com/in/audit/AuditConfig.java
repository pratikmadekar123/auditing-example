package com.in.audit;


public class AuditConfig {
    private String logDirectory;
    private String logPattern;
    private boolean consoleOutput;
	public String getLogDirectory() {
		return logDirectory;
	}
	public void setLogDirectory(String logDirectory) {
		this.logDirectory = logDirectory;
	}
	public String getLogPattern() {
		return logPattern;
	}
	public void setLogPattern(String logPattern) {
		this.logPattern = logPattern;
	}
	public boolean isConsoleOutput() {
		return consoleOutput;
	}
	public void setConsoleOutput(boolean consoleOutput) {
		this.consoleOutput = consoleOutput;
	}
    
    
    
    // Getters, setters, and builder pattern
}