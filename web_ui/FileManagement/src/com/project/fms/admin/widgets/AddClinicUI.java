package com.project.fms.admin.widgets;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class AddClinicUI extends VLayout{

	
	public AddClinicUI(){
		
		setMargin(10);
		setMembersMargin(10);
		final ValuesManager formValuesManager = new ValuesManager();
		
		final TabSet clinicTabSet = new TabSet();
		clinicTabSet.setWidth100();
		clinicTabSet.setHeight("80%");
		
		Tab clinicDataTab = new Tab("Clinic Data");
		final ClinicDataForm clinicDataFormWidget = new ClinicDataForm();
		clinicDataFormWidget.setValuesManager(formValuesManager);
		clinicDataTab.setPane(clinicDataFormWidget);
		
		
		Tab clinicConnectionTab = new Tab("Clinic Connection");
		final ClinicConnectionDetailsForm clinicConnectionFormWidget = new ClinicConnectionDetailsForm();
		clinicConnectionFormWidget.setValuesManager(formValuesManager);
		clinicConnectionTab.setPane(clinicConnectionFormWidget);
		
		Tab clinicManagersTab = new Tab("Clinic Managers Information");
		final ClinicManagersDataForm clinicManagersWidget = new ClinicManagersDataForm();
		clinicManagersWidget.setValuesManager(formValuesManager);
		clinicManagersTab.setPane(clinicManagersWidget);
		
		Tab clinicRequirementsTab = new Tab("Clinic Requirements Information");
		
		ClinicRequirementsForm clinicRequirementsWidget = new ClinicRequirementsForm();
//		clinicRequirementsWidget.setValuesManager(formValuesManager);
		clinicRequirementsTab.setPane(clinicRequirementsWidget);
		
		clinicTabSet.setTabs(clinicDataTab, clinicConnectionTab, clinicManagersTab, clinicRequirementsTab);
		
		
		addMember(clinicTabSet);
		
		IButton submitButton = new IButton("Submit");
		submitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				//Comment the below steps to skip Validations 
				formValuesManager.validate();
				if(clinicDataFormWidget.hasErrors()){
					SC.say("Errors in tab clinicDataFormWidget");
					clinicTabSet.selectTab(0);
				}else if(clinicConnectionFormWidget.hasErrors()){
					SC.say("Errors in tab clinicConnectionFormWidget");
					clinicTabSet.selectTab(1);
				}else if(clinicManagersWidget.hasErrors()){
					SC.say("Errors in tab clinicManagersWidget");
					clinicTabSet.selectTab(2);
				}
				//Add your DS logic here
				else{
				
//				clinicDataFormWidget.clinicDataDs.addData(new ClinicData(
//					((TextItem) clinicDataFormWidget.getItem("clinicAbbr")).getValueAsString().toUpperCase(),
//					((TextItem) clinicDataFormWidget.getItem("clinicName")).getValueAsString(),
//					((TextAreaItem) clinicDataFormWidget.getItem("addressLine1")).getValueAsString(),
//					((TextAreaItem) clinicDataFormWidget.getItem("addressLine2")).getValueAsString(),
//					((TextItem) clinicDataFormWidget.getItem("location")).getValueAsString(),
//					((TextItem) clinicDataFormWidget.getItem("country")).getValueAsString(),
//					((TextItem) clinicDataFormWidget.getItem("zipcode")).getValueAsString()));
				}
			}
		});
		addMember(submitButton);
		
	}
}
