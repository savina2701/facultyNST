package com.nst.faculty.menu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem {
	 public String id;
	 public String label;
	 public String title;
	 public String icon;
	 public String url;
	 
	 @JsonCreator
	 public MenuItem(@JsonProperty("id") String id, @JsonProperty("label") String label, @JsonProperty("title") String title, @JsonProperty("icon") String icon, @JsonProperty("url") String url) {
		this.id = id;
		this.label = label;
		this.title = title;
		this.icon = icon;
		this.url = url;
	}
	 
	 public MenuItem() {
		// TODO Auto-generated constructor stub
	}
	 
	 
}
