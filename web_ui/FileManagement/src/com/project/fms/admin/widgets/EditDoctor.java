package com.project.fms.admin.widgets;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EditDoctor extends Canvas {
	
	public EditDoctor()
	{
		final ListGrid doctorGrid = new ListGrid();
		
		doctorGrid.setWidth100();
		doctorGrid.setHeight100();
		
		doctorGrid.setShowFilterEditor(true);
		doctorGrid.setFilterOnKeypress(true);
	    //countryGrid.setDataSource(WorldXmlDS.getInstance());
		doctorGrid.setAutoFetchData(true);
		doctorGrid.setShowRecordComponents(true);          
		doctorGrid.setShowRecordComponentsByCell(true);  
		doctorGrid.setCanRemoveRecords(true); 
	    
		ListGridField docIdField = new ListGridField("docAbbr", "Doctor Abbr");
	    ListGridField docNameField = new ListGridField("docName", "Doctor Name");
	    ListGridField clinicNameField = new ListGridField("clinicName", "Clinic Name");
	    clinicNameField.setValueMap("Europe", "Asia", "North America", "Australia/Oceania", "South America", "Africa");  

	    ListGridField doctorTollFreeIdField = new ListGridField("doctorTollFreeId", "DoctorTollFreeId");
	    ListGridField doctorResourceIdField = new ListGridField("doctorResourceId", "DoctorResourceId");
	    ListGridField doctorTagAndIPField = new ListGridField("doctorComputerTagAndIP", "DoctorComputerTag & IP");
	    doctorGrid.setFields(docIdField, docNameField, clinicNameField, doctorTollFreeIdField, doctorResourceIdField, doctorTagAndIPField);
	    
	    Label titleLabel = new Label("Welcome to Edit Doctor Screen");
	    titleLabel.setWidth100();
	    titleLabel.setBorder("1px solid #808080");
	    titleLabel.setBackgroundColor("#C3D9FF");
	    titleLabel.setAlign(Alignment.CENTER);
	    titleLabel.setHeight(50);
	    
	    VLayout layout = new VLayout(10);
	    layout.setWidth100();
	    layout.setHeight100();
	    layout.addMember(titleLabel);
	    layout.addMember(doctorGrid);
        
	    HLayout buttonLayout = new HLayout();
	    
	    IButton addButton = new IButton("Add New");  
	    addButton.setTop(250);  
	    addButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                doctorGrid.startEditingNew();  
            }  
        });
	    
	    addButton.setWidth(120);
	    addButton.setAlign(Alignment.CENTER);
        
	    
	    IButton submitButton = new IButton("Submit");  
	    submitButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                                
            }             
        });
	     
	    submitButton.setWidth(120);
	    submitButton.setAlign(Alignment.CENTER);
          
        IButton saveButton = new IButton("Save");  
        saveButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                                  
            }  
              
        });
        saveButton.setWidth(120);
        saveButton.setAlign(Alignment.CENTER);
        
        buttonLayout.addMember(addButton);
        buttonLayout.addMember(submitButton);
        buttonLayout.addMember(saveButton);
        buttonLayout.setMembersMargin(10);
        layout.addMember(buttonLayout);
	    
	    addChild(layout);
	}
}