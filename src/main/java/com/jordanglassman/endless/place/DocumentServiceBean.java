package com.jordanglassman.endless.place;

import com.jordanglassman.endless.place.dao.BaseDao;
import com.jordanglassman.endless.place.entity.Document;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DocumentServiceBean {

	@EJB
	private BaseDao<Document> documentDao;

	@PostConstruct
	public void init() {
	    documentDao.setType(Document.class);
	}

	public Document createDocument(final Document document) {
		return this.documentDao.save(document);
	}

	public Document updateDocument(final Document document) {
		return this.documentDao.update(document);
	}

	public void deleteDocument(final Document document) {
		this.documentDao.delete(document);
	}

	public List<Document> getDocuments() {
		return this.documentDao.all();
	}

	public Document getDocument(final Long id) {
		return this.documentDao.get(id);
	}
}
