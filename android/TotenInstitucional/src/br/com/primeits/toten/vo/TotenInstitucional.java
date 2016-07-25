package br.com.primeits.toten.vo;

import java.util.ArrayList;

import android.widget.Button;

public class TotenInstitucional {
	
	private Button button;
	
	private String id;
	private String year;
	private String title;
	private ArrayList<Image> image;
	private String imagePath;
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<Image> getImage() {
		return image;
	}
	public void setImage(ArrayList<Image> image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}