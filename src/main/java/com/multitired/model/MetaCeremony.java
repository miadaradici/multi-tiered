package com.multitired.model;

import java.util.Date;

public class MetaCeremony {
	private Integer id;
	private CeremonyType type;
	private Date date;
	private String description;
	private Sprint sprint;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CeremonyType getCeremonyType() {
		return type;
	}
	public void setCeremonyType(CeremonyType ceremonyType) {
		this.type = ceremonyType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Sprint getSprint() {
		return sprint;
	}
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	
	
}
