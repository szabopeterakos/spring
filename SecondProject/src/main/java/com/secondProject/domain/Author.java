package com.secondProject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Author {

	@GeneratedValue
	@Id
	private Long id;
	private String name;
	private int phone;
	private List<Article> articles;

	public Author(String name, int phone, List<Article> articles) {
		super();
		this.name = name;
		this.phone = phone;
		this.articles = articles;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
