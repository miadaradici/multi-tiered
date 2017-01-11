package com.multitired.model;

import static com.multitired.model.Ceremony.FIND_ALL;
import static com.multitired.model.Ceremony.FIND_ALL_SPRINTS_CEREMONIES;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.mapping.MetadataSource;

@Entity(name="CEREMONY")
@Table(name="CEREMONY")
@NamedQueries({@NamedQuery(name = FIND_ALL, query = "SELECT a FROM CEREMONY a"),
	@NamedQuery(name = FIND_ALL_SPRINTS_CEREMONIES, query = "SELECT a FROM CEREMONY a WHERE a.sprint = :sprint"),
	})
public class Ceremony implements Serializable {
	
	private static final long serialVersionUID = 3385648733103959508L;
	public static final String FIND_ALL = "Ceremony.findAll";
	public static final String FIND_ALL_SPRINTS_CEREMONIES = "Ceremony.findSprintsCeremonies";
	
	public Ceremony(){
	}
	
	public Ceremony ( MetaCeremony metaCeremony){
		ceremonyType = metaCeremony.getCeremonyType();
		date = metaCeremony.getDate();
		description = metaCeremony.getDescription();
		sprint = metaCeremony.getSprint();
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	
	@ManyToMany(cascade = { CascadeType.MERGE } )
	@JoinTable(name="CEREMONY_USERS", joinColumns=@JoinColumn(name="id_ceremony"), inverseJoinColumns=@JoinColumn(name="id_user"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<User> ceremonyParticipants = new ArrayList<User>();

	public List<User> getCeremonyParticipants() {
		return ceremonyParticipants;
	}

	public void setCeremonyParticipants(List<User> ceremonyParticipants) {
		this.ceremonyParticipants = ceremonyParticipants;
	}

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
