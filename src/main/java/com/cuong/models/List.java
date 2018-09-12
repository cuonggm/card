package com.cuong.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LISTS")
public class List implements TimeManageable {

	@Id
	@GeneratedValue(generator = "increment")
	private Long id;
	private String name;
	@ManyToMany
	@JoinTable(name = "LISTS_WORDS", joinColumns = @JoinColumn(name = "listId"), inverseJoinColumns = @JoinColumn(name = "wordId"))
	private java.util.List<Word> words;
	private Date createdAt;
	private Date updatedAt;

	public List() {
		words = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.util.List<Word> getWords() {
		return words;
	}

	public void setWords(java.util.List<Word> words) {
		this.words = words;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@Override
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
