package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class ClinicDataForm extends DynamicForm{
	
	public ClinicDataForm(){
		
		setNumCols(4);
		
		setMargin(50);
		TextItem clinicId = new TextItem("clinicId", "Clinic ID");
		TextItem clinicName = new TextItem("clinicName", "Clinic Name");
		TextAreaItem clinicAddr1 = new TextAreaItem("clinicAddr1", "Address 1");
		TextAreaItem clinicAddr2 = new TextAreaItem("clinicAddr2", "Address 2");
		TextItem clinicCountry = new TextItem("clinicCountry", "Country");
		TextItem clinicLocation = new TextItem("clinicLocation", "Location");
		IntegerItem clinicZipCode = new IntegerItem("clinicZipcode", "ZipCode");
		clinicId.setRequired(true);
		clinicName.setRequired(true);
		clinicAddr1.setRequired(true);
		clinicAddr2.setRequired(true);
		clinicCountry.setRequired(true);
		clinicLocation.setRequired(true);
		clinicZipCode.setRequired(true);
		setFields(clinicId, clinicName, clinicAddr1, clinicAddr2, clinicCountry, clinicLocation, clinicZipCode);
	}

}
