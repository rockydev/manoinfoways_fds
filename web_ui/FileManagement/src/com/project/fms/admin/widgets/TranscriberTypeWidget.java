package com.project.fms.admin.widgets;

import java.util.LinkedHashMap;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectOtherItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class TranscriberTypeWidget extends VLayout{

	public TranscriberTypeWidget(){
		setWidth100();
		setHeight100();
		setMembersMargin(25);
		setPadding(25);
		
		DynamicForm transcriberTypeForm = new DynamicForm();
		
		final SelectOtherItem transcriberTypeSelect = new SelectOtherItem();  
		transcriberTypeSelect.setOtherTitle("Add New Transcriber Type");
		transcriberTypeSelect.setSelectOtherPrompt("Enter a new Transcriber Type");
		transcriberTypeSelect.setOtherValue("OtherVal");  
		
		
		transcriberTypeSelect.setTitle("Transcriber Type");
		final LinkedHashMap<String, String> valuesMap = new LinkedHashMap<String, String> ();
        valuesMap.put ("TYPE1", "TYPE1".toUpperCase());
        valuesMap.put ("TYPE2", "TYPE2".toUpperCase());
        valuesMap.put ("TYPE3", "TYPE3".toUpperCase());
        valuesMap.put ("TYPE4", "TYPE4".toUpperCase());
        valuesMap.put ("TYPE5", "TYPE5".toUpperCase());

        transcriberTypeSelect.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getValue().toString().equalsIgnoreCase("")){
					SC.say("New Type can not be empty");
				}
				else if(!valuesMap.containsValue(transcriberTypeSelect.getValues()[0].toUpperCase())){
					valuesMap.put(transcriberTypeSelect.getValues()[0].toUpperCase(), transcriberTypeSelect.getValues()[0].toUpperCase());
					transcriberTypeSelect.clearValue();
					transcriberTypeSelect.setValueMap(valuesMap);
				}
			}
		});
		transcriberTypeSelect.setValueMap(valuesMap); 
		transcriberTypeForm.setFields(transcriberTypeSelect);
		addMember(transcriberTypeForm);
	}
}
