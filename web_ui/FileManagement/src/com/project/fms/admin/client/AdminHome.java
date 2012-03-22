package com.project.fms.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.project.fms.admin.widgets.AdminUI;
import com.project.fms.admin.widgets.LoginForm;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.events.SubmitValuesEvent;
import com.smartgwt.client.widgets.form.events.SubmitValuesHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class AdminHome implements EntryPoint {

	@Override
	public void onModuleLoad() {
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
				// TODO Auto-generated method stub
				vLayoutDefaultLayoutAlign.removeChild(loginForm);
				vLayoutDefaultLayoutAlign.addMember(new AdminUI());
				vLayoutDefaultLayoutAlign.redraw();
			}
		});
 
        vLayoutDefaultLayoutAlign.draw();
	}

}
