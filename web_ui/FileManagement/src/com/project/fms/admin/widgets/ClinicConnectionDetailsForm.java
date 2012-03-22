package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class ClinicConnectionDetailsForm extends DynamicForm{
	
	public ClinicConnectionDetailsForm(){
		setNumCols(4);
		
		setMargin(50);
		TextItem clinicConnectionType = new TextItem("clinicConnectionType", "Clinic Connection Type");
		TextItem userName = new TextItem("userName", "Username");
		TextItem password = new TextItem("userName", "Password");
		TextItem sysUserName = new TextItem("sysUserName", "System Username");
		TextItem sysPassword = new TextItem("sysPassword", "System Password");
		TextItem sysType = new TextItem("softType", "Type of Software");
		TextItem mailFtpVoiceFilesType = new TextItem("mailFtpVoiceFilesType", "Mail or FTP for Voice Files");
		TextItem recorder = new TextItem("recorder", "Recorder User");
//		TextItem sysType = new TextItem("softType", "Mail or FTP for Voice Files");
		
		
		setFields(clinicConnectionType, userName, password, sysUserName, sysPassword, sysType, mailFtpVoiceFilesType, recorder);
		
		
	}

}
