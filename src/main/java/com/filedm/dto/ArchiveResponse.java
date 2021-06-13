package com.filedm.dto;

public class ArchiveResponse
{
	private int statusCode;
	private String docLink;
	private long docId;
	private String message;
	
	public ArchiveResponse(int status_Code, String doc_Link, long d, String message)
	{	
		this.statusCode = status_Code;
		this.docLink = doc_Link;
		this.docId = d;
		this.message = message;
	}
	
	public ArchiveResponse(int status_Code, String message)
	{	
		this.statusCode = status_Code;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDocLink() {
		return docLink;
	}

	public void setDocLink(String docLink) {
		this.docLink = docLink;
	}

	public double getDocId() {
		return docId;
	}

	public void setDocId(long docId) {
		this.docId = docId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ArchiveResponse [statusCode=" + statusCode + ", docLink=" + docLink + ", docId=" + docId + ", message="
				+ message + "]";
	}

	
	
}
