package com.project.fms.admin.widgets;

import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class TranscriberInformationUI extends VLayout{
	
	public TranscriberInformationUI(){
		setMargin(10);
		setMembersMargin(10);
		
		final TabSet transcriberTabset = new TabSet();
		transcriberTabset.setWidth100();
		transcriberTabset.setHeight("80%");
		
		Tab transcriberTypeTab = new Tab("Transcriber Type");
		final TranscriberTypeWidget transcriberTypeWidget = new TranscriberTypeWidget();
		transcriberTypeTab.setPane(transcriberTypeWidget);
		
		
		Tab transcriberInfoTab = new Tab("Transcriber Info");
		final TranscriberInformationWidget transcriberInformationWidget = new TranscriberInformationWidget();
		transcriberInfoTab.setPane(transcriberInformationWidget);
		
		transcriberTabset.setTabs(transcriberTypeTab, transcriberInfoTab);
		addMember(transcriberTabset);
	}
}
