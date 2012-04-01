package com.project.fms.admin.widgets;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TranscriberInformationWidget extends Canvas {
	
	public TranscriberInformationWidget()
	{
		final ListGrid transcriberGrid = new ListGrid();
		
		transcriberGrid.setWidth100();
		transcriberGrid.setHeight100();
		
		transcriberGrid.setShowFilterEditor(true);
		transcriberGrid.setFilterOnKeypress(true);
		transcriberGrid.setAutoFetchData(true);  
		transcriberGrid.setCanEdit(true);  
		transcriberGrid.setModalEditing(true);  
		transcriberGrid.setEditEvent(ListGridEditEvent.CLICK);  
		transcriberGrid.setListEndEditAction(RowEndEditAction.NEXT);  
		transcriberGrid.setAutoSaveEdits(false);
		
        
		
		transcriberGrid.setDataSource(TranscriberXMLDS.getInstance());
		transcriberGrid.setCanRemoveRecords(true); 
	    
	    Label titleLabel = new Label("Welcome to Transcribers Information Screen");
	    titleLabel.setWidth100();
	    titleLabel.setBorder("1px solid #808080");
	    titleLabel.setBackgroundColor("#C3D9FF");
	    titleLabel.setAlign(Alignment.CENTER);
	    titleLabel.setHeight(50);
	    
	    VLayout layout = new VLayout(10);
	    layout.setWidth100();
	    layout.setHeight100();
	    layout.addMember(titleLabel);
	    layout.addMember(transcriberGrid);
        
	    HLayout buttonLayout = new HLayout();
	    
	    IButton addButton = new IButton("Add New");  
	    addButton.setTop(250);  
	    addButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                transcriberGrid.startEditingNew();  
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
	    
	    submitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
          
        
        buttonLayout.addMember(addButton);
        buttonLayout.addMember(submitButton);
        buttonLayout.setMembersMargin(10);
        layout.addMember(buttonLayout);
	    
	    addChild(layout);
	}
}