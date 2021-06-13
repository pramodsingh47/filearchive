package com.filedm.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ach_file_sys")
public class ArchiveFileSystem 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long archiveID;
	private String docName;
	private String docFormate;
	private String docType;
	private String appSource;
	private String fileUrl;
	private Timestamp docCreationDate;
	private Timestamp retenDate;
	public long getArchiveID() {
		return archiveID;
	}
	public void setArchiveID(long archiveID) {
		this.archiveID = archiveID;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocFormate() {
		return docFormate;
	}
	public void setDocFormate(String docFormate) {
		this.docFormate = docFormate;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getAppSource() {
		return appSource;
	}
	public void setAppSource(String appSource) {
		this.appSource = appSource;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Timestamp getDocCreationDate() {
		return docCreationDate;
	}
	public void setDocCreationDate(Timestamp docCreationDate) {
		this.docCreationDate = docCreationDate;
	}
	public Timestamp getRetenDate() {
		return retenDate;
	}
	public void setRetenDate(Timestamp retenDate) {
		this.retenDate = retenDate;
	}
	@Override
	public String toString() {
		return "ArchiveFileSystem [archiveID=" + archiveID + ", docName=" + docName + ", docFormate=" + docFormate
				+ ", docType=" + docType + ", appSource=" + appSource + ", fileUrl=" + fileUrl + ", docCreationDate="
				+ docCreationDate + ", retenDate=" + retenDate + "]";
	}
	
	
	
	
	
	
}
