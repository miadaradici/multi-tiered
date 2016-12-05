package com.eby.bestapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="COMENTARIU")
public class Comentariu implements Serializable{
	private static final long serialVersionUID = 3385648733103959508L;
	
	@Id @GeneratedValue
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="author")
	private User author;
	
	@Column(name="description")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="comment_time")
	private Date commentTime;
	
	@ManyToMany
	@JoinTable(name="USERS_LABELS",
	joinColumns=@JoinColumn(name="id_comment"),
	inverseJoinColumns=@JoinColumn(name="id_user"))
	private List<User> labeledUsers;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public List<User> getLabeledUsers() {
		return labeledUsers;
	}

	public void setLabeledUsers(List<User> labeledUsers) {
		this.labeledUsers = labeledUsers;
	}



}
