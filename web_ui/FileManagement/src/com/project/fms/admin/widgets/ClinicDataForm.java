package com.project.fms.admin.widgets;


import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.ExportFormat;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class ClinicDataForm extends DynamicForm{
	
	RestDataSource clinicDataDs;
	String clinicId;
	
	public ClinicDataForm(){
		
		//Binding restdatasource to the form
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
		
		
		MyTextItem clinicAbbr = new MyTextItem("clinicAbbr", "Clinic ID");
		MyTextItem clinicName = new MyTextItem("clinicName", "Clinic Name");
		MyTextAreaItem addressLine1 = new MyTextAreaItem("addressLine1", "Address 1");
		MyTextAreaItem addressLine2 = new MyTextAreaItem("addressLine2", "Address 2");
		MyTextItem country = new MyTextItem("country", "Country");
		MyTextItem location = new MyTextItem("location", "Location");
		MyTextItem zipcode = new MyTextItem("zipcode", "ZipCode");
		
		setFields(clinicAbbr, clinicName, addressLine1, addressLine2, country, location, zipcode);
	}
}
