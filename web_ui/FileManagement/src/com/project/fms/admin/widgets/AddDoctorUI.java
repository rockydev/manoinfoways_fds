package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class AddDoctorUI extends DynamicForm{
	
	public AddDoctorUI(){
		
		setMargin(50);
		
		setNumCols(4); 
		HeaderItem header = new HeaderItem();  
        header.setDefaultValue("Add New Doctor");  
  
        TextItem docName = new TextItem("docName", "Name: ");
        TextItem docEmail = new TextItem("docEmail", "Email ID: ");
        TextItem docPhno = new TextItem("docPhno", "Phone Number: ");
        TextItem docClinic = new TextItem("docClinic", "Clinic: ");
        TextAreaItem docAddr1 = new TextAreaItem("docAddr1", "Address1: ");
        TextAreaItem docAddr2 = new TextAreaItem("docAddr2", "Address2: ");
        TextItem docLocation = new TextItem("docLocation", "Location: ");
        TextItem docCountry = new TextItem("docCountry", "Country: ");
        TextItem docPincode = new TextItem("docPincode", "Pincode: ");
        
        docName.setRequired(true);
        docEmail.setRequired(true);
        docPhno.setRequired(true);
        docClinic.setRequired(true);
        docAddr1.setRequired(true);
        docAddr2.setRequired(true);
        docLocation.setRequired(true);
        docCountry.setRequired(true);
        docPincode.setRequired(true);
        
        
        ButtonItem submitButton = new ButtonItem();  
        submitButton.setTitle("Submit"); 
        submitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(validate() == true)
					submit();
			}
		});
        setFields(header,docName, docEmail, docPhno, docClinic, docAddr1, docAddr2, docLocation, docCountry, docPincode, submitButton);
	}

}

