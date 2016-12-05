package com.eby.bestapp.model;

public enum JobTitle {
		JDEV("Java Developer"),
		NDEV (".Net Developer"),
        PDEV ("Php Developer"),
        FEDEV("FrontEnd Developer"),
        TESTER("Software Tester"),
        SPECIALIST( "Technical Specialist"),
        ARCHITECT ("System Architect");
  
	public final String jobTitle;
  
	private JobTitle(String jobTitle){
		this.jobTitle = jobTitle;
	}
}
