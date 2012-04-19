package com.manoinfoways.model;

// Generated Mar 30, 2012 7:20:30 PM by Hibernate Tools 3.4.0.CR1

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * TranscriberData generated by hbm2java
 */
public class TranscriberData implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7978038622762435809L;
	private Integer transcriberId;
//	private TranscriberTypeData transcriberTypeData;
	private Integer transcriberTypeId;
	private String userName;
	private String password;
	private String transcriberName;
	private Date dateofJoining;
	private String transcriberAddress;
	private String transcriberPermanentAddress;
	private String transcriberPhoneNumber;
	private String transcriberMobile;
	private String transcriberEmail;
	private String transcriberQualification;
	private String transcriberExperience;
	private Date transcriberDob;
	private Time transcriberLoginTime;
	
	@XStreamOmitField
	private Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForTranscriberId = new HashSet<VoiceFilesAssignmentData>(
			0);
	@XStreamOmitField
	private Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForEditorId = new HashSet<VoiceFilesAssignmentData>(
			0);
	@XStreamOmitField
	private Set<FileTransactionData> fileTransactionDatas = new HashSet<FileTransactionData>(
			0);
	@XStreamOmitField
	private Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForProoferId = new HashSet<VoiceFilesAssignmentData>(
			0);
	@XStreamOmitField
	private Set<TranscriberDoctorPriority> transcriberDoctorPriorities = new HashSet<TranscriberDoctorPriority>(
			0);
	@XStreamOmitField
	private Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForQaid = new HashSet<VoiceFilesAssignmentData>(
			0);

	public TranscriberData() {
	}

	public TranscriberData(
//			TranscriberTypeData transcriberTypeData,
			Integer transcriberTypeId,
			String userName,
			String password,
			String transcriberName,
			Date dateofJoining,
			String transcriberAddress,
			String transcriberPermanentAddress,
			String transcriberPhoneNumber,
			String transcriberMobile,
			String transcriberEmail,
			String transcriberQualification,
			String transcriberExperience,
			Date transcriberDob,
			Time transcriberLoginTime,
			Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForTranscriberId,
			Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForEditorId,
			Set<FileTransactionData> fileTransactionDatas,
			Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForProoferId,
			Set<TranscriberDoctorPriority> transcriberDoctorPriorities,
			Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForQaid) {
//		this.transcriberTypeData = transcriberTypeData;
		this.transcriberTypeId = transcriberTypeId;
		this.userName = userName;
		this.password = password;
		this.transcriberName = transcriberName;
		this.dateofJoining = dateofJoining;
		this.transcriberAddress = transcriberAddress;
		this.transcriberPermanentAddress = transcriberPermanentAddress;
		this.transcriberPhoneNumber = transcriberPhoneNumber;
		this.transcriberMobile = transcriberMobile;
		this.transcriberEmail = transcriberEmail;
		this.transcriberQualification = transcriberQualification;
		this.transcriberExperience = transcriberExperience;
		this.transcriberDob = transcriberDob;
		this.transcriberLoginTime = transcriberLoginTime;
		this.voicefilesassignmentdatasForTranscriberId = voicefilesassignmentdatasForTranscriberId;
		this.voicefilesassignmentdatasForEditorId = voicefilesassignmentdatasForEditorId;
		this.fileTransactionDatas = fileTransactionDatas;
		this.voicefilesassignmentdatasForProoferId = voicefilesassignmentdatasForProoferId;
		this.transcriberDoctorPriorities = transcriberDoctorPriorities;
		this.voicefilesassignmentdatasForQaid = voicefilesassignmentdatasForQaid;
	}
	
	public TranscriberData(
//			TranscriberTypeData transcriberTypeData,
			Integer transcriberTypeId,
			String userName,
			String password,
			String transcriberName,
			Date dateofJoining,
			String transcriberAddress,
			String transcriberPermanentAddress,
			String transcriberPhoneNumber,
			String transcriberMobile,
			String transcriberEmail,
			String transcriberQualification,
			String transcriberExperience,
			Date transcriberDob,
			Time transcriberLoginTime) {
//		this.transcriberTypeData = transcriberTypeData;
		this.transcriberTypeId = transcriberTypeId;
		this.userName = userName;
		this.password = password;
		this.transcriberName = transcriberName;
		this.dateofJoining = dateofJoining;
		this.transcriberAddress = transcriberAddress;
		this.transcriberPermanentAddress = transcriberPermanentAddress;
		this.transcriberPhoneNumber = transcriberPhoneNumber;
		this.transcriberMobile = transcriberMobile;
		this.transcriberEmail = transcriberEmail;
		this.transcriberQualification = transcriberQualification;
		this.transcriberExperience = transcriberExperience;
		this.transcriberDob = transcriberDob;
		this.transcriberLoginTime = transcriberLoginTime;
	}
	
	public Integer getTranscriberId() {
		return this.transcriberId;
	}

	public void setTranscriberId(Integer transcriberId) {
		this.transcriberId = transcriberId;
	}

	/**
	 * @return the transcriberTypeId
	 */
	public Integer getTranscriberTypeId() {
		return transcriberTypeId;
	}

	/**
	 * @param transcriberTypeId the transcriberTypeId to set
	 */
	public void setTranscriberTypeId(Integer transcriberTypeId) {
		this.transcriberTypeId = transcriberTypeId;
	}

