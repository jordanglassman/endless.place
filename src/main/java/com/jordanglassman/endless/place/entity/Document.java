package com.jordanglassman.endless.place.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column
	private String title;

	@Column
	private String url;

	@Column
	private String body;

	@Transient
	private String description;

	public Long getId() {
		return this.id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return (new ReflectionToStringBuilder(this) {
			protected boolean accept(Field f) {
				return super.accept(f) && !f.getName().equals("description");
			}
		}).toString();
	}
}
