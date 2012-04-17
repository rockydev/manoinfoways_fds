package com.manoinfoways.model;

// Generated Apr 13, 2012 2:07:43 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * FileTransactionData generated by hbm2java
 */
public class FileTransactionData implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8741405274161951296L;
	private Integer fileTransactionId;
	private Date timeStamp;
	private TranscriberData transcriberData;
	private TranscriberTypeData transcriberTypeData;
	private VoiceFilesAssignmentData voiceFilesAssignmentData;
	private Byte status;

	public FileTransactionData() {
	}

	public FileTransactionData(TranscriberData transcriberData,
			TranscriberTypeData transcriberTypeData,
			VoiceFilesAssignmentData voiceFilesAssignmentData, Byte status) {
		this.transcriberData = transcriberData;
		this.transcriberTypeData = transcriberTypeData;
		this.voiceFilesAssignmentData = voiceFilesAssignmentData;
		this.status = status;
	}

	public Integer getFileTransactionId() {
		return this.fileTransactionId;
	}

	public void setFileTransactionId(Integer fileTransactionId) {
		this.fileTransactionId = fileTransactionId;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public TranscriberData getTranscriberdata() {
		return this.transcriberData;
	}

	public void setTranscriberdata(TranscriberData transcriberData) {
		this.transcriberData = transcriberData;
	}

	public TranscriberTypeData getTranscribertypedata() {
		return this.transcriberTypeData;
	}

	public void setTranscribertypedata(TranscriberTypeData transcriberTypeData) {
		this.transcriberTypeData = transcriberTypeData;
	}

	public VoiceFilesAssignmentData getVoicefilesassignmentdata() {
		return this.voiceFilesAssignmentData;
	}

	public void setVoicefilesassignmentdata(
			VoiceFilesAssignmentData voiceFilesAssignmentData) {
		this.voiceFilesAssignmentData = voiceFilesAssignmentData;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
