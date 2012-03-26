package com.project.fms.admin.widgets;

import java.util.Date;

import com.project.fms.admin.widgets.data.ClinicConnectionDetailsData;
import com.project.fms.admin.widgets.data.ClinicData;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class AddClinicUI extends VLayout {

	private ClinicConnectionDetailsForm clinicConnectionFormWidget;

	private ClinicManagersDataForm clinicManagersWidget;

	public AddClinicUI() {

		setMargin(10);
		setMembersMargin(10);
		final ValuesManager formValuesManager = new ValuesManager();

		final TabSet clinicTabSet = new TabSet();
		clinicTabSet.setWidth100();
		clinicTabSet.setHeight("80%");

		Tab clinicDataTab = new Tab("Clinic Data");
		final ClinicDataForm clinicDataFormWidget = new ClinicDataForm();
		// clinicDataFormWidget.setValuesManager(formValuesManager);
		clinicDataTab.setPane(clinicDataFormWidget);

		Tab clinicConnectionTab = new Tab("Clinic Connection");
		clinicConnectionFormWidget = new ClinicConnectionDetailsForm();
		// clinicConnectionFormWidget.setValuesManager(formValuesManager);
		clinicConnectionTab.setPane(clinicConnectionFormWidget);

		Tab clinicManagersTab = new Tab("Clinic Managers Information");
		clinicManagersWidget = new ClinicManagersDataForm();
		// clinicManagersWidget.setValuesManager(formValuesManager);
		clinicManagersTab.setPane(clinicManagersWidget);

		Tab clinicRequirementsTab = new Tab("Clinic Requirements Information");

		ClinicRequirementsForm clinicRequirementsWidget = new ClinicRequirementsForm();
		// clinicRequirementsWidget.setValuesManager(formValuesManager);
		clinicRequirementsTab.setPane(clinicRequirementsWidget);

		clinicTabSet.setTabs(clinicDataTab, clinicConnectionTab,
				clinicManagersTab, clinicRequirementsTab);

		addMember(clinicTabSet);

		IButton submitButton = new IButton("Submit");
		submitButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// //Comment the below steps to skip Validations
				// formValuesManager.validate();
				// if(clinicDataFormWidget.hasErrors()){
				// SC.say("Errors in tab clinicDataFormWidget");
				// clinicTabSet.selectTab(0);
				// }else if(clinicConnectionFormWidget.hasErrors()){
				// SC.say("Errors in tab clinicConnectionFormWidget");
				// clinicTabSet.selectTab(1);
				// }else if(clinicManagersWidget.hasErrors()){
				// SC.say("Errors in tab clinicManagersWidget");
				// clinicTabSet.selectTab(2);
				// }
				// //Add your DS logic here
				// else{

				// Submitting Clinic Data values

				clinicDataFormWidget.clinicDataDs.addData(
						new ClinicData(
								((TextItem) clinicDataFormWidget
										.getItem("clinicAbbr"))
										.getValueAsString().toUpperCase(),
								((TextItem) clinicDataFormWidget
										.getItem("clinicName"))
										.getValueAsString(),
								((TextAreaItem) clinicDataFormWidget
										.getItem("addressLine1"))
										.getValueAsString(),
								((TextAreaItem) clinicDataFormWidget
										.getItem("addressLine2"))
										.getValueAsString(),
								((TextItem) clinicDataFormWidget
										.getItem("location"))
										.getValueAsString(),
								((TextItem) clinicDataFormWidget
										.getItem("country")).getValueAsString(),
								((TextItem) clinicDataFormWidget
										.getItem("zipcode")).getValueAsString()),
						new DSCallback() {

							@Override
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								if (response.getStatus() >= 0) {
									storeConnectionDetails(response.getData()[0]
											.getAttribute("clinicId"));
								} else {
									SC.say("Error storing Clinic Data!");
								}
							}
						});
				// }
			}
		});
		addMember(submitButton);
	}

	// Submitting Clinic Connection Details
	private void storeConnectionDetails(String clinicId) {
		// Setting the ADD url as per the REST route
		// "/fds/clinics/{clinicId}/conndetails"
		clinicConnectionFormWidget.connDetailsDs.setAddDataURL("/fds/clinics/"
				+ clinicId + "/conndetails");

		clinicConnectionFormWidget
				.setDataSource(clinicConnectionFormWidget.connDetailsDs);

		clinicConnectionFormWidget.connDetailsDs
				.addData(
						new ClinicConnectionDetailsData(
								new ClinicData(clinicId),
								((TextItem) clinicConnectionFormWidget
										.getItem("clinicConnectionType"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("userName"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("password"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("systemLoginUserName"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("systemLoginPassword"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("typeOfSoftware"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("mailOrFtpforVoiceFiles"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("recorderUsed"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("typeOfVoiceFiles"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("voiceFilesPath"))
										.getValueAsString(),
								((TextItem) clinicConnectionFormWidget
										.getItem("transcriptsPath"))
										.getValueAsString(),
								convertDateToString(((DateItem) clinicConnectionFormWidget
										.getItem("folderDate"))
										.getValueAsDate())), new DSCallback() {

							@Override
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								if (response.getStatus() >= 0) {
									SC.say("Success");
								} else {
									SC.say("Failure");
								}
							}
						});
	}

	@SuppressWarnings("deprecation")
	/**
	 * Method to convert the Date value to String format supported by Xstream DateConverter at RESTlet side.
	 * Format is "yyyy-MM-dd 00:00:00.0 IST"
	 * @param date
	 * @return
	 */
	private String convertDateToString(Date date) {
		String dateString = new Integer(1900 + date.getYear()).toString() + "-";

		// Adding padding to make month and date as MM/dd
		if (date.getMonth() < 10)
			dateString += "0" + new Integer(date.getMonth()).toString() + "-";
		else
			dateString += new Integer(date.getMonth()).toString() + "-";

		if (date.getDate() < 10)
			dateString += "0" + new Integer(date.getDate()).toString();
		else
			dateString += new Integer(date.getDate()).toString();

		// Adding timezone as IST (for xml conversion to Sql)
		dateString += " 00:00:00.0 IST";

		return dateString;
	}

}
