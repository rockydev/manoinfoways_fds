/**
 * 
 */
package com.project.fms.user.widgets;

import com.project.fms.user.data.FilesData;
import com.project.fms.user.data.MessagesLibrary;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
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
public class TranscriberUI extends Canvas {

	public TranscriberUI() {
		setMargin(50);
		setWidth100();
		setHeight100();
		setAlign(Alignment.CENTER);
		final ListGrid transcriberFilesGrid = new ListGrid() {  
            @Override  
            protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {  
  
                String fieldName = this.getFieldName(colNum);  
  
                if (fieldName.equals("uploadButton")) {  
                	ButtonManager uploadButton = new ButtonManager();  
                	uploadButton.setUploadButtonIcon(record.getAttribute("fileStatus"),MessagesLibrary.ROLE_TRANSCRIBER);  
                	uploadButton.addClickHandler(new ClickHandler() {  
                        public void onClick(ClickEvent event) {  
                            new UploadWindow().show();
                        }  
                    });  
                    return uploadButton;  
                } else {  
                    return null;  
                }  
  
            }  
        };  
        transcriberFilesGrid.setShowRecordComponents(true);          
        transcriberFilesGrid.setShowRecordComponentsByCell(true);  
        transcriberFilesGrid.setCanRemoveRecords(false);  
        
        transcriberFilesGrid.setWidth100();  
        transcriberFilesGrid.setHeight100();  
        transcriberFilesGrid.setShowAllRecords(true);  
  
        ListGridField fileNameField = new ListGridField("fileName", "File Name");  
        fileNameField.setAlign(Alignment.CENTER);  
        fileNameField.setType(ListGridFieldType.TEXT);
  
        ListGridField fileIdField = new ListGridField("fileId", "File ID");
        ListGridField fileStatusField = new ListGridField("fileStatus", "File Status");
        
        ListGridField buttonField = new ListGridField("uploadButton", "Upload");  
        buttonField.setAlign(Alignment.CENTER);  
          
  
        transcriberFilesGrid.setFields(fileNameField,fileIdField, fileStatusField, buttonField);
        fileStatusField.setHidden(true);
        transcriberFilesGrid.setCanResizeFields(true);  
        transcriberFilesGrid.setData(FilesData.getRecords());
        addChild(transcriberFilesGrid);
	}

}
