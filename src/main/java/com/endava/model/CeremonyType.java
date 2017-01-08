package com.endava.model;

public enum CeremonyType {
	DAILY_STAND_UP("DAILY_STAND_UP"),
	DEMO("DEMO"),
	PLANING("PLANING"),
	RETROSPECTIVE("RETROSPECTIVE");
	
	public final String ceremonyType;
	  public static CeremonyType fromString(String ceremonyType){
		  return  CeremonyType.valueOf(ceremonyType);
	  }
	private CeremonyType(String ceremonyType){
		this.ceremonyType = ceremonyType;
	}
}
