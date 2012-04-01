/**
 * 
 */
package com.project.fms.admin.widgets.data;

import com.smartgwt.client.data.Record;

/**
 * @author rdevinen
 *
 */
public class ClinicConnectionDetailsData extends Record{

	public ClinicConnectionDetailsData(){
		
	}
	
	public ClinicConnectionDetailsData(String clinicConnectionId)
	{
		setClinicConnectionId(clinicConnectionId);
	}
	
//	public ClinicConnectionDetailsData(ClinicData clinicData,
//			String clinicConnectionType, String userName, String password,
//			String systemLoginUserName, String systemLoginPassword,
//			String typeOfSoftware, String mailOrFtpforVoiceFiles,
//			String recorderUsed, String typeOfVoiceFiles,
//			String voiceFilesPath, String transcriptsPath, String date) {
//		setClinicdata(clinicData);
//		setClinicConnectionType(clinicConnectionType);
//		setUserName(userName);
//		setPassword(password);
//		setSystemLoginUserName(systemLoginUserName);
//		setSystemLoginPassword(systemLoginPassword);
//		setTypeOfSoftware(typeOfSoftware);
//		setMailOrFtpforVoiceFiles(mailOrFtpforVoiceFiles);
//		setRecorderUsed(recorderUsed);
//		setTypeOfVoiceFiles(typeOfVoiceFiles);
//		setVoiceFilesPath(voiceFilesPath);
//		setTranscriptsPath(transcriptsPath);
//		setFolderDate(date);
//	}
	
	public ClinicConnectionDetailsData(String clinicConnectionType, 
			String userName, String password,
			String systemLoginUserName, String systemLoginPassword,
			String typeOfSoftware, String mailOrFtpforVoiceFiles,
			String recorderUsed, String typeOfVoiceFiles,
			String voiceFilesPath, String transcriptsPath, String date) {
		setClinicConnectionType(clinicConnectionType);
		setUserName(userName);
		setPassword(password);
		setSystemLoginUserName(systemLoginUserName);
		setSystemLoginPassword(systemLoginPassword);
		setTypeOfSoftware(typeOfSoftware);
		setMailOrFtpforVoiceFiles(mailOrFtpforVoiceFiles);
		setRecorderUsed(recorderUsed);
		setTypeOfVoiceFiles(typeOfVoiceFiles);
		setVoiceFilesPath(voiceFilesPath);
		setTranscriptsPath(transcriptsPath);
		setFolderDate(date);
	}
	
	public String getClinicConnectionId() {
		return getAttributeAsString("clinicConnectionId");
	}

	public void setClinicConnectionId(String clinicConnectionId) {
		setAttribute("clinicConnectionId",clinicConnectionId);
	}

	public String getClinicConnectionType() {
		return getAttributeAsString("clinicConnectionType");
	}

	public void setClinicConnectionType(String clinicConnectionType) {
		setAttribute("clinicConnectionType",clinicConnectionType);
	}

	public String getUserName() {
		return getAttributeAsString("userName");
	}

	public void setUserName(String userName) {
		setAttribute("userName",userName);
	}

	public String getPassword() {
		return getAttributeAsString("password");
	}

	public void setPassword(String password) {
		setAttribute("password",password);
	}

	public String getSystemLoginUserName() {
		return getAttributeAsString("systemLoginUserName");
	}

	public void setSystemLoginUserName(String systemLoginUserName) {
		setAttribute("systemLoginUserName",systemLoginUserName);
	}

	public String getSystemLoginPassword() {
		return getAttributeAsString("systemLoginPassword");
	}

	public void setSystemLoginPassword(String systemLoginPassword) {
		setAttribute("systemLoginPassword",systemLoginPassword);
	}

	public String getTypeOfSoftware() {
		return getAttributeAsString("typeOfSoftware");
	}

	public void setTypeOfSoftware(String typeOfSoftware) {
		setAttribute("typeOfSoftware",typeOfSoftware);
	}

	public String getMailOrFtpforVoiceFiles() {
		return getAttributeAsString("mailOrFtpforVoiceFiles");
	}

	public void setMailOrFtpforVoiceFiles(String mailOrFtpforVoiceFiles) {
		setAttribute("mailOrFtpforVoiceFiles",mailOrFtpforVoiceFiles);
	}

	public String getRecorderUsed() {
		return getAttributeAsString("recorderUsed");
	}

	public void setRecorderUsed(String recorderUsed) {
		setAttribute("recorderUsed",recorderUsed);
	}

	public String getTypeOfVoiceFiles() {
		return getAttributeAsString("typeOfVoiceFiles");
	}

	public void setTypeOfVoiceFiles(String typeOfVoiceFiles) {
		setAttribute("typeOfVoiceFiles",typeOfVoiceFiles);
	}

	public String getVoiceFilesPath() {
		return getAttributeAsString("voiceFilesPath");
	}

	public void setVoiceFilesPath(String voiceFilesPath) {
		setAttribute("voiceFilesPath",voiceFilesPath);
	}

	public String getTranscriptsPath() {
		return getAttributeAsString("transcriptsPath");
	}

	public void setTranscriptsPath(String transcriptsPath) {
		setAttribute("transcriptsPath",transcriptsPath);
	}

	public String getFolderDate() {
		return getAttributeAsString("folderDate");
		
	}

	public void setFolderDate(String date) {
		setAttribute("folderDate",date);
	}
	
}
