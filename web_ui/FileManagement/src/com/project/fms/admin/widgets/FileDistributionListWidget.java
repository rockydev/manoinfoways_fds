package com.project.fms.admin.widgets;

import java.util.HashMap;

import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class FileDistributionListWidget extends Canvas {

	HashMap<String, String> transcriberMap;
	ListGrid fileDistributionGrid;
	ListGridField transcriberField;
	ListGridField editorField;
	ListGridField prooferField;
	ListGridField qaField;

	public FileDistributionListWidget() {

		ListGridField fileNameField = new ListGridField("fileName", "FileName");
		fileNameField.setCanEdit(false);

		transcriberField = new ListGridField("transcriberId", "Transcriber");

		editorField = new ListGridField("editorId", "Editor");

		prooferField = new ListGridField("prooferId", "Proofer");

		qaField = new ListGridField("QAId", "QA");
		
		fileDistributionGrid = new ListGrid();
		
		fileDistributionGrid.setCanEdit(true);
		fileDistributionGrid.setEditEvent(ListGridEditEvent.CLICK);
		fileDistributionGrid.setAutoSaveEdits(false);
		fileDistributionGrid.setAutoFetchData(false);
		fileDistributionGrid.setShowFilterEditor(true);
		fileDistributionGrid.setFields(fileNameField, transcriberField,
				editorField, prooferField, qaField);

		RestDataSource transcriberAbbrDs = new RestDataSource();

		OperationBinding transcriberFetch = new OperationBinding();
		transcriberFetch.setOperationType(DSOperationType.FETCH);
		transcriberFetch.setDataProtocol(DSProtocol.GETPARAMS);
		transcriberFetch.setDataFormat(DSDataFormat.XML);

		transcriberAbbrDs.setOperationBindings(transcriberFetch);
		transcriberAbbrDs.setSendMetaData(false);
		transcriberAbbrDs.setFetchDataURL("/fds/transcribers/usernames");

		transcriberAbbrDs.fetchData(null, new DSCallback() {

			@Override
			public void execute(DSResponse response, Object rawData,
					DSRequest request) {
				Record[] records = response.getData();

				transcriberMap = new HashMap<String, String>();

				for (Record record : records) {
					Log.debug(record.getAttributeAsString("transcriberId")
							+ "::" + record.getAttributeAsString("userName"));
					transcriberMap.put(record.getAttribute("transcriberId"),
							record.getAttribute("userName"));
				}

				transcriberField.setValueMap(transcriberMap);
				editorField.setValueMap(transcriberMap);
				prooferField.setValueMap(transcriberMap);
				qaField.setValueMap(transcriberMap);

			}
		});

		RestDataSource fileAssignmentDataGridDs = new RestDataSource();

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

		fileAssignmentDataGridDs.setOperationBindings(fetch, update);
		fileAssignmentDataGridDs.setSendMetaData(false);

		fileAssignmentDataGridDs.setDataURL("/fds/voicefileassignments");

		DataSourceTextField fileAssignmentIdDataField = new DataSourceTextField(
				"fileAssignmentId", "File Assignment Id");
		fileAssignmentIdDataField.setHidden(true);
		fileAssignmentIdDataField.setPrimaryKey(true);
		DataSourceTextField fileIdDataField = new DataSourceTextField("fileId",
				"File Id");
		fileIdDataField.setHidden(true);
		DataSourceTextField fileNameDataField = new DataSourceTextField(
				"fileName", "File Name");
		DataSourceTextField transcriberDataField = new DataSourceTextField(
				"transcriberId", "Transcriber");
		DataSourceTextField editorDataField = new DataSourceTextField(
				"editorId", "Editor");
		DataSourceTextField prooferDataField = new DataSourceTextField(
				"prooferId", "Proofer");
		DataSourceTextField qaDataField = new DataSourceTextField("QAId", "QA");

		fileAssignmentDataGridDs.setFields(fileAssignmentIdDataField, fileIdDataField,
				fileNameDataField, transcriberDataField, editorDataField,
				prooferDataField, qaDataField);

		fileDistributionGrid.setDataSource(fileAssignmentDataGridDs,
				fileNameField, transcriberField, editorField, prooferField,
				qaField);

		fileDistributionGrid.fetchData();

		Label titleLabel = new Label("Welcome to File Distribution List Screen");
		titleLabel.setWidth100();
		titleLabel.setBorder("1px solid #808080");
		titleLabel.setBackgroundColor("#C3D9FF");
		titleLabel.setAlign(Alignment.CENTER);
		titleLabel.setHeight(50);

		VLayout layout = new VLayout(10);
		layout.setWidth100();
		layout.setHeight100();
		layout.addMember(titleLabel);

		layout.addMember(fileDistributionGrid);

		HLayout buttonLayout = new HLayout();

		IButton submitButton = new IButton("Submit");
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				fileDistributionGrid.saveAllEdits(new Function() {

					@Override
					public void execute() {
						SC.say("Saved Sucessfully");

					}

				});
			}
		});

		submitButton.setWidth(120);
		submitButton.setAlign(Alignment.CENTER);

		buttonLayout.addMember(submitButton);
		buttonLayout.setMembersMargin(10);
		layout.addMember(buttonLayout);

		addChild(layout);
	}
}