package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the supplierdocument database table.
 * 
 */
@Entity
@Table(name="supplierdocument")
public class Supplierdocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int supplierdocumentId;

	@Column(length=150)
	private String documentName;

	@Column(length=5)
	private String fileextention;

	@Column(length=200)
	private String filelocation;

	//bi-directional many-to-one association to Supplier
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supplierId")
	private Supplier supplier;

	public Supplierdocument() {
	}

	public int getSupplierdocumentId() {
		return this.supplierdocumentId;
	}

	public void setSupplierdocumentId(int supplierdocumentId) {
		this.supplierdocumentId = supplierdocumentId;
	}

	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getFileextention() {
		return this.fileextention;
	}

	public void setFileextention(String fileextention) {
		this.fileextention = fileextention;
	}

	public String getFilelocation() {
		return this.filelocation;
	}

	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}