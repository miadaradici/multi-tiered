package com.endava.model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name="SPRINT")
public class Sprint implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@Column
	private Integer capacity;
	
	@ManyToOne
	@JoinColumn(name="id_project")
	private Project project;
	
//	@OneToMany(mappedBy = "sprint", cascade=ALL, fetch=EAGER)
//	private List<Ceremony> ceremonies = new ArrayList<Ceremony>();
//	
//	@OneToMany(mappedBy = "sprint", cascade=ALL, fetch=EAGER)
//	private List<Ceremony> capacities = new ArrayList<Ceremony>();

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

//	public List<Ceremony> getCeremonies() {
//		return ceremonies;
//	}
//
//	public void setCeremonies(List<Ceremony> ceremonies) {
//		this.ceremonies = ceremonies;
//	}
//	
}
