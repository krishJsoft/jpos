package com.project.model.datamodel;


public class SupplierdocumentModel {

	private int supplierdocumentId;
	private String documentName;
	private String fileextention;
	private String filelocation;
	private int supplierId;
	private String companyName;

	public int getSupplierdocumentId() {
		return supplierdocumentId;
	}

	public void setSupplierdocumentId(int supplierdocumentId) {
		this.supplierdocumentId = supplierdocumentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getFileextention() {
		return fileextention;
	}

	public void setFileextention(String fileextention) {
		this.fileextention = fileextention;
	}

	public String getFilelocation() {
		return filelocation;
	}

	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
