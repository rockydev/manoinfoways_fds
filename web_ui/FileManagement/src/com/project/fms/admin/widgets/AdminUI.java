package com.project.fms.admin.widgets;

import com.allen_sauer.gwt.log.client.DivLogger;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.WidgetCanvas;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class AdminUI extends Canvas {
	public AdminUI() {
		setWidth("95%");
		setHeight("80%");

		final TabSet tabSet = new TabSet();
		tabSet.setTabBarPosition(Side.TOP);
		
		
		Tab tTab0 = new Tab("Clinic Information");
		AddClinicUI addClinicWidget = new AddClinicUI();
		tTab0.setPane(addClinicWidget);

//		Tab tTab1 = new Tab("Add new Doctor");
//		AddDoctorUI docUI = new AddDoctorUI();
//		tTab1.setPane(docUI);
		
		Tab tTab2 = new Tab("Doctor Information Screen");
		EditDoctor editDoc = new EditDoctor();
		tTab2.setPane(editDoc);
		
//		Tab tTab3 = new Tab("Transcriber Information");
//		TranscriberInformationWidget transcriberInformationWidget = new TranscriberInformationWidget();
//		tTab3.setPane(transcriberInformationWidget);
		
//		Tab tTab4 = new Tab("Edit Transcriber Info");
//		EditTranscriberUI editTranscriber = new EditTranscriberUI();
//		tTab4.setPane(editTranscriber);
		
//		Tab tTab5 = new Tab("Edit Clinic Info");
//		EditClinic editClinicObj = new EditClinic();
//		tTab5.setPane(editClinicObj);
		
		Tab tTab6 = new Tab("Transcriber Information Screen");
//		TranscriberInformationUI transcriberInformationWidget = new TranscriberInformationUI();
		TranscriberInformationWidget transcriberInformationWidget = new TranscriberInformationWidget();
		tTab6.setPane(transcriberInformationWidget);
		
		Tab tTab7 = new Tab("Transcriber Doctor Priority Screen");
		TranscriberDoctorPriorityWidget transcriberDoctorWidget = new TranscriberDoctorPriorityWidget();
		tTab7.setPane(transcriberDoctorWidget);
		
		Tab tTab8 = new Tab("File Distribution List Screen");
		FileDistributionListWidget fileDistributionWidget = new FileDistributionListWidget();
		tTab8.setPane(fileDistributionWidget);
		
		tabSet.addTab(tTab0);
//		tabSet.addTab(tTab1);
		tabSet.addTab(tTab2);
//		tabSet.addTab(tTab3);
//		tabSet.addTab(tTab4);
//		tabSet.addTab(tTab5);
		tabSet.addTab(tTab6);
		tabSet.addTab(tTab7);
		tabSet.addTab(tTab8);
		
		VLayout vLayout = new VLayout();
		vLayout.setWidth100();  
		vLayout.setHeight100();
		vLayout.setDefaultLayoutAlign(Alignment.CENTER);
		vLayout.setMembersMargin(15);
		vLayout.addMember(tabSet);
		
		addChild(vLayout);
		
		
	}

}
