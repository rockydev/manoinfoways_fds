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
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TranscriberDictationLengthsWidget extends Canvas {

	ListGridField transcriberDicationLengthIdField,transcriberTypeField, dictationLengthField;			
	RestDataSource transcriberGridDs;
	HashMap<String, String> transcriberValueMap;
	ListGrid transcriberGrid;

	public TranscriberDictationLengthsWidget() {

		RestDataSource transcriberAbbrDs = new RestDataSource();

		OperationBinding transcriberFetch = new OperationBinding();
		transcriberFetch.setOperationType(DSOperationType.FETCH);
		transcriberFetch.setDataProtocol(DSProtocol.GETPARAMS);
		transcriberFetch.setDataFormat(DSDataFormat.XML);

		transcriberAbbrDs.setOperationBindings(transcriberFetch);
		transcriberAbbrDs.setSendMetaData(false);
		transcriberAbbrDs.setFetchDataURL("/fds/transcribers/usernames");

		transcriberTypeField = new ListGridField("transcriberTypeId",
				"TranscriberType");
		SelectItem transcriberAbbrSelect = new SelectItem("userName",
				"Select Transcriber");
		transcriberAbbrSelect.setOptionDataSource(transcriberAbbrDs);
		transcriberAbbrSelect.setDisplayField("userName");

		transcriberAbbrSelect.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				HashMap<String, String> transcriberTypeMap = new HashMap<String, String>(
						15);

				switch (Integer.parseInt(((SelectItem) event.getItem())
						.getSelectedRecord().getAttribute("transcriberTypeId"))) {

				case 1:
					transcriberTypeMap.put("1", "Transcriber");
					break;

				case 2:
					transcriberTypeMap.put("2", "Editor");
					break;

				case 3:
					transcriberTypeMap.put("1", "Transcriber");
					transcriberTypeMap.put("2", "Editor");
					break;

				case 4:
					transcriberTypeMap.put("4", "Proofer");
					break;

				case 5:
					transcriberTypeMap.put("1", "Transcriber");
					transcriberTypeMap.put("4", "Proofer");
					break;

				case 6:
					transcriberTypeMap.put("2", "Editor");
					transcriberTypeMap.put("4", "Proofer");
					break;

				case 7:
					transcriberTypeMap.put("1", "Transcriber");
					transcriberTypeMap.put("2", "Editor");
					transcriberTypeMap.put("4", "Proofer");
					break;

				case 8:
					transcriberTypeMap.put("8", "QA");
					break;

				case 9:
					transcriberTypeMap.put("1", "Transcriber");
					transcriberTypeMap.put("8", "QA");
					break;

				case 10:
					transcriberTypeMap.put("2", "Editor");
					transcriberTypeMap.put("8", "QA");
					break;

				case 11:
					transcriberTypeMap.put("1", "Transcriber");
					transcriberTypeMap.put("2", "Editor");
					transcriberTypeMap.put("8", "QA");
					break;

				case 12:
					transcriberTypeMap.put("4", "Proofer");
					transcriberTypeMap.put("8", "QA");
					break;

				case 13:
					transcriberTypeMap.put("1", "Transcriber");
					transcriberTypeMap.put("4", "Proofer");
					transcriberTypeMap.put("8", "QA");
					break;

				case 14:
					transcriberTypeMap.put("2", "Editor");
					transcriberTypeMap.put("4", "Proofer");
					transcriberTypeMap.put("8", "QA");
					break;

				case 15:
					transcriberTypeMap.put("1", "Transcriber");
					transcriberTypeMap.put("2", "Editor");
					transcriberTypeMap.put("4", "Proofer");
					transcriberTypeMap.put("8", "QA");
					break;

				default:
					Log.debug("Invalid Transcriber Type ID");
					break;
				}

				transcriberTypeField.setValueMap(transcriberTypeMap);

				// TranscriberGrid DS
				transcriberGridDs = createDynamicRestDS(((SelectItem) event
						.getItem()).getSelectedRecord().getAttribute(
						"transcriberId"));

				transcriberGrid.setDataSource(transcriberGridDs,
						transcriberDicationLengthIdField, transcriberTypeField,
						dictationLengthField);

				transcriberGrid.fetchData();

			}
		});

