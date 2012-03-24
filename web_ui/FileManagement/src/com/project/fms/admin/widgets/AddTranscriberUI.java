package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class AddTranscriberUI extends DynamicForm{
	public AddTranscriberUI(){
		setMargin(50);
		
		setNumCols(4); 
		HeaderItem header = new HeaderItem();  
        header.setDefaultValue("Add New Transcriber");  
  
        TextItem transcriberName = new TextItem("transcriberName", "Name: ");
        
        SelectItem transcriberRole = new SelectItem();  
        transcriberRole.setName("transcriberRole");  
        transcriberRole.setTitle("Role");  
        transcriberRole.setValueMap("Role 1", "Role 2", "Role 3", "Role 4");
        
        
        DateItem dateItem = new DateItem();  
        dateItem.setTitle("Joining Date");  
        dateItem.setUseTextField(true);  
        
//        dateItem.setHint("<nobr>Direct date input</nobr>"); 
        IntegerItem transcriberPhno = new IntegerItem("transcriberPhno", "Phone Number: ");
        IntegerItem transcriberMobile = new IntegerItem("transcriberMobile", "Mobile Number: ");
        
        
        TextAreaItem transcriberAddress = new TextAreaItem("transcriberAddress", "Address: ");
        TextAreaItem transcriberPermanentAddress = new TextAreaItem("transcriberPermanentAddress", "Permanent Address: ");
        
        transcriberName.setRequired(true);
        transcriberRole.setRequired(true);
        dateItem.setRequired(true);
        transcriberPhno.setRequired(true);
        transcriberMobile.setRequired(true);
        transcriberAddress.setRequired(true);
        transcriberPermanentAddress.setRequired(true);
        
        ButtonItem submitButton = new ButtonItem();  
        submitButton.setTitle("Submit"); 
        submitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(validate() == true)
					submit();
			}
		});
        setFields(header,transcriberName, transcriberRole, dateItem, transcriberPhno, transcriberMobile, transcriberAddress, transcriberPermanentAddress, submitButton);
	}
}
