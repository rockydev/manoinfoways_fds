package com.project.fms.admin.widgets;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class MyTextItem extends TextItem{

	public MyTextItem() {
		super();
		super.setWidth(250);
		super.setRequired(true);
		super.setValue("");
		// TODO Auto-generated constructor stub
	}

	public MyTextItem(JavaScriptObject jsObj) {
		super(jsObj);
		super.setWidth(250);
		super.setRequired(true);
		super.setValue("");
		// TODO Auto-generated constructor stub
	}

	public MyTextItem(String name, String title) {
		super(name, title);
		super.setWidth(250);
		super.setRequired(true);
		super.setValue("");
		// TODO Auto-generated constructor stub
	}

	public MyTextItem(String name) {
		super(name);
		super.setWidth(250);
		super.setRequired(true);
		super.setValue("");
		// TODO Auto-generated constructor stub
	}

	

}
