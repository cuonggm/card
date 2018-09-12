package com.cuong.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cuong.utils.DateUtils;

@Entity
@Table(name = "WORDS")
public class Word implements TimeManageable {

	@Id
	@GeneratedValue(generator = "increment")
	private Long id;

	@Column(name = "kanji")
	private String kanji;

	@Column(name = "hiragana")
	private String hiragara;

	@Column(name = "meaning")
	private String meaning;

	@Column(name = "amhanviet")
	private String amHanViet;

	@Column(name = "remembered", columnDefinition = "bit(1) default 0")
	private boolean remembered;

	@Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "updatedAt")
	private Date updatedAt;

	@ManyToMany(mappedBy = "words")
	private List<com.cuong.models.List> lists;

	public Word() {
		lists = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKanji() {
		return kanji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public String getHiragara() {
		return hiragara;
	}

	public void setHiragara(String hiragara) {
		this.hiragara = hiragara;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getAmHanViet() {
		return amHanViet;
	}

	public void setAmHanViet(String amHanViet) {
		this.amHanViet = amHanViet;
	}

	public boolean isRemembered() {
		return remembered;
	}

	public void setRemembered(boolean remembered) {
		this.remembered = remembered;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return super.toString() + "\n\tid: " + getId() + "\n\tkanji: " + getKanji() + "\n\thiragana: " + getHiragara()
				+ "\n\tmeaning: " + getMeaning() + "\n\tamHanViet: " + getAmHanViet() + "\n\tcreatedAt: "
				+ DateUtils.format(getCreatedAt()) + "\n\tupdatedAt: " + DateUtils.format(getUpdatedAt()) + "\n--";
	}

}
