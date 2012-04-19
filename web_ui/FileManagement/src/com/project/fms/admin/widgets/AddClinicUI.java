package com.project.fms.admin.widgets;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.project.fms.admin.widgets.data.ClinicConnectionDetailsData;
import com.project.fms.admin.widgets.data.ClinicData;
import com.project.fms.admin.widgets.data.ClinicMetadataData;
import com.project.fms.admin.widgets.data.SessionData;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

public class AddClinicUI extends VLayout {

	private ClinicConnectionDetailsForm clinicConnectionFormWidget;

	private ClinicManagersDataForm clinicManagersWidget;

	private ClinicDataForm clinicDataFormWidget;

	private Tab clinicDataTab;

	private IButton submitButton;

	public AddClinicUI() {

		setMargin(10);
		setMembersMargin(10);

		ToolStrip toolStrip = new ToolStrip();

		IButton addButton = new IButton("Add");
		IButton editButton = new IButton("Edit");

		addButton.setActionType(SelectionType.RADIO);
		editButton.setActionType(SelectionType.RADIO);

		addButton.setRadioGroup("selectClinicOperation");
		editButton.setRadioGroup("selectClinicOperation");

		addButton.setShowRollOver(false);
		editButton.setShowRollOver(false);
		toolStrip.addMember(addButton);
		toolStrip.addMember(editButton);

		SessionData.getInstance().setClinicUIState("add");

		addMember(toolStrip);

		final ValuesManager formValuesManager = new ValuesManager();

		final TabSet clinicTabSet = new TabSet();
		clinicTabSet.setWidth100();
		clinicTabSet.setHeight("80%");

		clinicDataTab = new Tab("Clinic Data");
		clinicDataFormWidget = new ClinicDataForm("add");
		// clinicDataFormWidget.setValuesManager(formValuesManager);
		clinicDataTab.setPane(clinicDataFormWidget);

		Tab clinicConnectionTab = new Tab("Clinic Connection");
		clinicConnectionFormWidget = new ClinicConnectionDetailsForm();
		clinicConnectionFormWidget.setID("fmui_conndetailsform");
		// clinicConnectionFormWidget.setValuesManager(formValuesManager);
		clinicConnectionTab.setPane(clinicConnectionFormWidget);

		Tab clinicManagersTab = new Tab("Clinic Managers Information");
		clinicManagersWidget = new ClinicManagersDataForm();
		// clinicManagersWidget.setValuesManager(formValuesManager);
		clinicManagersTab.setPane(clinicManagersWidget);

		// Commented Adding Requirements Tab

		// Tab clinicRequirementsTab = new
		// Tab("Clinic Requirements Information");

		// ClinicRequirementsForm clinicRequirementsWidget = new
		// ClinicRequirementsForm();
		// clinicRequirementsWidget.setValuesManager(formValuesManager);
		// clinicRequirementsTab.setPane(clinicRequirementsWidget);

		clinicTabSet.setTabs(clinicDataTab, clinicConnectionTab,
				clinicManagersTab);
		// clinicTabSet.setTabs(clinicDataTab, clinicConnectionTab,
		// clinicManagersTab, clinicRequirementsTab);

		addMember(clinicTabSet);

		submitButton = new IButton("Submit");
		submitButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (SessionData.getInstance().getClinicUIState()
						.equalsIgnoreCase("add")) {
					Log.debug("Adding clinic details");
					addData();
				} else if (SessionData.getInstance().getClinicUIState()
						.equalsIgnoreCase("edit")) {
					Log.debug("Updating clinic details");
					updateData();
				} else {
					Log.debug("WTF!");
				}

			}
		});
		addMember(submitButton);
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SessionData.getInstance().setClinicUIState("add");
				clinicDataFormWidget.clearValues();
				clinicConnectionFormWidget.clearValues();
				clinicManagersWidget.clearValues();
				clinicTabSet.removeTab(0);
				clinicDataFormWidget = new ClinicDataForm("add");
				clinicDataTab = new Tab("Clinic Data");
				clinicDataTab.setPane(clinicDataFormWidget);
				clinicTabSet.addTab(clinicDataTab, 0);
				clinicTabSet.selectTab(0);
			}
		});

		editButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SessionData.getInstance().setClinicUIState("edit");
				clinicDataFormWidget.clearValues();
				clinicConnectionFormWidget.clearValues();
				clinicManagersWidget.clearValues();

				clinicTabSet.removeTab(0);
				clinicDataFormWidget = new ClinicDataForm("edit");
				clinicDataTab = new Tab("Clinic Data");
				clinicDataTab.setPane(clinicDataFormWidget);
				clinicTabSet.addTab(clinicDataTab, 0);
				clinicTabSet.selectTab(0);
			}

		});
	}

	protected void addData() {
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

		ClinicConnectionDetailsData connDetailsData = new ClinicConnectionDetailsData(
				((TextItem) clinicConnectionFormWidget
						.getItem("clinicConnectionType")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget.getItem("userName"))
						.getValueAsString(),
				((TextItem) clinicConnectionFormWidget.getItem("password"))
						.getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("systemLoginUserName")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("systemLoginPassword")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("typeOfSoftware")).getValueAsString(),
				((SelectItem) clinicConnectionFormWidget
						.getItem("mailOrFtpforVoiceFiles")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget.getItem("recorderUsed"))
						.getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("typeOfVoiceFiles")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("voiceFilesPath")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("transcriptsPath")).getValueAsString(),
				DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.S z").format(
						((DateItem) clinicConnectionFormWidget
								.getItem("folderDate")).getValueAsDate()));

		ClinicMetadataData metadata = new ClinicMetadataData(
				((TextItem) clinicManagersWidget
						.getItem("transcriptionsInchargeName"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("transcriptionsInchargeEmail"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("transcriptionInchargePhnNo"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("trancriptionInchargeFaxNo"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("transcriptionInchargeAddress"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("technicalPersonName"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("technicalPersonEmail")).getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("technicalPersonPhnNo")).getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("technicalPersonFaxNo")).getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("technicalPersonAddress")).getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("softwarePersonName"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("softwarePersonEmail"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("softwarePersonPhnNo"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("softwarePersonCellNo")).getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("softwarePersonAddress")).getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("emailForPatientList"))
						.getValueAsString(), ((TextItem) clinicManagersWidget
						.getItem("emailForInvoices")).getValueAsString());

		ClinicData clinicData = new ClinicData(
				((TextItem) clinicDataFormWidget.getItem("clinicAbbr"))
						.getValueAsString().toUpperCase(),
				((TextItem) clinicDataFormWidget.getItem("clinicName"))
						.getValueAsString(),
				((TextAreaItem) clinicDataFormWidget.getItem("addressLine1"))
						.getValueAsString(),
				((TextAreaItem) clinicDataFormWidget.getItem("addressLine2"))
						.getValueAsString(),
				((TextItem) clinicDataFormWidget.getItem("location"))
						.getValueAsString(),
				((TextItem) clinicDataFormWidget.getItem("country"))
						.getValueAsString(),
				((TextItem) clinicDataFormWidget.getItem("zipcode"))
						.getValueAsString(), connDetailsData, metadata);

		clinicDataFormWidget.clinicDataDs.addData(clinicData, new DSCallback() {

			@Override
			public void execute(DSResponse response, Object rawData,
					DSRequest request) {
				if (response.getStatus() >= 0) {
					SC.say("Success");
				} else {
					SC.say("Error storing Clinic Data!");
				}
			}
		});
		// }
	}

	protected void updateData() {
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
		ClinicConnectionDetailsData connDetailsData = new ClinicConnectionDetailsData(
				((TextItem) clinicConnectionFormWidget
						.getItem("clinicConnectionType")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget.getItem("userName"))
						.getValueAsString(),
				((TextItem) clinicConnectionFormWidget.getItem("password"))
						.getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("systemLoginUserName")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("systemLoginPassword")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("typeOfSoftware")).getValueAsString(),
				((SelectItem) clinicConnectionFormWidget
						.getItem("mailOrFtpforVoiceFiles")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget.getItem("recorderUsed"))
						.getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("typeOfVoiceFiles")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("voiceFilesPath")).getValueAsString(),
				((TextItem) clinicConnectionFormWidget
						.getItem("transcriptsPath")).getValueAsString(),
				DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.S z").format(
						((DateItem) clinicConnectionFormWidget
								.getItem("folderDate")).getValueAsDate()));

		connDetailsData
				.setClinicConnectionId((String) ((HiddenItem) clinicConnectionFormWidget
						.getItem("clinicConnectionId")).getValue());

		ClinicMetadataData metadata = new ClinicMetadataData(
				((TextItem) clinicManagersWidget
						.getItem("transcriptionsInchargeName"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("transcriptionsInchargeEmail"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("transcriptionInchargePhnNo"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("trancriptionInchargeFaxNo"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("transcriptionInchargeAddress"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("technicalPersonName"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("technicalPersonEmail")).getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("technicalPersonPhnNo")).getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("technicalPersonFaxNo")).getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("technicalPersonAddress")).getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("softwarePersonName"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("softwarePersonEmail"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("softwarePersonPhnNo"))
						.getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("softwarePersonCellNo")).getValueAsString(),
				((TextItem) clinicManagersWidget
						.getItem("softwarePersonAddress")).getValueAsString(),
				((TextItem) clinicManagersWidget.getItem("emailForPatientList"))
						.getValueAsString(), ((TextItem) clinicManagersWidget
						.getItem("emailForInvoices")).getValueAsString());

		metadata.setMetaDataId((String) ((HiddenItem) clinicManagersWidget
				.getItem("metaDataId")).getValue());

		ClinicData clinicData = new ClinicData(
				((SelectItem) clinicDataFormWidget.getItem("clinicAbbr"))
						.getSelectedRecord().getAttributeAsString("clinicAbbr")
						.toUpperCase(),
				((TextItem) clinicDataFormWidget.getItem("clinicName"))
						.getValueAsString(),
				((TextAreaItem) clinicDataFormWidget.getItem("addressLine1"))
						.getValueAsString(),
				((TextAreaItem) clinicDataFormWidget.getItem("addressLine2"))
						.getValueAsString(),
				((TextItem) clinicDataFormWidget.getItem("location"))
						.getValueAsString(),
				((TextItem) clinicDataFormWidget.getItem("country"))
						.getValueAsString(),
				((TextItem) clinicDataFormWidget.getItem("zipcode"))
						.getValueAsString(), connDetailsData, metadata);

		clinicData.setClinicId((String) ((HiddenItem) clinicDataFormWidget
				.getItem("clinicId")).getValue());

		clinicDataFormWidget.clinicDataDs.updateData(clinicData,
				new DSCallback() {

					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						if (response.getStatus() >= 0) {
							SC.say("Success");
						} else {
							SC.say("Error storing Clinic Data!");
						}
					}
				});
		// }
	}

	/**
	 * @return the clinicConnectionFormWidget
	 */
	public ClinicConnectionDetailsForm getClinicConnectionFormWidget() {
		return clinicConnectionFormWidget;
	}

	/**
	 * @return the clinicManagersWidget
	 */
	public ClinicManagersDataForm getClinicManagersWidget() {
		return clinicManagersWidget;
	}

	/**
	 * @return the clinicDataFormWidget
	 */
	public ClinicDataForm getClinicDataFormWidget() {
		return clinicDataFormWidget;
	}

	/**
	 * @return the clinicDataTab
	 */
	public Tab getClinicDataTab() {
		return clinicDataTab;
	}

}
