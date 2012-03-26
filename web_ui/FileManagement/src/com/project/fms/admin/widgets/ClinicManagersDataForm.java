package com.project.fms.admin.widgets;

import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.widgets.form.DynamicForm;

public class ClinicManagersDataForm extends DynamicForm {

	RestDataSource clinicMetadataDs;

	public ClinicManagersDataForm() {

		// Binding restdatasource to the form
		clinicMetadataDs = new RestDataSource();

		OperationBinding add = new OperationBinding();
		add.setOperationType(DSOperationType.ADD);
		add.setDataProtocol(DSProtocol.POSTXML);
		add.setDataFormat(DSDataFormat.XML);

		clinicMetadataDs.setOperationBindings(add);
		clinicMetadataDs.setSendMetaData(false);

		setNumCols(4);
		setWidth100();
		setMargin(25);
		setCellPadding(10);
		
		MyTextItem transIncharge = new MyTextItem("transcriptionsInchargeName",
				"Transcriptions Incharge Name");
		EmailTextItem transInchargeEmail = new EmailTextItem(
				"transcriptionsInchargeEmail", "Transcriptions Incharge Email");
		MyTextItem transInchargePhone = new MyTextItem("transcriptionInchargePhnNo",
				"Transcriptions Incharge Phone");
		MyTextItem transInchargeFax = new MyTextItem("trancriptionInchargeFaxNo",
				"Transcriptions Incharge Fax");
		MyTextItem transInchargeAddr = new MyTextItem("transcriptionInchargeAddress",
				"Transcriptions Incharge Address");

		MyTextItem technicalPersonName = new MyTextItem("technicalPersonName",
				"technicalPerson Name");
		EmailTextItem technicalPersonEmail = new EmailTextItem(
				"technicalPersonEmail", "technicalPerson Email");
		MyTextItem technicalPersonPhone = new MyTextItem(
				"technicalPersonPhnNo", "technicalPerson Phone");
		MyTextItem technicalPersonFax = new MyTextItem("technicalPersonFaxNo",
				"technicalPerson Fax");
		MyTextItem technicalPersonAddr = new MyTextItem("technicalPersonAddress",
				"technicalPerson Address");

		MyTextItem softwarePersonName = new MyTextItem("softwarePersonName",
				"Software Person Name");
		EmailTextItem softwarePersonEmail = new EmailTextItem(
				"softwarePersonEmail", "Software Person Email");
		MyTextItem softwarePersonPhone = new MyTextItem("softwarePersonPhnNo",
				"Software Person Phone");
		MyTextItem softwarePersonMobile = new MyTextItem("softwarePersonCellNo",
				"Software Person Mobile");
		MyTextItem softwarePersonAddr = new MyTextItem("softwarePersonAddress",
				"SoftwarePerson Address");
		EmailTextItem patientListEmail = new EmailTextItem("emailForPatientList",
				"Email for Patient List");
		EmailTextItem invoicesEmail = new EmailTextItem("emailForInvoices",
				"Email for Invoices");

		setFields(transIncharge, transInchargeEmail, transInchargePhone,
				transInchargeFax, transInchargeAddr, technicalPersonName,
				technicalPersonEmail, technicalPersonPhone, technicalPersonFax,
				technicalPersonAddr, softwarePersonName, softwarePersonEmail,
				softwarePersonPhone, softwarePersonMobile, softwarePersonAddr,
				patientListEmail, invoicesEmail);

	}

}
