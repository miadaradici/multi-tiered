package com.eby.bestapp.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "users")
@Table(name="users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 3385648733103959508L;

	@Id
    @GeneratedValue
    private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
    private String surname;
	
	@Column(name = "username")
    private String username;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "role")
    private String role;

    @Enumerated(EnumType.STRING)
    @Column(name = "jobTitle")
    private String jobTitle;

    @ManyToMany(mappedBy = "participants")
    private List<Project> projects;
    
    @ManyToMany(mappedBy = "ceremonyParticipants")
    private List<Ceremony> ceremonies;
    
    @ManyToMany(mappedBy = "labeledUsers")
    private List<Comentariu> labels;
    
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id +", name="+name+", surname="+surname+", username="+username+", role="
				+role+", jobTitle="+jobTitle;
	}
	
	
}
