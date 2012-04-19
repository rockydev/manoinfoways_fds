package com.manoinfoways.model;

// Generated Mar 30, 2012 7:20:30 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * TranscriberTypeData generated by hbm2java
 */
public class TranscriberTypeData implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -806914259161969426L;
	private int transcriberTypeId;
	private String transcriberTypeName;

	@XStreamOmitField
	private Set<FileTransactionData> fileTransactionDatas = new HashSet<FileTransactionData>(
			0);

	@XStreamOmitField
	private Set<TranscriberData> transcriberDatas = new HashSet<TranscriberData>(
			0);

	@XStreamOmitField
	private Set<TranscriberDoctorPriority> transcriberDoctorPriorities = new HashSet<TranscriberDoctorPriority>(
			0);

	public TranscriberTypeData() {
	}

	public TranscriberTypeData(int transcriberTypeId) {
		this.transcriberTypeId = transcriberTypeId;
	}

	public TranscriberTypeData(int transcriberTypeId,
			String transcriberTypeName,
			Set<FileTransactionData> fileTransactionDatas,
			Set<TranscriberData> transcriberDatas,
			Set<TranscriberDoctorPriority> transcriberDoctorPriorities) {
		this.transcriberTypeId = transcriberTypeId;
		this.transcriberTypeName = transcriberTypeName;
		this.fileTransactionDatas = fileTransactionDatas;
		this.transcriberDatas = transcriberDatas;
		this.transcriberDoctorPriorities = transcriberDoctorPriorities;
	}

	public TranscriberTypeData(int transcriberTypeId, String transcriberTypeName) {
		this.transcriberTypeId = transcriberTypeId;
		this.transcriberTypeName = transcriberTypeName;
	}

	public int getTranscriberTypeId() {
		return this.transcriberTypeId;
	}

	public void setTranscriberTypeId(int transcriberTypeId) {
		this.transcriberTypeId = transcriberTypeId;
	}

	public String getTranscriberTypeName() {
		return this.transcriberTypeName;
	}

	public void setTranscriberTypeName(String transcriberTypeName) {
		this.transcriberTypeName = transcriberTypeName;
	}

	public Set<FileTransactionData> getFiletransactiondatas() {
		return this.fileTransactionDatas;
	}

	public void setFiletransactiondatas(
			Set<FileTransactionData> fileTransactionDatas) {
		this.fileTransactionDatas = fileTransactionDatas;
	}

	public Set<TranscriberData> getTranscriberdatas() {
		return this.transcriberDatas;
	}

	public void setTranscriberdatas(Set<TranscriberData> transcriberDatas) {
		this.transcriberDatas = transcriberDatas;
	}

	public Set<TranscriberDoctorPriority> getTranscriberdoctorpriorities() {
		return this.transcriberDoctorPriorities;
	}

	public void setTranscriberdoctorpriorities(
			Set<TranscriberDoctorPriority> transcriberDoctorPriorities) {
		this.transcriberDoctorPriorities = transcriberDoctorPriorities;
	}
}
