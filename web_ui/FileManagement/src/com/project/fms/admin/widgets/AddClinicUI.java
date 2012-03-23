package com.project.fms.admin.widgets;

import com.google.gwt.user.client.ui.TextArea;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
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
		final ClinicDataForm clinicDataFormWidget = new ClinicDataForm();
//		clinicDataFormWidget.setValuesManager(formValuesManager);
		
		Tab clinicConnectionTab = new Tab("Clinic Connection");
		ClinicConnectionDetailsForm clinicConnectionFormWidget = new ClinicConnectionDetailsForm();
//		clinicConnectionFormWidget.setValuesManager(formValuesManager);
		
		
		clinicDataTab.setPane(clinicDataFormWidget);
		clinicConnectionTab.setPane(clinicConnectionFormWidget);
		clinicTabSet.addTab(clinicDataTab);
		clinicTabSet.addTab(clinicConnectionTab);
		
		
		addMember(clinicTabSet);
		
		IButton submitButton = new IButton("Submit");
		submitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
//				clinicDataFormWidget.clinicDataDs.addData(record)
//				clinicDataFormWidget.getItem(name)
				clinicDataFormWidget.clinicDataDs.addData(new ClinicData(
					((TextItem) clinicDataFormWidget.getItem("clinicAbbr")).getValueAsString(),
					((TextItem) clinicDataFormWidget.getItem("clinicName")).getValueAsString(),
					((TextAreaItem) clinicDataFormWidget.getItem("addressLine1")).getValueAsString(),
					((TextAreaItem) clinicDataFormWidget.getItem("addressLine2")).getValueAsString(),
					((TextItem) clinicDataFormWidget.getItem("location")).getValueAsString(),
					((TextItem) clinicDataFormWidget.getItem("country")).getValueAsString(),
					((TextItem) clinicDataFormWidget.getItem("zipcode")).getValueAsString()));
			}
		});
		addMember(submitButton);
		
	}
}
