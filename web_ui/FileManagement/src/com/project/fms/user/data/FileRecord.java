package com.project.fms.user.data;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class FileRecord extends ListGridRecord{
	
	public FileRecord(){
		
	}
	public FileRecord(String fileName, String fileId, String fileStatus){
		setFileName(fileName);
		setFileId(fileId);
		setFileStatus(fileStatus);		
	}
	
	public void setFileName(String fileName){
		setAttribute("fileName", fileName);
	}
	public void setFileId(String fileId){
		setAttribute("fileId",fileId);
	}
	public void setFileStatus(String fileStatus){
		setAttribute("fileStatus", fileStatus);
	}

}
