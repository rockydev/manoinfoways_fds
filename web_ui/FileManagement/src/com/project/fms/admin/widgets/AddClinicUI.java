package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class AddClinicUI extends VLayout{
	
	public AddClinicUI(){
		
		setMargin(10);
		setMembersMargin(10);
		ValuesManager formValuesManager = new ValuesManager();
		
		TabSet clinicTabSet = new TabSet();
		clinicTabSet.setWidth100();
		clinicTabSet.setHeight("80%");
		
		Tab clinicDataTab = new Tab("Clinic Data");
		ClinicDataForm clinicDataFormWidget = new ClinicDataForm();
		clinicDataFormWidget.setValuesManager(formValuesManager);
		
		Tab clinicConnectionTab = new Tab("Clinic Connection");
		ClinicConnectionDetailsForm clinicConnectionFormWidget = new ClinicConnectionDetailsForm();
		clinicConnectionFormWidget.setValuesManager(formValuesManager);
		
		
		clinicDataTab.setPane(clinicDataFormWidget);
		clinicConnectionTab.setPane(clinicConnectionFormWidget);
		clinicTabSet.addTab(clinicDataTab);
		clinicTabSet.addTab(clinicConnectionTab);
		
		
		addMember(clinicTabSet);
		
		IButton submitButton = new IButton("Submit");
		addMember(submitButton);
	}
}
