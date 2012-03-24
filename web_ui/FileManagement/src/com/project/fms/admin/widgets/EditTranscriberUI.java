package com.project.fms.admin.widgets;

import java.util.Date;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class EditTranscriberUI extends DynamicForm{
	
	public EditTranscriberUI(){
		setMargin(50);
		setNumCols(4);  
  
        final SelectItem transcriberSelectedItem = new SelectItem();  
        transcriberSelectedItem.setName("transcriber");  
        transcriberSelectedItem.setTitle("Transcriber");  

        transcriberSelectedItem.setValueMap("Transcriber 1", "Transcriber 2", "Transcriber 3", "Transcriber 4");  
        
        final SelectItem transcriberRole = new SelectItem();  
        transcriberRole.setName("transcriberRole");  
        transcriberRole.setTitle("Role");  
        transcriberRole.setValueMap("Role 1", "Role 2", "Role 3", "Role 4");
        
        final DateItem dateItem = new DateItem();  
        dateItem.setTitle("Joining Date");  
        dateItem.setUseTextField(true);  
        
//        dateItem.setHint("<nobr>Direct date input</nobr>"); 
        IntegerItem transcriberPhno = new IntegerItem("transcriberPhno", "Phone Number: ");
        IntegerItem transcriberMobile = new IntegerItem("transcriberMobile", "Mobile Number: ");
        
        
        TextAreaItem transcriberAddress = new TextAreaItem("transcriberAddress", "Address: ");
        TextAreaItem transcriberPermanentAddress = new TextAreaItem("transcriberPermanentAddress", "Permanent Address: ");
        
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
        
        transcriberSelectedItem.addChangeHandler(new ChangeHandler() {  
            public void onChange(ChangeEvent event) {  
                String selectedItem = (String) event.getValue();  
                transcriberRole.setValue(Integer.parseInt(selectedItem.substring(selectedItem.length()-1, selectedItem.length())));
//                Dat
                dateItem.setValue(new Date());
                getField("transcriberPhno").setValue(Integer.parseInt(selectedItem.substring(selectedItem.length()-1, selectedItem.length())));
                getField("transcriberMobile").setValue(Integer.parseInt(selectedItem.substring(selectedItem.length()-1, selectedItem.length())));
                getField("transcriberAddress").setValue(selectedItem+" - Transcriber's Old Address");
                getField("transcriberPermanentAddress").setValue(selectedItem+" - Transcriber's Old Permanent Address");
            }  
        });    
  
        setFields(transcriberSelectedItem, transcriberRole, dateItem, transcriberPhno, transcriberMobile, transcriberAddress, transcriberPermanentAddress, submitButton);
	}

}
