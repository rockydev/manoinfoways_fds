/**
 * 
 */
package com.project.fms.user.widgets;

import com.project.fms.user.data.SessionHandler;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * @author deadlydeo
 * 
 */
public class UserUI extends Canvas {
	public UserUI() {

		setWidth("95%");
		setHeight("80%");

		final TabSet tabSet = new TabSet();
		tabSet.setTabBarPosition(Side.TOP);

		TranscriberUI transcriberWidget = null;
		EditorUI editorWidget = null;
		ProoferUI prooferWidget = null;
		QaUI qaWidget = null;

		Tab transcriberTab = null;
		Tab editorTab = null;
		Tab prooferTab = null;
		Tab qaTab = null;
		int roleTypeId = SessionHandler.getSessionInstance().getTypeId();
		try {
			//Do not remove this. Though we are not using it, it will check if the roleType is not null
			
			@SuppressWarnings("unused")
			Integer temp = Integer.valueOf(roleTypeId);
			switch(roleTypeId){
			case 1:
				tabSet.addTab(getTranscriberTab(transcriberTab, transcriberWidget));
				break;
			case 2:
				tabSet.addTab(getEditorTab(editorTab, editorWidget));
				break;
			case 3:
				tabSet.addTab(getTranscriberTab(transcriberTab, transcriberWidget));
				tabSet.addTab(getEditorTab(editorTab, editorWidget));
				break;
			case 4:
				tabSet.addTab(getProoferTab(prooferTab, prooferWidget));
				break;
			case 5:
				tabSet.addTab(getTranscriberTab(transcriberTab, transcriberWidget));
				tabSet.addTab(getProoferTab(prooferTab, prooferWidget));
				break;
			case 6:
				tabSet.addTab(getEditorTab(editorTab, editorWidget));
				tabSet.addTab(getProoferTab(prooferTab, prooferWidget));
				break;
			case 7:
				tabSet.addTab(getTranscriberTab(transcriberTab, transcriberWidget));
				tabSet.addTab(getEditorTab(editorTab, editorWidget));
				tabSet.addTab(getProoferTab(prooferTab, prooferWidget));;
				break;
			case 8:
				tabSet.addTab(getQaTab(qaTab, qaWidget));
				break;
			case 9:
				tabSet.addTab(getTranscriberTab(transcriberTab, transcriberWidget));
				tabSet.addTab(getQaTab(qaTab, qaWidget));
				break;
			case 10:
				tabSet.addTab(getEditorTab(editorTab, editorWidget));
				tabSet.addTab(getQaTab(qaTab, qaWidget));
				break;
			case 11:
				tabSet.addTab(getTranscriberTab(transcriberTab, transcriberWidget));
				tabSet.addTab(getEditorTab(editorTab, editorWidget));
				tabSet.addTab(getQaTab(qaTab, qaWidget));
				break;
			case 12:
				tabSet.addTab(getProoferTab(prooferTab, prooferWidget));
				tabSet.addTab(getQaTab(qaTab, qaWidget));
				break;
			case 13:
				tabSet.addTab(getTranscriberTab(transcriberTab, transcriberWidget));
				tabSet.addTab(getProoferTab(prooferTab, prooferWidget));
				tabSet.addTab(getQaTab(qaTab, qaWidget));
				break;
			case 14:
				tabSet.addTab(getEditorTab(editorTab, editorWidget));
				tabSet.addTab(getProoferTab(prooferTab, prooferWidget));
				tabSet.addTab(getQaTab(qaTab, qaWidget));
				break;
			case 15:
				tabSet.addTab(getTranscriberTab(transcriberTab, transcriberWidget));
				tabSet.addTab(getEditorTab(editorTab, editorWidget));
				tabSet.addTab(getProoferTab(prooferTab, prooferWidget));
				tabSet.addTab(getQaTab(qaTab, qaWidget));
				break;
			default:
				//redirect to home page
				break;
			}
		} catch (Exception exE) {
			//redirect to home page
		}
		

		VLayout vLayout = new VLayout();
		vLayout.setWidth100();
		vLayout.setHeight100();
		vLayout.setDefaultLayoutAlign(Alignment.CENTER);
		vLayout.setMembersMargin(15);
		vLayout.addMember(tabSet);
		addChild(vLayout);

	}
	public Tab getTranscriberTab(Tab transcriberTab, TranscriberUI transcriberWidget){
		transcriberTab = new Tab("Transcriber Screen");
		transcriberWidget = new TranscriberUI();
		transcriberTab.setPane(transcriberWidget);
		return transcriberTab;
	}
	public Tab getEditorTab(Tab editorTab, EditorUI editorWidget){
		editorTab = new Tab("Editor Screen");
		editorWidget = new EditorUI();
		editorTab.setPane(editorWidget);
		return editorTab;
	}
	public Tab getProoferTab(Tab prooferTab, ProoferUI prooferWidget){
		prooferTab = new Tab("Proofer Screen");
		prooferWidget = new ProoferUI();
		prooferTab.setPane(prooferWidget);
		return prooferTab;
	}
	public Tab getQaTab(Tab qaTab, QaUI qaWidget){
		qaTab = new Tab("QA Screen");
		qaWidget = new QaUI();
		qaTab.setPane(qaWidget);
		return qaTab;
	}
}
