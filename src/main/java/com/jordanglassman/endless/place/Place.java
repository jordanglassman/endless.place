package com.jordanglassman.endless.place;

import com.jordanglassman.endless.place.dao.BaseDao;
import com.jordanglassman.endless.place.entity.Document;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
public class Place {

	@EJB
	private BaseDao<Document> dao;

	@PostConstruct
	public void init() {
	    dao.setType(Document.class);
	}

	public Document createDocument(final Document document) {
		return this.dao.save(document);
	}

	public Document updateDocument(final Document document) {
		return this.dao.update(document);
	}

	public void deleteDocument(final Document document) {
		this.dao.delete(document);
	}

	public List<Document> getDocuments() {
		return this.dao.all();
	}

	public Document getDocument(final Long id) {
		return this.dao.get(id);
	}
}
