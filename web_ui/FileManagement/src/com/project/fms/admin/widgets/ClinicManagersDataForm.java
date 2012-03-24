package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.form.DynamicForm;

public class ClinicManagersDataForm extends DynamicForm{
	
	public ClinicManagersDataForm(){
		setNumCols(4);
		setWidth100();
		setMargin(25);
		setCellPadding(10);
		MyTextItem transIncharge = new MyTextItem("transIncharge","Transcriptions Incharge Name");
		EmailTextItem transInchargeEmail = new EmailTextItem("transInchargeEmail","Transcriptions Incharge Email");
		MyTextItem transInchargePhone = new MyTextItem("transInchargePhone","Transcriptions Incharge Phone");
		MyTextItem transInchargeFax = new MyTextItem("transInchargeFax","Transcriptions Incharge Fax");
		MyTextItem transInchargeAddr = new MyTextItem("transInchargeAddr","Transcriptions Incharge Address");
		
		MyTextItem technicalPersonName = new MyTextItem("technicalPersonName","technicalPerson Name");
		EmailTextItem technicalPersonEmail = new EmailTextItem("technicalPersonEmail","technicalPerson Email");
		MyTextItem technicalPersonPhone = new MyTextItem("technicalPersonPhone","technicalPerson Phone");
		MyTextItem technicalPersonFax = new MyTextItem("technicalPersonFax","technicalPerson Fax");
		MyTextItem technicalPersonAddr = new MyTextItem("technicalPersonAddr","technicalPerson Address");
		
		
		MyTextItem softwarePersonName = new MyTextItem("softwarePersonName","Software Person Name");
		EmailTextItem softwarePersonEmail = new EmailTextItem("softwarePersonEmail","Software Person Email");
		MyTextItem softwarePersonPhone = new MyTextItem("softwarePersonPhone","Software Person Phone");
		MyTextItem softwarePersonMobile = new MyTextItem("softwarePersonFax","Software Person Mobile");
		MyTextItem softwarePersonAddr = new MyTextItem("softwarePersonAddr","SoftwarePerson Address");
		EmailTextItem patientListEmail = new EmailTextItem("patientListEmail","Email for Patient List");
		EmailTextItem invoicesEmail = new EmailTextItem("invoicesEmail","Email for Invoices");
		
		
		setFields(transIncharge, transInchargeEmail, transInchargePhone, transInchargeFax, transInchargeAddr, 
				technicalPersonName, technicalPersonEmail, technicalPersonPhone, technicalPersonFax, technicalPersonAddr,
				softwarePersonName, softwarePersonEmail, softwarePersonPhone, softwarePersonMobile, softwarePersonAddr, patientListEmail, invoicesEmail);
		
	}

}
