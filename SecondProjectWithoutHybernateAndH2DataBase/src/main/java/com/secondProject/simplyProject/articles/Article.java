package com.secondProject.simplyProject.articles;

public class Article {
	
	private String title;
	private String date;
	private String content;
	private String author;
	
	
	
	public Article(String title, String date, String content, String author) {
		super();
		this.title = title;
		this.date = date;
		this.content = content;
		this.author = author;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Article [title=" + title + ", date=" + date + ", content=" + content + ", author=" + author + "]";
	}
	

}
