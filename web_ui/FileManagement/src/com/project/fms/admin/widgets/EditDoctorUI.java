package com.project.fms.admin.widgets;

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.ExportFormat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class EditDoctorUI extends DynamicForm {

	public EditDoctorUI() {
		setMargin(50);
		setNumCols(4);

		final Map<String, String[]> clinicsMap = new HashMap<String, String[]>();
		clinicsMap.put("ABC", new String[] { "Doctor 1", "Doctor 9" });
		clinicsMap.put("Clinic 2", new String[] { "Doctor 2", "Doctor 8" });
		clinicsMap.put("Clinic 3", new String[] { "Doctor 3", "Doctor 5",
				"Doctor 6" });
		clinicsMap.put("Clinic 4", new String[] { "Doctor 4", "Doctor 7" });

		RestDataSource clinicDataDs = new RestDataSource();

		OperationBinding fetch = new OperationBinding();
		fetch.setOperationType(DSOperationType.FETCH);
		fetch.setDataProtocol(DSProtocol.GETPARAMS);
		fetch.setDataFormat(DSDataFormat.XML);

		clinicDataDs.setOperationBindings(fetch);
		clinicDataDs.setSendMetaData(false);
		clinicDataDs.setFetchDataURL("/fds/clinics/allabbrs");

		SelectItem clinicSelectedItem = new SelectItem();
		clinicSelectedItem.setName("clinic");
		clinicSelectedItem.setTitle("Clinic");
		clinicSelectedItem.setDisplayField("clinicAbbr");

		// clinicSelectedItem.setValueMap("Clinic 1", "Clinic 2", "Clinic 3",
		// "Clinic 4");
		clinicSelectedItem.setOptionDataSource(clinicDataDs);

		clinicSelectedItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String selectedItem = (String) event.getValue();

				getField("doctor").setValueMap(clinicsMap.get(selectedItem));
				getField("docName").setValue("");
				getField("docEmail").setValue("");
				getField("docPhno").setValue("");
				getField("docClinic").setValue("");
				getField("docAddr1").setValue("");
				getField("docAddr2").setValue("");
				getField("docLocation").setValue("");
				getField("docCountry").setValue("");
				getField("docPincode").setValue("");
			}
		});

//		clinicSelectedItem.addBlurHandler(new BlurHandler() {
//
//			@Override
//			public void onBlur(BlurEvent event) {
//				// TODO Auto-generated method stub
//				SC.say("Selected Value: "
//						+ ((SelectItem) event.getItem()).getSelectedRecord()
//								.getAttribute("clinicAbbr")
//						+ "\n"
//						+ "Selected clinicId: "
//						+ ((SelectItem) event.getItem()).getSelectedRecord()
//								.getAttribute("clinicId"));
//			}
//		});

		SelectItem doctorSelecteditem = new SelectItem();
		doctorSelecteditem.setName("doctor");
		doctorSelecteditem.setTitle("Doctor");
		doctorSelecteditem.setDefaultValue("Select Clinic");
		doctorSelecteditem.setAddUnknownValues(false);

		TextItem docName = new TextItem("docName", "Name: ");
		TextItem docEmail = new TextItem("docEmail", "Email ID: ");
		TextItem docPhno = new TextItem("docPhno", "Phone Number: ");
		TextItem docClinic = new TextItem("docClinic", "Clinic: ");
		TextAreaItem docAddr1 = new TextAreaItem("docAddr1", "Address1: ");
		TextAreaItem docAddr2 = new TextAreaItem("docAddr2", "Address2: ");
		TextItem docLocation = new TextItem("docLocation", "Location: ");
		TextItem docCountry = new TextItem("docCountry", "Country: ");
		TextItem docPincode = new TextItem("docPincode", "Pincode: ");

		doctorSelecteditem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				String selectedItem = (String) event.getValue();
				getField("docName").setValue(
						selectedItem + " - Doctor's Old Name");
				getField("docEmail").setValue(
						selectedItem + " - Doctor's Old Email");
				getField("docPhno").setValue(
						selectedItem + " - Doctor's Old Phone NO");
				getField("docClinic").setValue(
						selectedItem + " - Doctor's Old Clinic");
				getField("docAddr1").setValue(
						selectedItem + " - Doctor's Old Address1");
				getField("docAddr2").setValue(
						selectedItem + " - Doctor's Old Address2");
				getField("docLocation").setValue(
						selectedItem + " - Doctor's Old Location");
				getField("docCountry").setValue(
						selectedItem + " - Doctor's Old Country");
				getField("docPincode").setValue(
						selectedItem + " - Doctor's Old Pincode");
			}
		});

		docName.setRequired(true);
		docEmail.setRequired(true);
		docPhno.setRequired(true);
		docClinic.setRequired(true);
		docAddr1.setRequired(true);
		docAddr2.setRequired(true);
		docLocation.setRequired(true);
		docCountry.setRequired(true);
		docPincode.setRequired(true);

		ButtonItem submitButton = new ButtonItem();
		submitButton.setTitle("Submit");
		submitButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (validate() == true)
					submit();
			}
		});

		setItems(clinicSelectedItem, doctorSelecteditem, docName, docEmail,
				docPhno, docClinic, docAddr1, docAddr2, docLocation,
				docCountry, docPincode, submitButton);
	}
}
