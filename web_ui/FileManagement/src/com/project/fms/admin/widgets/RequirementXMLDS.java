package com.project.fms.admin.widgets;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;

public class RequirementXMLDS extends DataSource {

	private static RequirementXMLDS instance = null;

	public static RequirementXMLDS getInstance() {
		if (instance == null) {
			instance = new RequirementXMLDS("requirementDS");
		}
		return instance;
	}

	public RequirementXMLDS(String id) {

		setID(id);
		setRecordXPath("/List/requirement");

		DataSourceTextField requirementData = new DataSourceTextField(
				"requirementData", "Requirement");

		DataSourceBooleanField isRequirementSelected = new DataSourceBooleanField(
				"isRequirementSelected", "Select");

		

		setFields(isRequirementSelected, requirementData);

		setDataURL("requirement.data.xml");
		setClientOnly(true);
	}
}