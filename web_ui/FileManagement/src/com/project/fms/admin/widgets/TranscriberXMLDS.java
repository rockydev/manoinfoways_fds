package com.project.fms.admin.widgets;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class TranscriberXMLDS extends DataSource{
	private static TranscriberXMLDS instance = null;

	public static TranscriberXMLDS getInstance() {
		if (instance == null) {
			instance = new TranscriberXMLDS("transcriberDS");
		}
		return instance;
	}

	public TranscriberXMLDS(String id) {

		setID(id);
		setRecordXPath("/List/transcriber");

		DataSourceTextField transcriberId = new DataSourceTextField(
				"transcriberid", "Transcriber ID");
		DataSourceTextField password = new DataSourceTextField(
				"password", "Password");
		DataSourceTextField type = new DataSourceTextField(
				"type", "TYPE");
		DataSourceDateField dateofjoiningField = new DataSourceDateField("dateofjoining", "DOJ");  
		DataSourceTextField addressField = new DataSourceTextField("address", "Address", 500);
		DataSourceTextField permanentAddressField = new DataSourceTextField("permanentAddress", "Permanent Address", 500);
		DataSourceTextField phnumField = new DataSourceTextField("phnum", "Ph No");
		DataSourceTextField mobileField = new DataSourceTextField("mobile", "Mobile");
		DataSourceTextField emailField = new DataSourceTextField("email", "Email");
		
		emailField.setRequired(true);
		
		DataSourceTextField qualificationField = new DataSourceTextField("qualification", "Qualification");
		DataSourceTextField experienceField = new DataSourceTextField("experience", "Experience");
		DataSourceTextField dobField = new DataSourceTextField("dob", "DOB");
		DataSourceDateField lastLoginField = new DataSourceDateField("lastLogin", "Last Login");
		
		
		setFields(transcriberId, password, type,dateofjoiningField, addressField,permanentAddressField,phnumField,
				mobileField, emailField, qualificationField, experienceField, dobField, lastLoginField);
		
		
		transcriberId.setRequired(true);
		password.setRequired(true);
		type.setRequired(true);
		dateofjoiningField.setRequired(true); 
		addressField.setRequired(true);
		permanentAddressField.setRequired(true);
		phnumField.setRequired(true);
		mobileField.setRequired(true); 
		emailField.setRequired(true); 
		qualificationField.setRequired(true); 
		experienceField.setRequired(true); 
		dobField.setRequired(true); 
		lastLoginField.setRequired(true);
		setDataURL("transcriber.data.xml");
		setClientOnly(true);
	}
}