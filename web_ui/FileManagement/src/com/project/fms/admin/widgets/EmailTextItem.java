package com.project.fms.admin.widgets;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

public class EmailTextItem extends TextItem{
	RegExpValidator regExpValidator = new RegExpValidator();  
    
	public EmailTextItem() {
		super();
		super.setWidth(250);
		super.setRequired(true);
		regExpValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
	    super.setValidators(regExpValidator);
		// TODO Auto-generated constructor stub
	}

	public EmailTextItem(JavaScriptObject jsObj) {
		super(jsObj);
		super.setWidth(250);
		super.setRequired(true);
		regExpValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
	    super.setValidators(regExpValidator);
		// TODO Auto-generated constructor stub
	}

	public EmailTextItem(String name, String title) {
		super(name, title);
		super.setWidth(250);
		super.setRequired(true);
		regExpValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
	    super.setValidators(regExpValidator);
		// TODO Auto-generated constructor stub
	}

	public EmailTextItem(String name) {
		super(name);
		super.setWidth(250);
		super.setRequired(true);
		regExpValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
	    super.setValidators(regExpValidator);
		// TODO Auto-generated constructor stub
	}

}
