package com.manoinfoways.model;

// Generated Mar 30, 2012 7:20:30 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * InboundVoiceFilesData generated by hbm2java
 */
public class InboundVoiceFilesData implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4835558859617520901L;
	private Integer fileId;
	private Date timeStamp;
	private DoctorData doctorData;
	private ClinicData clinicData;
	private String filePath;
	private Date inboundDate;
	private BigDecimal lengthOfDictation;
	private Set<VoiceFilesAssignmentData> voiceFilesAssignmentDatas = new HashSet<VoiceFilesAssignmentData>(
			0);

	public InboundVoiceFilesData() {
	}

	public InboundVoiceFilesData(DoctorData doctorData, ClinicData clinicData,
			String filePath) {
		this.doctorData = doctorData;
		this.clinicData = clinicData;
		this.filePath = filePath;
	}

	public InboundVoiceFilesData(DoctorData doctorData, ClinicData clinicData,
			String filePath, Date inboundDate, BigDecimal lengthOfDictation,
			Set<VoiceFilesAssignmentData> voiceFilesAssignmentDatas) {
		this.doctorData = doctorData;
		this.clinicData = clinicData;
		this.filePath = filePath;
		this.inboundDate = inboundDate;
		this.lengthOfDictation = lengthOfDictation;
		this.voiceFilesAssignmentDatas = voiceFilesAssignmentDatas;
	}

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public DoctorData getDoctordata() {
		return this.doctorData;
	}

	public void setDoctordata(DoctorData doctorData) {
		this.doctorData = doctorData;
	}

	public ClinicData getClinicdata() {
		return this.clinicData;
	}

	public void setClinicdata(ClinicData clinicData) {
		this.clinicData = clinicData;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getInboundDate() {
		return this.inboundDate;
	}

	public void setInboundDate(Date inboundDate) {
		this.inboundDate = inboundDate;
	}

	public BigDecimal getLengthOfDictation() {
		return this.lengthOfDictation;
	}

	public void setLengthOfDictation(BigDecimal lengthOfDictation) {
		this.lengthOfDictation = lengthOfDictation;
	}

	public Set<VoiceFilesAssignmentData> getVoicefilesassignmentdatas() {
		return this.voiceFilesAssignmentDatas;
	}

	public void setVoicefilesassignmentdatas(
			Set<VoiceFilesAssignmentData> voiceFilesAssignmentDatas) {
		this.voiceFilesAssignmentDatas = voiceFilesAssignmentDatas;
	}

}
