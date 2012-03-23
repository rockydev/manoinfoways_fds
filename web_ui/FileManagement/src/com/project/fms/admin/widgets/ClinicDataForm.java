package com.project.fms.admin.widgets;


import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.Record;
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
        
//        <clinicId>1</clinicId>
//        <clinicAbbr>ABC</clinicAbbr>
//        <clinicName>clinic1</clinicName>
//        <addressLine1>qweqwr</addressLine1>
//        <addressLine2>wqeqrw</addressLine2>
//        <country>India</country>
//        <location>Hyderabad</location>
//        <zipcode>500089</zipcode>
////        
//        TextItem abbrField = new TextItem("clinicAbbr", "Clinic Id");
//        TextItem nameField = new TextItem("clinicName", "Clinic Name");
//        TextItem addr1Field = new TextItem("addressLine1", "Address Line 1");
//        TextItem addr2Field = new TextItem("addressLine2", "Address Line 2");
//        TextItem locationField = new TextItem("location", "Location");
//        TextItem countryField = new TextItem("country", "Country", 45, true);
//        TextItem zipCodeField = new TextItem("zipcode", "Zipcode", 45, true);
        
//        clinicDataDs.setFields(abbrField,nameField,addr1Field,addr2Field,locationField,countryField,zipCodeField);
        clinicDataDs.setAddDataURL("/fds/clinics");
        
		setNumCols(4);
		
		setMargin(50);
		
		setDataSource(clinicDataDs);
		
//		IButton submitButton = new IButton("Submit");
//		submitButton.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				
////					clinicDataDs.addData(new ClinicData(getItem("clinicAbbr").getValue().toString(),
////							getItem("clinicName").getValue().toString(), 
////							getItem("addressLine1").getValue().toString(), 
////							getItem("addressLine2").getValue().toString(), 
////							getItem("location").getValue().toString(), 
////							getItem("country").getValue().toString(), 
////							getItem("zipcode").getValue().toString()));
//					
////					clinicDataDs.addData(new ClinicData(data[0],data[1],data[2],data[3],data[4],data[5],data[0]));
//				
//				
//				
//				submit();
//			}
//		});
//		
//		addChild(submitButton);
		
		TextItem clinicAbbr = new TextItem("clinicAbbr", "Clinic ID");
		TextItem clinicName = new TextItem("clinicName", "Clinic Name");
		TextAreaItem addressLine1 = new TextAreaItem("addressLine1", "Address 1");
		TextAreaItem addressLine2 = new TextAreaItem("addressLine2", "Address 2");
		TextItem country = new TextItem("country", "Country");
		TextItem location = new TextItem("location", "Location");
		TextItem zipcode = new TextItem("zipcode", "ZipCode");
		
//		clinicId.setRequired(true);
//		clinicName.setRequired(true);
//		clinicAddr1.setRequired(true);
//		clinicAddr2.setRequired(true);
//		clinicCountry.setRequired(true);
//		clinicLocation.setRequired(true);
//		clinicZipCode.setRequired(true);
		setFields(clinicAbbr, clinicName, addressLine1, addressLine2, country, location, zipcode);
	}
	
}
