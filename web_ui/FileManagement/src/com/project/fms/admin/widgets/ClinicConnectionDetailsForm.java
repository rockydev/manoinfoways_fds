package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;

public class ClinicConnectionDetailsForm extends DynamicForm{
	
	public ClinicConnectionDetailsForm(){
		setNumCols(4);
		setWidth100();
		setMargin(25);
		setCellPadding(10);
		MyTextItem clinicConnectionType = new MyTextItem("clinicConnectionType", "Clinic Connection Type");
		MyTextItem userName = new MyTextItem("userName", "Username");
		MyTextItem password = new MyTextItem("userName", "Password");
		MyTextItem sysUserName = new MyTextItem("sysUserName", "System Username");
		MyTextItem sysPassword = new MyTextItem("sysPassword", "System Password");
		MyTextItem sysType = new MyTextItem("softType", "Type of Software");
		MyTextItem mailFtpVoiceFilesType = new MyTextItem("mailFtpVoiceFilesType", "Mail or FTP for Voice Files");
		MyTextItem recorder = new MyTextItem("recorder", "Recorder Used");
		MyTextItem voiceFilesType = new MyTextItem("voiceFilesType", "Type of Voice Files");
		MyTextItem voiceFilesPath = new MyTextItem("voiceFilesPath", "Voice Files Path");
		MyTextItem transcriptsPath = new MyTextItem("transcriptsPath", "Transcripts Path");
		DateItem folderDate = new DateItem();  
		folderDate.setName("folderDate");  
		folderDate.setTitle("Folder Date"); 
		folderDate.setUseTextField(true);
		
		clinicConnectionType.setWidth(300);
		userName.setWidth(300);
		password.setWidth(300);
		sysUserName.setWidth(300);
		sysPassword.setWidth(300);
		sysType.setWidth(300);
		mailFtpVoiceFilesType.setWidth(300);
		recorder.setWidth(300);
		voiceFilesType.setWidth(300);
		voiceFilesPath.setWidth(300);
		transcriptsPath.setWidth(300);
		
		clinicConnectionType.setRequired(true);
		userName.setRequired(true);
		password.setRequired(true);
		sysUserName.setRequired(true);
		sysPassword.setRequired(true);
		sysType.setRequired(true);
		mailFtpVoiceFilesType.setRequired(true);
		recorder.setRequired(true);
		voiceFilesType.setRequired(true);
		voiceFilesPath.setRequired(true);
		transcriptsPath.setRequired(true);
		folderDate.setRequired(true);
		
        
		folderDate.setRequired(true);
		setFields(clinicConnectionType, userName, password, sysUserName, sysPassword, sysType, mailFtpVoiceFilesType, recorder, 
				voiceFilesType, voiceFilesPath, transcriptsPath, folderDate);
		
		
	}

}
