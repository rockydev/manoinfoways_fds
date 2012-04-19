package com.project.fms.admin.widgets;

import java.util.Date;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.project.fms.admin.widgets.data.ClinicConnectionDetailsData;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

public class ClinicDataForm extends DynamicForm {

	RestDataSource clinicDataDs;
	private String currentFormType;
	SelectItem clinicAbbrItems;

	public String getCurrentFormType() {
		return currentFormType;
	}

	public void setCurrentFormType(String currentFormType) {
		this.currentFormType = currentFormType;
	}

	public ClinicDataForm(String formType) {

		// Binding restdatasource to the form
		this.setCurrentFormType(formType);
		clinicDataDs = new RestDataSource();

		OperationBinding add = new OperationBinding();
		add.setOperationType(DSOperationType.ADD);
		add.setDataProtocol(DSProtocol.POSTXML);
		add.setDataFormat(DSDataFormat.XML);

		OperationBinding fetch = new OperationBinding();
		fetch.setOperationType(DSOperationType.FETCH);
		fetch.setDataProtocol(DSProtocol.GETPARAMS);
		fetch.setDataFormat(DSDataFormat.XML);
		
		OperationBinding update = new OperationBinding();
		update.setOperationType(DSOperationType.UPDATE);
		update.setDataProtocol(DSProtocol.POSTXML);
		update.setDataFormat(DSDataFormat.XML);
		
		DSRequest putRequest = new DSRequest();
		putRequest.setHttpMethod("PUT");
		update.setRequestProperties(putRequest);
		
		clinicDataDs.setOperationBindings(add, fetch, update);
		clinicDataDs.setSendMetaData(false);

		clinicDataDs.setDataURL("/fds/clinics");

		setDataSource(clinicDataDs);

		setNumCols(4);

		setMargin(25);

		MyTextItem clinicAbbr;

		HiddenItem clinicId = new HiddenItem("clinicId");
		MyTextItem clinicName = new MyTextItem("clinicName", "Clinic Name");
		MyTextAreaItem addressLine1 = new MyTextAreaItem("addressLine1",
				"Address 1");
		MyTextAreaItem addressLine2 = new MyTextAreaItem("addressLine2",
				"Address 2");
		MyTextItem country = new MyTextItem("country", "Country");
		MyTextItem location = new MyTextItem("location", "Location");
		MyTextItem zipcode = new MyTextItem("zipcode", "ZipCode");

		if (formType.equalsIgnoreCase("add")) {
			clinicAbbr = new MyTextItem("clinicAbbr", "Clinic ID");
			setFields(clinicAbbr, clinicName, addressLine1, addressLine2,
					country, location, zipcode);
		} else {
			RestDataSource clinicAbbrDs = new RestDataSource();

			OperationBinding clinicFetch = new OperationBinding();
			clinicFetch.setOperationType(DSOperationType.FETCH);
			clinicFetch.setDataProtocol(DSProtocol.GETPARAMS);
			clinicFetch.setDataFormat(DSDataFormat.XML);

			clinicAbbrDs.setOperationBindings(clinicFetch);
			clinicAbbrDs.setSendMetaData(false);
			clinicAbbrDs.setFetchDataURL("/fds/clinics/allabbrs");

			clinicAbbrItems = new SelectItem("clinicAbbr", "Clinic ID");
			clinicAbbrItems.setDisplayField("clinicAbbr");
			clinicAbbrItems.setOptionDataSource(clinicAbbrDs);
			clinicAbbrItems.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {

					Criteria clinicIdCriteria = new Criteria("clinicId",
							clinicAbbrItems.getSelectedRecord()
									.getAttributeAsString("clinicId"));

					// Fetching clinicData
					Criteria clinicDataCriteria = new Criteria("dataType", "0");
					clinicDataCriteria.addCriteria(clinicIdCriteria);
					clinicDataDs.fetchData(clinicDataCriteria,
							new DSCallback() {

								@Override
								public void execute(DSResponse response,
										Object rawData, DSRequest request) {

									try {
										Record data = response
												.getDataAsRecordList().get(0);

										for (String attribute : data
												.getAttributes()) {
											getItem(attribute)
													.setValue(
															data.getAttributeAsString(attribute));
										}

									} catch (Exception e) {
										Log.debug("Unable to fetch clinicDetails.");
									}

								}
							});

					// Fetching clinicConnectionDetails
					Criteria connDetailsCriteria = new Criteria("dataType", "1");
					connDetailsCriteria.addCriteria(clinicIdCriteria);
					clinicDataDs.fetchData(connDetailsCriteria,
							new DSCallback() {

								@Override
								public void execute(DSResponse response,
										Object rawData, DSRequest request) {

									Record data = response
											.getDataAsRecordList().first();
									for (String attribute : data
											.getAttributes()) {
										if (attribute
												.equalsIgnoreCase("folderdate")) {
											((DateItem) ((AddClinicUI) getParentElement()
													.getParentElement()
													.getParentElement())
													.getClinicConnectionFormWidget()
													.getItem(attribute))
													.setValue(DateTimeFormat
															.getFormat(
																	PredefinedFormat.DATE_SHORT)
															.parse(data
																	.getAttribute(attribute)));
											continue;
										}

										((AddClinicUI) getParentElement()
												.getParentElement()
												.getParentElement())
												.getClinicConnectionFormWidget()
												.getItem(attribute)
												.setValue(
														data.getAttributeAsString(attribute));
									}

								}
							});

					// Fetching clinicMetadata
					Criteria metadataCriteria = new Criteria("dataType", "2");
					metadataCriteria.addCriteria(clinicIdCriteria);
					clinicDataDs.fetchData(metadataCriteria, new DSCallback() {

						@Override
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {

							try {
								Record data = response.getDataAsRecordList()
										.first();
								for (String attribute : data.getAttributes()) {
									((AddClinicUI) getParentElement()
											.getParentElement()
											.getParentElement())
											.getClinicManagersWidget()
											.getItem(attribute)
											.setValue(
													data.getAttributeAsString(attribute));
								}

							} catch (Exception e) {
								Log.debug("Unable to fetch clinicMetadata.");
							}

						}
					});

				}
			});
			setFields(clinicId, clinicAbbrItems, clinicName, addressLine1,
					addressLine2, country, location, zipcode);
		}
	}

}
