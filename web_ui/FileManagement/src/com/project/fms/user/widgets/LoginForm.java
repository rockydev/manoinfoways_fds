package com.project.fms.user.widgets;

import com.project.fms.user.data.SessionHandler;
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
        
        final TextItem loginId = new TextItem("loginID", "Login ID: ");
        final PasswordItem passwordItem = new PasswordItem("password", "Password: ");
        ButtonItem submitButton = new ButtonItem();
        
        loginId.setRequired(true);
        passwordItem.setRequired(true);
        submitButton.setTitle("Submit"); 
        
        submitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(validate() == true){
					if(loginId.getValue().toString().equalsIgnoreCase(passwordItem.getValue().toString())){
						SessionHandler.getSessionInstance().setUserId(loginId.getValue().toString());
						SessionHandler.getSessionInstance().setTypeId((Integer.parseInt(passwordItem.getValue().toString())));
						submit();
					}
				}
				
				
			}
		});
        setFields(header,loginId,  passwordItem, submitButton);  
	}

}
