package com.genzai.model;

public class Color {
	
	private Integer id;
	private String color;
	private String rgb;
	
	public Color(Integer id, String color, String rgb) {
		super();
		this.id = id;
		this.color = color;
		this.rgb = rgb;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRgb() {
		return rgb;
	}

	public void setRgb(String rgb) {
		this.rgb = rgb;
	}
	
}
