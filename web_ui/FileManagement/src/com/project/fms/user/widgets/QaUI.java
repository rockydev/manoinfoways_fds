/**
 * 
 */
package com.project.fms.user.widgets;

import com.project.fms.user.data.FilesData;
import com.project.fms.user.data.MessagesLibrary;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author deadlydeo
 * 
 */
public class QaUI extends Canvas {
	public QaUI() {
		setMargin(50);
		setWidth100();
		setHeight100();
		setAlign(Alignment.CENTER);
		final ListGrid qaFilesGrid = new ListGrid() {
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record,
					Integer colNum) {

				String fieldName = this.getFieldName(colNum);

				if (fieldName.equals("uploadButton")) {
					ButtonManager uploadButton = new ButtonManager();
					uploadButton.setUploadButtonIcon(
							record.getAttribute("fileStatus"),
							MessagesLibrary.ROLE_QA);
					uploadButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							new UploadWindow().show();
						}
					});
					return uploadButton;
				} else if (fieldName.equals("sendbackButton")) {
					ButtonManager sendBackButton = new ButtonManager();
					sendBackButton.setSendBackButtonIcon(
							record.getAttribute("fileStatus"),
							MessagesLibrary.ROLE_QA);
					sendBackButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							SC.say("Sent back to Transcriber");
						}
					});
					return sendBackButton;
				} else {
					return null;
				}

			}
		};
		qaFilesGrid.setShowRecordComponents(true);
		qaFilesGrid.setShowRecordComponentsByCell(true);
		qaFilesGrid.setCanRemoveRecords(false);

		qaFilesGrid.setWidth100();
		qaFilesGrid.setHeight100();
		qaFilesGrid.setShowAllRecords(true);

		ListGridField fileNameField = new ListGridField("fileName", "File Name");
		fileNameField.setAlign(Alignment.CENTER);
		fileNameField.setType(ListGridFieldType.TEXT);

		ListGridField fileIdField = new ListGridField("fileId", "File ID");
		ListGridField fileStatusField = new ListGridField("fileStatus",
				"File Status");

		ListGridField buttonField = new ListGridField("uploadButton", "Upload");
		buttonField.setAlign(Alignment.CENTER);
		ListGridField sendbackButtonField = new ListGridField("sendbackButton", "Send Back");
		sendbackButtonField.setAlign(Alignment.CENTER);

		qaFilesGrid.setFields(fileNameField, fileIdField, fileStatusField,
				buttonField, sendbackButtonField);
		qaFilesGrid.setCanResizeFields(true);
		qaFilesGrid.setData(FilesData.getRecords());
		addChild(qaFilesGrid);
	}
}
