package com.peter.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="stories")
public class Story {

	@Id
	@GeneratedValue
	private Long id;
	private String postedDate;
	@Column(length=1000) // jobb verzió a @Column(columnDefinition = TEXT) // ami varcharról átállitja clob lesz.
	private String storyLine;
	
	@ManyToOne
	private Blogger blogger;
	
	
	private Story() {}
	

	public Story(String postedDate, String storyLine, Blogger blogger) {
		super();
		this.postedDate = postedDate;
		this.storyLine = storyLine;
		this.blogger = blogger;
	}

	


	public Story(String storyLine, Blogger blogger) {
		super();
		this.storyLine = storyLine;
		this.blogger = blogger;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPostedDate() {
		return postedDate;
	}


	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}


	public String getStoryLine() {
		return storyLine;
	}


	public void setStoryLine(String storyLine) {
		this.storyLine = storyLine;
	}


	public Blogger getBlogger() {
		return blogger;
	}


	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}


	
	
	
}
