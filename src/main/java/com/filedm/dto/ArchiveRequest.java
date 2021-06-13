package com.filedm.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArchiveRequest 
{

	
	@JsonProperty(required = false)
	private String documentName;
	
	@JsonProperty(required = false)
	private String documentFormat;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotNull
	private String documentType;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotNull
	private MultipartFile file;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotNull
	private String appSource;
	
	@JsonProperty(required = true)
	@NotEmpty
	@NotNull
	private int retentionDays;

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentFormat() {
		return documentFormat;
	}

	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getAppSource() {
		return appSource;
	}

	public void setAppSource(String appSource) {
		this.appSource = appSource;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getRetentionDays() {
		return retentionDays;
	}

	public void setRetentionDays(int retentionDays) {
		this.retentionDays = retentionDays;
	}

	@Override
	public String toString() {
		return "ArchiveRequest [documentName=" + documentName + ", documentFormat=" + documentFormat + ", documentType="
				+ documentType + ", file=" + file + ", appSource=" + appSource + ", retentionDays=" + retentionDays
				+ "]";
	}

	

	
	
}
