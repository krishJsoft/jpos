package com.project.model.his;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kategoriler database table.
 * 
 */
@Entity
@Table(name="kategoriler")
public class Kategoriler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="kategori_id", unique=true, nullable=false)
	private int kategoriId;

	@Column(name="kat_ust_id")
	private int katUstId;

	@Column(name="kategori_adi", nullable=false, length=255)
	private String kategoriAdi;

	public Kategoriler() {
	}

	public int getKategoriId() {
		return this.kategoriId;
	}

	public void setKategoriId(int kategoriId) {
		this.kategoriId = kategoriId;
	}

	public int getKatUstId() {
		return this.katUstId;
	}

	public void setKatUstId(int katUstId) {
		this.katUstId = katUstId;
	}

	public String getKategoriAdi() {
		return this.kategoriAdi;
	}

	public void setKategoriAdi(String kategoriAdi) {
		this.kategoriAdi = kategoriAdi;
	}

}