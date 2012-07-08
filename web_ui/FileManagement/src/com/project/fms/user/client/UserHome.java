package com.project.fms.user.client;

import com.google.gwt.core.client.EntryPoint;
import com.project.fms.user.widgets.LoginForm;
import com.project.fms.user.widgets.UserUI;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.events.SubmitValuesEvent;
import com.smartgwt.client.widgets.form.events.SubmitValuesHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserHome implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		final VLayout vLayoutDefaultLayoutAlign = new VLayout();  
        vLayoutDefaultLayoutAlign.setWidth("100%");  
        vLayoutDefaultLayoutAlign.setHeight100();  
        vLayoutDefaultLayoutAlign.setLayoutMargin(6);  
        vLayoutDefaultLayoutAlign.setMembersMargin(6);  
        vLayoutDefaultLayoutAlign.setDefaultLayoutAlign(Alignment.CENTER);  
        vLayoutDefaultLayoutAlign.addMember(new Img("banner.jpg", 640, 100) {{  
        }});
        final LoginForm loginForm = new LoginForm();
        
        vLayoutDefaultLayoutAlign.addMember(loginForm);
        
        loginForm.addSubmitValuesHandler(new SubmitValuesHandler() {
			
			@Override
			public void onSubmitValues(SubmitValuesEvent event) {
//				SC.say(SessionHandler.getSessionInstance().getUserId() + SessionHandler.getSessionInstance().getUserRole());
				vLayoutDefaultLayoutAlign.removeChild(loginForm);
				vLayoutDefaultLayoutAlign.addMember(new UserUI());
				vLayoutDefaultLayoutAlign.redraw();
			}
		});
 
        vLayoutDefaultLayoutAlign.draw();
	}

}
