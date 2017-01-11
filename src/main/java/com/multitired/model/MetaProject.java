package com.multitired.model;

public class MetaProject {

	private Integer id;
	private String name;
	private User scrumMaster;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getScrumMaster() {
		return scrumMaster;
	}
	public void setScrumMaster(User scrumMaster) {
		this.scrumMaster = scrumMaster;
	}
	
}
