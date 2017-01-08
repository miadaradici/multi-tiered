package com.endava.model;

import static com.endava.model.Project.FIND_ALL;
import static com.endava.model.Project.FIND_PROJECT_WITH_NAME;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;

@Entity(name = "PROJECT")
@Table(name = "PROJECT")
@NamedQueries({ @NamedQuery(name = FIND_ALL, query = "SELECT a FROM PROJECT a"),
		@NamedQuery(name = FIND_PROJECT_WITH_NAME, query = "SELECT a FROM PROJECT a WHERE a.name = :name") })
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Project.findAll";
	public static final String FIND_PROJECT_WITH_NAME = "Project.findProject";

	public Project() {
	}

	public Project(MetaProject metaProject) {
		this.name = metaProject.getName();
		this.scrumMaster = metaProject.getScrumMaster();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "USERS_PROJECT", joinColumns = @JoinColumn(name = "id_project"), inverseJoinColumns = @JoinColumn(name = "id_user"))
	private List<User> participants = new ArrayList<User>();

	@OneToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "idScrumMaster")
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

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public User getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(User scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

}
