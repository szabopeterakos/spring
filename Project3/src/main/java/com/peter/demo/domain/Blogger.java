package com.peter.demo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="bloggers") // tábla reprezentáció
public class Blogger {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	@Column(name="first_name")
	private String first;
	@Column(name="last_name") // alias az sql-ben igy keresd vagy ilyet hozz létre oszlopszint
	private String last;
	@OneToMany(mappedBy="blogger")
	private List<Story> stories;
	
	private Blogger() {}
	
	public Blogger(String email, String first, String last, List<Story> stories) {
		super();
		this.email = email;
		this.first = first;
		this.last = last;
		this.stories = stories;
	}
	
	public Blogger(String first, String last) {
		super();
		this.first = first;
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
