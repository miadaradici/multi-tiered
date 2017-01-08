package com.endava.model;
import static com.endava.model.User.FIND_ALL;
import static com.endava.model.User.FIND_USER_WITH_NAME;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity(name = "users")
@Table(name="users")
@NamedQueries({@NamedQuery(name = FIND_ALL, query = "SELECT a FROM users a"),
				@NamedQuery(name = FIND_USER_WITH_NAME, query = "SELECT a FROM users a WHERE a.name = :userName AND a.surname = :userSurname")})
public class User implements Serializable {
	
	private static final long serialVersionUID = 3385648733103959508L;
	public static final String FIND_ALL = "User.findAll";
	public static final String FIND_USER_WITH_NAME = "User.findUserWithName";
	
	public User (MetaUser metaUser ){
		name = metaUser.getName();
		email = metaUser.getEmail();
		password = metaUser.getPassword();
		surname = metaUser.getSurname();
		role = metaUser.getRole();
		username = metaUser.getUsername();
		jobTitle = JobTitle.valueOf(metaUser.getJobTitle());
	}
	
	public User (){}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "jobtitle")
    private JobTitle jobTitle;
    
    @ManyToMany(mappedBy = "participants", cascade = CascadeType.MERGE)
    private List<Project> projects;
//    
//    @ManyToMany(mappedBy = "ceremonyParticipants", cascade = CascadeType.MERGE)
//    private List<Ceremony> ceremonies;
//    
//    @ManyToMany(mappedBy = "labeledUsers", cascade = CascadeType.MERGE)
//    private List<Comentariu> labels;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Ceremony> getCeremonies() {
		return ceremonies;
	}

	public void setCeremonies(List<Ceremony> ceremonies) {
		this.ceremonies = ceremonies;
	}*/

/*	public List<Comentariu> getLabels() {
		return labels;
	}

	public void setLabels(List<Comentariu> labels) {
		this.labels = labels;
	}*/

    
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

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id +", name="+name+", surname="+surname+", username="+username+", role="
				+role+", jobTitle="+jobTitle;
	}
	
	
}