//		transcriberTypeField.setShowGridSummary(true);
//		transcriberTypeField.setSummaryFunction(SummaryFunctionType.COUNT);
//		transcriberTypeField.setSummaryTitle("Total:");

		dictationLengthField = new ListGridField("dictationLength", "Dictation Length");
//		priorityField.setShowGridSummary(false);
//		priorityField.setSummaryFunction(SummaryFunctionType.COUNT);
//		priorityField.setSummaryTitle("Total:");

		transcriberDicationLengthIdField = new ListGridField("transcriberLengthTimesId", "Id");
		transcriberDicationLengthIdField.setCanEdit(false);
		transcriberDicationLengthIdField.setHidden(true);

		transcriberGrid = new ListGrid();

		transcriberGrid.setWidth100();
		transcriberGrid.setHeight(520);
		transcriberGrid.setAutoFetchData(true);

		transcriberGrid.setShowAllRecords(true);
		transcriberGrid.setCanEdit(true);
		transcriberGrid.setGroupStartOpen(GroupStartOpen.NONE);
//		transcriberGrid.setShowGridSummary(true);
//		transcriberGrid.setShowGroupSummary(true);
		transcriberGrid.setAutoSaveEdits(false);
		transcriberGrid.setCanRemoveRecords(true);

		transcriberGrid.setFields(transcriberDicationLengthIdField, transcriberTypeField, dictationLengthField);

		Label titleLabel = new Label(
				"Welcome to Transcribers Doctor Priority Screen");
		titleLabel.setWidth100();
		titleLabel.setBorder("1px solid #808080");
		titleLabel.setBackgroundColor("#C3D9FF");
		titleLabel.setAlign(Alignment.CENTER);
		titleLabel.setHeight(50);

		VLayout layout = new VLayout(10);
		layout.setWidth100();
		layout.setHeight100();
		layout.addMember(titleLabel);

		DynamicForm selectForm = new DynamicForm();
		selectForm.setItems(transcriberAbbrSelect);

		layout.addMember(selectForm);
		layout.addMember(transcriberGrid);

		HLayout buttonLayout = new HLayout();

		IButton addButton = new IButton("Add New");
		addButton.setTop(250);
		addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				transcriberGrid.startEditingNew();
			}
		});

		addButton.setWidth(120);
		addButton.setAlign(Alignment.CENTER);

		IButton submitButton = new IButton("Submit");
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				transcriberGrid.saveAllEdits(new Function() {

					@Override
					public void execute() {
						SC.say("Success");
					}
				});
			}
		});

		submitButton.setWidth(120);
		submitButton.setAlign(Alignment.CENTER);

		buttonLayout.addMember(addButton);
		buttonLayout.addMember(submitButton);
		buttonLayout.setMembersMargin(10);
		layout.addMember(buttonLayout);

		addChild(layout);
	}

	private RestDataSource createDynamicRestDS(String transcriberId) {
		RestDataSource transcriberPriorityGridDs = new RestDataSource();

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

		transcriberPriorityGridDs.setOperationBindings(fetch, add, update,
				delete);
		transcriberPriorityGridDs.setSendMetaData(false);

		DataSourceTextField transcriberIdDataField = new DataSourceTextField(
				"transcriberId", "TranscriberId");
		transcriberIdDataField.setCanEdit(false);
		transcriberIdDataField.setHidden(true);

		DataSourceTextField transcriberDictationLengthsIdDataField = new DataSourceTextField(
				"transcriberLengthTimesId", "Id");
		transcriberDictationLengthsIdDataField.setHidden(true);

		DataSourceTextField transcriberTypeDataField = new DataSourceTextField(
				"transcriberTypeId", "Transcriber Type");

		DataSourceTextField dictationLengthDataField = new DataSourceTextField(
				"dictationLength", "Dictation Length");

		transcriberPriorityGridDs.setFields(transcriberDictationLengthsIdDataField, transcriberIdDataField,
				transcriberTypeDataField, dictationLengthDataField);

		String dataUrl = "/fds/transcribers/" + transcriberId + "/lengths";

		transcriberPriorityGridDs.setDataURL(dataUrl);

		return transcriberPriorityGridDs;
	}
}