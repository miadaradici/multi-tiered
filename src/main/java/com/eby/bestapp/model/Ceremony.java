package com.eby.bestapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CEREMONY")
public class Ceremony implements Serializable {
	
	private static final long serialVersionUID = 3385648733103959508L;

	@Id @GeneratedValue
	private Integer id;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "ceremonyType")
    private CeremonyType ceremonyType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ceremony_date")
	private Date date;
	
	@Column
	private String description;
	
	@Column
	private String photos;
	
	@ManyToOne
	@JoinColumn(name="id_sprint")
	private Sprint sprint;
	
	
	@ManyToMany
	@JoinTable(name="CEREMONY_USERS",
	joinColumns=@JoinColumn(name="id_ceremony"),
	inverseJoinColumns=@JoinColumn(name="id_user"))
	private List<User> ceremonyParticipants;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public CeremonyType getType() {
		return ceremonyType;
	}

	public void setType(CeremonyType type) {
		this.ceremonyType = type;
	}
	
	
}
