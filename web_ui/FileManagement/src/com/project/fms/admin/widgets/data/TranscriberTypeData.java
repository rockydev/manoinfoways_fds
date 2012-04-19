/**
 * 
 */
package com.project.fms.admin.widgets.data;

import com.smartgwt.client.data.Record;

/**
 * @author rdevinen
 *
 */
public class TranscriberTypeData extends Record {

	public TranscriberTypeData(String transcriberTypeId, String transcriberTypeName) {
		setTranscriberTypeId(transcriberTypeId);
		setTranscriberTypeName(transcriberTypeName);
	}

	public void setTranscriberTypeName(String transcriberTypeName) {
		setAttribute("transcriberTypeName", transcriberTypeName);
		
	}
	
	public String getTranscriberTypeName()
	{
		return getAttributeAsString("transcriberTypeName");
	}
	
	public void setTranscriberTypeId(String transcriberTypeId) {
		setAttribute("transcriberTypeId",transcriberTypeId);
	}
	
	public String getTranscriberTypeId()
	{
		return getAttributeAsString("transcriberTypeId");
	}
}
