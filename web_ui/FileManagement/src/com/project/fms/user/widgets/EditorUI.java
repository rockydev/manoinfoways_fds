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
public class EditorUI extends Canvas {
	public EditorUI(){
		setMargin(50);
		setWidth100();
		setHeight100();
		setAlign(Alignment.CENTER);
		final ListGrid editorFilesGrid = new ListGrid() {  
            @Override  
            protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {  
  
                String fieldName = this.getFieldName(colNum);  
  
                if (fieldName.equals("uploadButton")) {  
                	ButtonManager uploadButton = new ButtonManager(); 
                	uploadButton.setUploadButtonIcon(record.getAttribute("fileStatus"),MessagesLibrary.ROLE_EDITOR);  
                	uploadButton.addClickHandler(new ClickHandler() {  
                        public void onClick(ClickEvent event) {  
                            new UploadWindow().show();
                        }  
                    });  
                    return uploadButton;  
                } else if (fieldName.equals("sendbackButton")) {  
                	ButtonManager sendBackButton = new ButtonManager();
                	sendBackButton.setSendBackButtonIcon(record.getAttribute("fileStatus"),MessagesLibrary.ROLE_EDITOR);  
                	sendBackButton.addClickHandler(new ClickHandler() {  
                        public void onClick(ClickEvent event) {  
                            SC.say("Sent back to Transcriber");
                        }  
                    });  
                    return sendBackButton;  
                }else {  
                    return null;  
                }  
  
            }  
        };  
        
        editorFilesGrid.setShowRecordComponents(true);          
        editorFilesGrid.setShowRecordComponentsByCell(true);  
        editorFilesGrid.setCanRemoveRecords(false);  
        
        editorFilesGrid.setWidth100();  
        editorFilesGrid.setHeight100();  
        editorFilesGrid.setShowAllRecords(true);  
  
        ListGridField fileNameField = new ListGridField("fileName", "File Name");  
        fileNameField.setAlign(Alignment.CENTER);  
        fileNameField.setType(ListGridFieldType.TEXT);
  
        ListGridField fileIdField = new ListGridField("fileId", "File ID");
        ListGridField fileStatusField = new ListGridField("fileStatus", "File Status");
        
        ListGridField buttonField = new ListGridField("uploadButton", "Upload");  
        ListGridField sendbackButtonField = new ListGridField("sendbackButton", "Send Back");
		sendbackButtonField.setAlign(Alignment.CENTER);
  
        editorFilesGrid.setFields(fileNameField,fileIdField, fileStatusField, buttonField, sendbackButtonField);  
        fileStatusField.setHidden(true);
        editorFilesGrid.setCanResizeFields(true);  
        editorFilesGrid.setData(FilesData.getRecords());
        addChild(editorFilesGrid);
	}
}
