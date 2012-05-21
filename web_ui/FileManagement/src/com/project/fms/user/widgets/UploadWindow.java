package com.project.fms.user.widgets;


import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.FormMethod;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class UploadWindow extends Window{
	
	public UploadWindow(){
        setWidth("60%");  
        setHeight("30%"); 
        setPadding(30);
        setMargin(50);
        setTitle("Please upload your file");  
        setShowMinimizeButton(false);  
        setIsModal(true);  
        setShowModalMask(true);  
        centerInPage();  
        addCloseClickHandler(new CloseClickHandler() {  
            public void onCloseClick(CloseClickEvent event) {  
                destroy();  
            }  
        });  
        
        
        VLayout vLayout = new VLayout();
        
        final DynamicForm uploadForm = new DynamicForm();  
        uploadForm.setCanSubmit(true);
        uploadForm.setEncoding(Encoding.MULTIPART);
        uploadForm.setTarget("__gwt_historyFrame");
        uploadForm.setMethod(FormMethod.POST);
        uploadForm.setAction("http://localhost:8080/SampleWebApp/uploadmodule");
        uploadForm.setTitle("Please upload the file");
        UploadItem uploadItem = new UploadItem("upload", "File Upload");
        uploadItem.setRequired(true);
        
        IButton submitButton = new IButton("Submit");
        submitButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				// TODO Auto-generated method stub
				if(uploadForm.validate()==true){
					uploadForm.submit();
					destroy();
				}
				
			}
		});
        uploadForm.setFields(uploadItem);
        vLayout.setMembers(uploadForm, submitButton);
        addItem(vLayout);
//        setMembers(vLayout);
	}

}
