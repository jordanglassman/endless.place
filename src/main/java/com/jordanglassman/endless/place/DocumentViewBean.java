package com.jordanglassman.endless.place;

import com.jordanglassman.endless.place.entity.Document;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@ManagedBean
@ViewScoped
public class DocumentViewBean {
	private static final Logger LOG = LoggerFactory.getLogger(DocumentViewBean.class);

	@Inject
	private Place place;

	private Document document;

	public DocumentViewBean() {
		this.document = new Document();
	}

	public List<Document> all() {
		return this.place.getDocuments();
	}

	public String save() {
		LOG.info("save() - this.toString() = {}", this.toString());
		this.place.createDocument(this.document);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("document", this.document);
		return "success";
	}

	public String getId() {
		return Objects.nonNull(this.document.getId()) ? this.document.getId().toString() : "";
	}

	public void setId(final String documentId) {
		this.document = this.place.getDocument(Long.valueOf(documentId));
	}

	public String getTitle() {
		return this.document.getTitle();
	}

	public void setTitle(String title) {
		this.document.setTitle(title);
	}

	public String getBody() {
		return this.document.getBody();
	}

	public void setBody(String body) {
		LOG.info("setBody() - body = {}", body);
		this.document.setBody(body);
	}

	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this) {
			@Override
			protected boolean accept(Field field) {
				return super.accept(field) && !field.getName().equals("place");
			}
		}.toString();
	}
}
