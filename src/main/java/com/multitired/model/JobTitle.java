package com.multitired.model;

public enum JobTitle {
		JAVA_DEVELOPER("JAVA_DEVELOPER"),
		NET_DEVELOPER ("NET_DEVELOPER"),
        PHP_DEVELOPER ("PHP_DEVELOPER"),
        FRONTEND_DEVELOPER("FRONTEND_DEVELOPER"),
        TESTER("TESTER"),
        TEHNICAL_SPECIALIST( "TEHNICAL_SPECIALIST"),
        ARCHITECT ("ARCHITECT");
  
	public final String jobTitle;
	  public static JobTitle fromString(String jobTitle){
		  return  JobTitle.valueOf(jobTitle);
	  }
	private JobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}
	
	public String toString() {
	       return this.jobTitle;
	    }
}
