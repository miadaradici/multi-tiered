package com.multitired.model;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static com.multitired.model.Sprint.FIND_ALL;
import static com.multitired.model.Sprint.FIND_ALL_PROJECTS_SPRINTS;
import static com.multitired.model.Sprint.FIND_SPRINT_WITH_DATES;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity(name = "SPRINT")
@Table(name="SPRINT")
@NamedQueries({@NamedQuery(name = FIND_ALL, query = "SELECT a FROM SPRINT a"),
	@NamedQuery(name = FIND_ALL_PROJECTS_SPRINTS, query = "SELECT a FROM SPRINT a WHERE a.project = :project"),
	@NamedQuery(name = FIND_SPRINT_WITH_DATES, query = "SELECT a FROM SPRINT a WHERE a.project = :project AND :startDate BETWEEN a.startDate AND a.endDate OR :endDate BETWEEN a.startDate AND a.endDate ")})
public class Sprint implements Serializable{
	
	public Sprint (MetaSprint metaSprint){
		startDate = metaSprint.getStartDate();
		endDate = metaSprint.getEndDate();
		capacity = metaSprint.getCapacity();
	}
	
	public Sprint (){}
	
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "Sprint.findAll";
	public static final String FIND_ALL_PROJECTS_SPRINTS = "Sprint.findProjectsSprints";
	public static final String FIND_SPRINT_WITH_DATES = "Sprint.findSprintWithDates";
	
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
	
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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