//	public TranscriberTypeData getTranscribertypedata() {
//		return this.transcriberTypeData;
//	}
//
//	public void setTranscribertypedata(TranscriberTypeData transcriberTypeData) {
//		this.transcriberTypeData = transcriberTypeData;
//	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTranscriberName() {
		return this.transcriberName;
	}

	public void setTranscriberName(String transcriberName) {
		this.transcriberName = transcriberName;
	}

	public Date getDateofJoining() {
		return this.dateofJoining;
	}

	public void setDateofJoining(Date dateofJoining) {
		this.dateofJoining = dateofJoining;
	}

	public String getTranscriberAddress() {
		return this.transcriberAddress;
	}

	public void setTranscriberAddress(String transcriberAddress) {
		this.transcriberAddress = transcriberAddress;
	}

	public String getTranscriberPermanentAddress() {
		return this.transcriberPermanentAddress;
	}

	public void setTranscriberPermanentAddress(
			String transcriberPermanentAddress) {
		this.transcriberPermanentAddress = transcriberPermanentAddress;
	}

	public String getTranscriberPhoneNumber() {
		return this.transcriberPhoneNumber;
	}

	public void setTranscriberPhoneNumber(String transcriberPhoneNumber) {
		this.transcriberPhoneNumber = transcriberPhoneNumber;
	}

	public String getTranscriberMobile() {
		return this.transcriberMobile;
	}

	public void setTranscriberMobile(String transcriberMobile) {
		this.transcriberMobile = transcriberMobile;
	}

	public String getTranscriberEmail() {
		return this.transcriberEmail;
	}

	public void setTranscriberEmail(String transcriberEmail) {
		this.transcriberEmail = transcriberEmail;
	}

	public String getTranscriberQualification() {
		return this.transcriberQualification;
	}

	public void setTranscriberQualification(String transcriberQualification) {
		this.transcriberQualification = transcriberQualification;
	}

	public String getTranscriberExperience() {
		return this.transcriberExperience;
	}

	public void setTranscriberExperience(String transcriberExperience) {
		this.transcriberExperience = transcriberExperience;
	}

	public Date getTranscriberDob() {
		return this.transcriberDob;
	}

	public void setTranscriberDob(Date transcriberDob) {
		this.transcriberDob = transcriberDob;
	}

	public Time getTranscriberLoginTime() {
		return this.transcriberLoginTime;
	}

	public void setTranscriberLoginTime(Time transcriberLoginTime) {
		this.transcriberLoginTime = transcriberLoginTime;
	}

	public Set<VoiceFilesAssignmentData> getVoicefilesassignmentdatasForTranscriberId() {
		return this.voicefilesassignmentdatasForTranscriberId;
	}

	public void setVoicefilesassignmentdatasForTranscriberId(
			Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForTranscriberId) {
		this.voicefilesassignmentdatasForTranscriberId = voicefilesassignmentdatasForTranscriberId;
	}

	public Set<VoiceFilesAssignmentData> getVoicefilesassignmentdatasForEditorId() {
		return this.voicefilesassignmentdatasForEditorId;
	}

	public void setVoicefilesassignmentdatasForEditorId(
			Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForEditorId) {
		this.voicefilesassignmentdatasForEditorId = voicefilesassignmentdatasForEditorId;
	}

	public Set<FileTransactionData> getFiletransactiondatas() {
		return this.fileTransactionDatas;
	}

	public void setFiletransactiondatas(
			Set<FileTransactionData> fileTransactionDatas) {
		this.fileTransactionDatas = fileTransactionDatas;
	}

	public Set<VoiceFilesAssignmentData> getVoicefilesassignmentdatasForProoferId() {
		return this.voicefilesassignmentdatasForProoferId;
	}

	public void setVoicefilesassignmentdatasForProoferId(
			Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForProoferId) {
		this.voicefilesassignmentdatasForProoferId = voicefilesassignmentdatasForProoferId;
	}
	
	public Set<TranscriberDoctorPriority> getTranscriberdoctorpriorities() {
		return this.transcriberDoctorPriorities;
	}

	public void setTranscriberdoctorpriorities(
			Set<TranscriberDoctorPriority> transcriberDoctorPriorities) {
		this.transcriberDoctorPriorities = transcriberDoctorPriorities;
	}
	
	public Set<VoiceFilesAssignmentData> getVoicefilesassignmentdatasForQaid() {
		return this.voicefilesassignmentdatasForQaid;
	}

	public void setVoicefilesassignmentdatasForQaid(
			Set<VoiceFilesAssignmentData> voicefilesassignmentdatasForQaid) {
		this.voicefilesassignmentdatasForQaid = voicefilesassignmentdatasForQaid;
	}

}
