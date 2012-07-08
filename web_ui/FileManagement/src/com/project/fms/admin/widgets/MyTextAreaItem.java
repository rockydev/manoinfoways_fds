package com.project.fms.admin.widgets;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;

public class MyTextAreaItem extends TextAreaItem{

	public MyTextAreaItem(){
		super();
		super.setWidth(250);
		super.setRequired(true);
		super.setValue("");
	}
	
	public MyTextAreaItem(JavaScriptObject jsObj){
		super(jsObj);
		super.setWidth(250);
		super.setRequired(true);
		super.setValue("");
	}
	public MyTextAreaItem(String name, String title){
		super(name, title);
		super.setWidth(250);
		super.setRequired(true);
		super.setValue("");
	}
	public MyTextAreaItem(String name){
		super(name);
		super.setWidth(250);
		super.setRequired(true);
		super.setValue("");
	}
}
