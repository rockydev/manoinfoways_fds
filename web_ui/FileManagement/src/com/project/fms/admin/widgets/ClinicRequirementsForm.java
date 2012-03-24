package com.project.fms.admin.widgets;

import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ClinicRequirementsForm extends VLayout{

	public ClinicRequirementsForm(){
		setPadding(5);
		setMembersMargin(25);
		HLayout hLayout = new HLayout();
		hLayout.setMembersMargin(25);
  
        final ListGrid requirementGrid = new ListGrid();  
        requirementGrid.setHeight100();  
        requirementGrid.setShowAllRecords(true);  
        requirementGrid.setDataSource(RequirementXMLDS.getInstance());
        
        requirementGrid.setAutoFetchData(true);  
        requirementGrid.setCanEdit(true);  
        requirementGrid.setModalEditing(true);  
        requirementGrid.setEditEvent(ListGridEditEvent.CLICK);  
        requirementGrid.setListEndEditAction(RowEndEditAction.NEXT);  
        requirementGrid.setAutoSaveEdits(false);
  
        
        hLayout.addMember(requirementGrid);    
        
        HLayout buttonsLayout = new HLayout();
        IButton addRequirementButton = new IButton("Add Requirement");
        IButton discardRequirementButton = new IButton("Discard");
        IButton saveRequirementButton = new IButton("Save");
  
        addRequirementButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
            	requirementGrid.startEditingNew();  
            }  
        });  
        
        discardRequirementButton.setLeft(220);  
        discardRequirementButton.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
            	requirementGrid.discardAllEdits();  
            }  
        }); 
        saveRequirementButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
        
        buttonsLayout.setMembersMargin(25);
        buttonsLayout.addMember(addRequirementButton);
        buttonsLayout.addMember(discardRequirementButton);
        buttonsLayout.addMember(saveRequirementButton);
        buttonsLayout.setHeight("10%");
        
        addMember(hLayout);
        addMember(buttonsLayout);
	}
}
