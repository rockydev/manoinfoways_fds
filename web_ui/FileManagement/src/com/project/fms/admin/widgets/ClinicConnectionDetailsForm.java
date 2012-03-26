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
import com.smartgwt.client.widgets.form.fields.DateItem;

public class ClinicConnectionDetailsForm extends DynamicForm{
	
	RestDataSource connDetailsDs;
	
	public ClinicConnectionDetailsForm(){
		
		//Binding restdatasource to the form
		connDetailsDs = new RestDataSource(){  
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
		
        connDetailsDs.setOperationBindings(add);
        connDetailsDs.setSendMetaData(false);
        
		setNumCols(4);
		setWidth100();
		setMargin(25);
		setCellPadding(10);
		
//		setDataSource(connDetailsDs);
		
		MyTextItem clinicConnectionType = new MyTextItem("clinicConnectionType", "Clinic Connection Type");
		MyTextItem userName = new MyTextItem("userName", "Username");
		MyTextItem password = new MyTextItem("password", "Password");
		MyTextItem sysUserName = new MyTextItem("systemLoginUserName", "System Username");
		MyTextItem sysPassword = new MyTextItem("systemLoginPassword", "System Password");
		MyTextItem sysType = new MyTextItem("typeOfSoftware", "Type of Software");
		MyTextItem mailFtpVoiceFilesType = new MyTextItem("mailOrFtpforVoiceFiles", "Mail or FTP for Voice Files");
		MyTextItem recorder = new MyTextItem("recorderUsed", "Recorder Used");
		MyTextItem voiceFilesType = new MyTextItem("typeOfVoiceFiles", "Type of Voice Files");
		MyTextItem voiceFilesPath = new MyTextItem("voiceFilesPath", "Voice Files Path");
		MyTextItem transcriptsPath = new MyTextItem("transcriptsPath", "Transcripts Path");
		DateItem folderDate = new DateItem();  
		folderDate.setName("folderDate");  
		folderDate.setTitle("Folder Date"); 
		folderDate.setUseTextField(true);
		
		folderDate.setRequired(true);
		setFields(clinicConnectionType, userName, password, sysUserName, sysPassword, sysType, mailFtpVoiceFilesType, recorder, 
				voiceFilesType, voiceFilesPath, transcriptsPath, folderDate);
		
		
	}

}

//<clinicConnectionId>4</clinicConnectionId>
//<clinicData>
//  <clinicId>1</clinicId>
//  <clinicAbbr>ABC</clinicAbbr>
//  <clinicName>clinic1</clinicName>
//  <addressLine1>qweqwr</addressLine1>
//  <addressLine2>wqeqrw</addressLine2>
//  <country>India</country>
//  <location>Hyderabad</location>
//  <zipcode>500089</zipcode>
//</clinicData>
//<clinicConnectionType>Test3</clinicConnectionType>
//<userName>modified</userName>
//<password>modified</password>
//<systemLoginUserName>fdkjsk</systemLoginUserName>
//<systemLoginPassword>fssdf</systemLoginPassword>
//<typeOfSoftware>sdfs</typeOfSoftware>
//<mailOrFtpforVoiceFiles>fs</mailOrFtpforVoiceFiles>
//<recorderUsed>sff</recorderUsed>
//<typeOfVoiceFiles>fs</typeOfVoiceFiles>
//<voiceFilesPath>/DEF/</voiceFilesPath>
//<transcriptsPath>/DEF/</transcriptsPath>
//<folderDate class="sql-timestamp">2012-10-09 00:00:00.0</folderDate>
//</record>