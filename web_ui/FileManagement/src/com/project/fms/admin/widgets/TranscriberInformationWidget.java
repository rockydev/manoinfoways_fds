package com.project.fms.admin.widgets;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.project.fms.admin.widgets.data.TranscriberData;
import com.project.fms.admin.widgets.data.TranscriberTypeData;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceDateTimeField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TranscriberInformationWidget extends Canvas {

	ListGrid transcriberGrid;
	RestDataSource transcriberDataDs;

	public TranscriberInformationWidget() {
		transcriberDataDs = new RestDataSource();

		OperationBinding fetch = new OperationBinding();
		fetch.setOperationType(DSOperationType.FETCH);
		fetch.setDataProtocol(DSProtocol.GETPARAMS);
		fetch.setDataFormat(DSDataFormat.XML);

		OperationBinding add = new OperationBinding();
		add.setOperationType(DSOperationType.ADD);
		add.setDataProtocol(DSProtocol.POSTXML);
		add.setDataFormat(DSDataFormat.XML);

		OperationBinding update = new OperationBinding();
		update.setOperationType(DSOperationType.UPDATE);
		update.setDataProtocol(DSProtocol.POSTXML);
		update.setDataFormat(DSDataFormat.XML);

		DSRequest putRequest = new DSRequest();
		putRequest.setHttpMethod("PUT");
		update.setRequestProperties(putRequest);

		OperationBinding delete = new OperationBinding();
		delete.setOperationType(DSOperationType.REMOVE);
		delete.setDataProtocol(DSProtocol.POSTMESSAGE);

		// DSRequest deleteRequest = new DSRequest();
		// deleteRequest.setHttpMethod("DELETE");
		// delete.setRequestProperties(deleteRequest);

		transcriberDataDs.setOperationBindings(fetch, add, update, delete);
		transcriberDataDs.setSendMetaData(false);

		transcriberDataDs.setDataURL("/fds/transcribers");

//		DataSourceTextField transcriberId = new DataSourceTextField(
//				"transcriberId", "Transcriber Id");
//		transcriberId.setPrimaryKey(true);
//		transcriberId.setCanEdit(false);
//
//		DataSourceTextField userName = new DataSourceTextField("userName",
//				"Username");
//		DataSourceTextField password = new DataSourceTextField("password",
//				"Password");
//		DataSourceTextField type = new DataSourceTextField("transcriberTypeId", "TYPE");
//		
//		HashMap<String, String> typeMap = new HashMap<String, String>();
//		typeMap.put("1","Transcriber");
//		typeMap.put("2","Editor");
//		typeMap.put("3","Editor/Transcriber");
//		typeMap.put("4","Proofer");
//		typeMap.put("4","Proofer/Transcriber");
//		typeMap.put("6","Proofer/Editor");
//		typeMap.put("7","Proofer/Editor/Transcriber");
//		typeMap.put("8","QA");
//		typeMap.put("9","QA/Transcriber");
//		typeMap.put("10","QA/Editor");
//		typeMap.put("11","QA/Editor/Transcriber");
//		typeMap.put("12","QA/Proofer");
//		typeMap.put("13","QA/Proofer/Transcriber");
//		typeMap.put("14","QA/Proofer/Editor");
//		typeMap.put("15","QA/Proofer/Editor/Transcriber");
//		
//		type.setValueMap(typeMap);
//		
//		DataSourceTextField transcriberNameField = new DataSourceTextField("transcriberName", "Name");
//				
//		DataSourceDateField dateofjoiningField = new DataSourceDateField(
//				"dateofJoining", "DOJ");
//		
//		DataSourceTextField addressField = new DataSourceTextField("transcriberAddress",
//				"Address", 100);
//		DataSourceTextField permanentAddressField = new DataSourceTextField(
//				"transcriberPermanentAddress", "Permanent Address", 100);
//		DataSourceTextField phnumField = new DataSourceTextField("transcriberPhnNuber",
//				"Ph No");
//		DataSourceTextField mobileField = new DataSourceTextField("transcriberMobile",
//				"Mobile");
//		DataSourceTextField emailField = new DataSourceTextField("transcriberEmail",
//				"Email");
//
//		// emailField.setRequired(true);
//
//		DataSourceTextField qualificationField = new DataSourceTextField(
//				"transcriberQualification", "Qualification");
//		DataSourceTextField experienceField = new DataSourceTextField(
//				"transcriberExperience", "Experience");
//		DataSourceTextField dobField = new DataSourceTextField("transcriberDob", "DOB");
//		DataSourceDateTimeField lastLoginField = new DataSourceDateTimeField(
//				"transcriberLoginTime", "Last Login");
//
//		transcriberDataDs.setFields(transcriberId, userName, password, type, transcriberNameField,
//				dateofjoiningField, addressField, permanentAddressField,
//				phnumField, mobileField, emailField, qualificationField,
//				experienceField, dobField, lastLoginField);

		transcriberGrid = new ListGrid();

		transcriberGrid.setWidth100();
		transcriberGrid.setHeight100();

		transcriberGrid.setShowFilterEditor(true);
		transcriberGrid.setFilterOnKeypress(true);
		transcriberGrid.setAutoFetchData(true);
		transcriberGrid.setCanEdit(true);
		// transcriberGrid.setModalEditing(true);
		transcriberGrid.setEditEvent(ListGridEditEvent.CLICK);
		transcriberGrid.setListEndEditAction(RowEndEditAction.NEXT);
		transcriberGrid.setAutoSaveEdits(false);
		
		ListGridField transcriberId = new ListGridField(
				"transcriberId", "Transcriber Id");
//		transcriberId.setPrimaryKey(true);
		transcriberId.setCanEdit(false);

		ListGridField userName = new ListGridField("userName",
				"Username");
		ListGridField password = new ListGridField("password",
				"Password");
		ListGridField type = new ListGridField("transcriberTypeId", "TYPE");
		
		HashMap<String, String> typeMap = new HashMap<String, String>();
		typeMap.put("1","Transcriber");
		typeMap.put("2","Editor");
		typeMap.put("3","Editor/Transcriber");
		typeMap.put("4","Proofer");
		typeMap.put("5","Proofer/Transcriber");
		typeMap.put("6","Proofer/Editor");
		typeMap.put("7","Proofer/Editor/Transcriber");
		typeMap.put("8","QA");
		typeMap.put("9","QA/Transcriber");
		typeMap.put("10","QA/Editor");
		typeMap.put("11","QA/Editor/Transcriber");
		typeMap.put("12","QA/Proofer");
		typeMap.put("13","QA/Proofer/Transcriber");
		typeMap.put("14","QA/Proofer/Editor");
		typeMap.put("15","QA/Proofer/Editor/Transcriber");
		
		type.setValueMap(typeMap);
		
		ListGridField transcriberNameField = new ListGridField("transcriberName", "Name");
				
		ListGridField dateofjoiningField = new ListGridField(
				"dateofJoining", "DOJ");
		dateofjoiningField.setType(ListGridFieldType.DATE);
		
		ListGridField addressField = new ListGridField("transcriberAddress",
				"Address", 100);
		ListGridField permanentAddressField = new ListGridField(
				"transcriberPermanentAddress", "Permanent Address", 100);
		ListGridField phnumField = new ListGridField("transcriberPhoneNumber",
				"Ph No");
		ListGridField mobileField = new ListGridField("transcriberMobile",
				"Mobile");
		ListGridField emailField = new ListGridField("transcriberEmail",
				"Email");

		// emailField.setRequired(true);

		ListGridField qualificationField = new ListGridField(
				"transcriberQualification", "Qualification");
		ListGridField experienceField = new ListGridField(
				"transcriberExperience", "Experience");
		ListGridField dobField = new ListGridField("transcriberDob", "DOB");
		dobField.setType(ListGridFieldType.DATE);
		
		ListGridField lastLoginField = new ListGridField(
				"transcriberLoginTime", "Last Login");
//		lastLoginField.setType(ListGridFieldType.TIME);

		transcriberGrid.setFields(transcriberId, userName, password, type, transcriberNameField,
				dateofjoiningField, addressField, permanentAddressField,
				phnumField, mobileField, emailField, qualificationField,
				experienceField, dobField, lastLoginField);
		
		final DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");  
		CellFormatter dateTimeFormatter = new CellFormatter() {  
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                if (value != null) {  
  
                    try {  
                        Date dateValue = (Date) value;  
                        return dateTimeFormat.format(dateValue);  
                    } catch (Exception e) {  
                        return value.toString();  
                    }  
                } else {  
                    return "";  
                }  
            }  
        };
		dateofjoiningField.setCellFormatter(dateTimeFormatter);
		dobField.setCellFormatter(dateTimeFormatter);
		
		final DateTimeFormat timeFormat = DateTimeFormat.getFormat("HH:mm");  
		CellFormatter timeFormatter = new CellFormatter() {  
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
                if (value != null) {  
  
                    try {  
                        Date dateValue = (Date) value;  
                        return timeFormat.format(dateValue);  
                    } catch (Exception e) {  
                        return value.toString();  
                    }  
                } else {  
                    return "";  
                }  
            }  
        };
        lastLoginField.setCellFormatter(timeFormatter);
		
		transcriberGrid.setDataSource(transcriberDataDs,transcriberId, userName, password, type, transcriberNameField,
				dateofjoiningField, addressField, permanentAddressField,
				phnumField, mobileField, emailField, qualificationField,
				experienceField, dobField, lastLoginField);
		transcriberGrid.setCanRemoveRecords(true);

		Label titleLabel = new Label(
				"Welcome to Transcribers Information Screen");
		titleLabel.setWidth100();
		titleLabel.setBorder("1px solid #808080");
		titleLabel.setBackgroundColor("#C3D9FF");
		titleLabel.setAlign(Alignment.CENTER);
		titleLabel.setHeight(50);

		VLayout layout = new VLayout(10 );
		layout.setWidth100();
		layout.setHeight100();
		layout.addMember(titleLabel);
		layout.addMember(transcriberGrid);

		HLayout buttonLayout = new HLayout();

		IButton addButton = new IButton("Add New");
		addButton.setTop(250);
		addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				transcriberGrid.startEditingNew();
//				transcriberGrid.startEditingNew(new TranscriberData("1", "", "", "", "", "", "", "", "", "", "", "", "", ""));
			}
		});

		addButton.setWidth(120);
		addButton.setAlign(Alignment.CENTER);

		IButton submitButton = new IButton("Submit");
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (transcriberGrid.hasChanges()) {
					transcriberGrid.saveAllEdits();
				}
			}
		});

		submitButton.setWidth(120);
		submitButton.setAlign(Alignment.CENTER);

		IButton discardButton = new IButton("Discard");
		discardButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				transcriberGrid.discardAllEdits();
			}

		});
		discardButton.setWidth(120);
		discardButton.setAlign(Alignment.CENTER);

		buttonLayout.addMember(addButton);
		buttonLayout.addMember(submitButton);
		buttonLayout.addMember(discardButton);
		buttonLayout.setMembersMargin(10);
		layout.addMember(buttonLayout);

		addChild(layout);
	}
}