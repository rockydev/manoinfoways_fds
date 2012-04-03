package com.project.fms.admin.widgets;


import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.ExportFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;

public class ClinicDataForm extends DynamicForm{
	
	RestDataSource clinicDataDs;
	private String currentFormType;
	
	public String getCurrentFormType() {
		return currentFormType;
	}

	public void setCurrentFormType(String currentFormType) {
		this.currentFormType = currentFormType;
	}

	public ClinicDataForm(String formType){
		
		//Binding restdatasource to the form
		this.setCurrentFormType(formType);
		clinicDataDs = new RestDataSource() {  
            @Override  
            protected Object transformRequest(DSRequest dsRequest) {  
                dsRequest.setExportAs(ExportFormat.XML);
                dsRequest.setExportValueFields(true);
            	return super.transformRequest(dsRequest);  
            }  
            @Override  
            protected void transformResponse(DSResponse response, DSRequest request, Object data) {  
                super.transformResponse(response, request, data);  
            }  
        }; 
		
		OperationBinding add = new OperationBinding();  
        add.setOperationType(DSOperationType.ADD);  
        add.setDataProtocol(DSProtocol.POSTXML);
        add.setDataFormat(DSDataFormat.XML);
		
        clinicDataDs.setOperationBindings(add);
        clinicDataDs.setSendMetaData(false);
        
        clinicDataDs.setAddDataURL("/fds/clinics");
        
		setNumCols(4);
		
		setMargin(25);
		
		setDataSource(clinicDataDs);
		
		MyTextItem clinicAbbr;
		SelectItem clinicAbbrItems;
		
//		clinicAbbr = new MyTextItem("clinicAbbr", "Clinic ID");
		MyTextItem clinicName = new MyTextItem("clinicName", "Clinic Name");
		MyTextAreaItem addressLine1 = new MyTextAreaItem("addressLine1", "Address 1");
		MyTextAreaItem addressLine2 = new MyTextAreaItem("addressLine2", "Address 2");
		MyTextItem country = new MyTextItem("country", "Country");
		MyTextItem location = new MyTextItem("location", "Location");
		MyTextItem zipcode = new MyTextItem("zipcode", "ZipCode");
		
		if(formType.equalsIgnoreCase("add")){
			clinicAbbr = new MyTextItem("clinicAbbr", "Clinic ID");
			setFields(clinicAbbr, clinicName, addressLine1, addressLine2, country, location, zipcode);
		}else{
			clinicAbbrItems = new SelectItem("clinicAbbr", "Clinic ID");
//			clinicAbbrItems.setValues("Clinic Abbr1, Clinic Abbr2, Clinic Abbr3, Clinic Abbr4, Clinic Abbr5");
			setFields(clinicAbbrItems, clinicName, addressLine1, addressLine2, country, location, zipcode);
		}
		
//		setFields(clinicAbbr, clinicName, addressLine1, addressLine2, country, location, zipcode);
	}
}
