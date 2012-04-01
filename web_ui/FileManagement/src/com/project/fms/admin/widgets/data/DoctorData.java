package com.project.fms.admin.widgets.data;

import com.smartgwt.client.data.Record;

public class DoctorData extends Record {
	
	 public DoctorData() {  
	    }
	 
	 public DoctorData(String doctorAbbr,
				String doctorName, String doctorTollFreeId,
				String doctorResourceId, String doctorComputerTagsIp) {
			setDoctorAbbr(doctorAbbr);
			setDoctorName(doctorName);
			setDoctorTollFreeId(doctorTollFreeId);
			setDoctorResourceId(doctorResourceId);
			setDoctorComputerTagsIp(doctorComputerTagsIp);
		}

	private void setDoctorComputerTagsIp(String doctorComputerTagsIp) {
			setAttribute("doctorComputerTagsIp", doctorComputerTagsIp);
	}
	
	private void setDoctorResourceId(String doctorResourceId) {
			setAttribute("doctorResourceId", doctorResourceId);
	}

	private void setDoctorTollFreeId(String doctorTollFreeId) {
			setAttribute("doctorTollFreeId", doctorTollFreeId);
	}

	private void setDoctorName(String doctorName) {
		setAttribute("doctorName", doctorName);
	}

	private void setDoctorAbbr(String doctorAbbr) {
		setAttribute("doctorAbbr", doctorAbbr);
	}

//	private void setClinicData(Record clinicId) {
//		setAttribute("clinicData", new ClinicData(clinicId.getAttributeAsString("clinicId")));
//	}
//		
//	@SuppressWarnings("unused")
//	private String getClinicData()
//	{
//		return ((ClinicData)getAttributeAsObject("clinicData")).getClinicId();
//	}
}
