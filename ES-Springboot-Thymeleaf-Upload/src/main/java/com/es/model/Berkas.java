package com.es.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Berkas {
	private String kodeBerkas;
	private byte[] byteBerkas;
	private String namaBerkas;
	private Date tglUpload;
	private String extension;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(length = 40, nullable = false)
	public String getKodeBerkas() {
		return kodeBerkas;
	}
	public void setKodeBerkas(String kodeBerkas) {
		this.kodeBerkas = kodeBerkas;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length=1048576)
	public byte[] getByteBerkas() {
		return byteBerkas;
	}
	public void setByteBerkas(byte[] byteBerkas) {
		this.byteBerkas = byteBerkas;
	}
	public String getNamaBerkas() {
		return namaBerkas;
	}
	public void setNamaBerkas(String namaBerkas) {
		this.namaBerkas = namaBerkas;
	}
	public Date getTglUpload() {
		return tglUpload;
	}
	public void setTglUpload(Date tglUpload) {
		this.tglUpload = tglUpload;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
}
