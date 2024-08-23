package com.Postman.DTO;

public class MovieDTO {

	private int id;
	private String title;
	private int ratingValues;
	private String username;
	
	public MovieDTO(int id, String title, int ratingValue) {
		this.id=id;
		this.title=title;
		this.ratingValues=ratingValue;
	}
	public MovieDTO(String title, int ratingValue) {
        this.title = title;
        this.ratingValues = ratingValue;
    }
	public MovieDTO(int id, String title, String username, int ratingValue) {
		this.id=id;
		this.title=title;
		this.username=username;
		this.ratingValues=ratingValue;
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRatingValues() {
		return ratingValues;
	}
	public void setRatingValues(int ratingValues) {
		this.ratingValues = ratingValues;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", title=" + title + ", ratingValues=" + ratingValues + ", username=" + username
				+ "]";
	}
	
	
	
}
