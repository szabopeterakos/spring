package com.secondProject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Article {

	@GeneratedValue
	@Id
	private Long id;
	private String title;
	private String date;
	private String content;
	private Author author;

	public Article(String title, String date, String content, Author author) {
		super();
		this.title = title;
		this.date = date;
		this.content = content;
		this.author = author;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
