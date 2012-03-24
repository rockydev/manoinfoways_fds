package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class LoginForm extends DynamicForm{
	
	public LoginForm(){
	    setWidth(400);
  
        HeaderItem header = new HeaderItem();  
        header.setDefaultValue("Login to File Systems Management");  
  
        PasswordItem passwordItem = new PasswordItem("password", "Password: ");
        TextItem loginId = new TextItem("loginID", "Login ID: ");
        
//        loginId.setRequired(true);
//        passwordItem.setRequired(true);
        
        ButtonItem submitButton = new ButtonItem();  
        submitButton.setTitle("Submit"); 
        submitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(validate() == true)
					submit();
			}
		});
        setFields(header,loginId,  passwordItem, submitButton);  
	}

}
