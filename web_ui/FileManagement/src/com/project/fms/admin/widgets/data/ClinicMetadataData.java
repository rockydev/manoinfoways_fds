/**
 * 
 */
package com.project.fms.admin.widgets.data;

import com.smartgwt.client.data.Record;

/**
 * @author rdevinen
 *
 */
public class ClinicMetadataData extends Record {
	public ClinicMetadataData() {
	}
	
	
//	public ClinicMetadataData(ClinicData clinicData,
//			String transcriptionsInchargeName,
//			String transcriptionsInchargeEmail,
//			String transcriptionInchargePhnNo,
//			String trancriptionInchargeFaxNo,
//			String transcriptionInchargeAddress, String technicalPersonName,
//			String technicalPersonEmail, String technicalPersonPhnNo,
//			String technicalPersonFaxNo, String technicalPersonAddress,
//			String softwarePersonName, String softwarePersonEmail,
//			String softwarePersonPhnNo, String softwarePersonCellNo,
//			String softwarePersonAddress, String emailForPatientList,
//			String emailForInvoices) {
//		setClinicdata(clinicData);
//		setTranscriptionsInchargeName(transcriptionsInchargeName);
//		setTranscriptionsInchargeEmail(transcriptionsInchargeEmail);
//		setTranscriptionInchargePhnNo(transcriptionInchargePhnNo);
//		setTrancriptionInchargeFaxNo(trancriptionInchargeFaxNo);
//		setTranscriptionInchargeAddress(transcriptionInchargeAddress);
//		setTechnicalPersonName(technicalPersonName);
//		setTechnicalPersonEmail(technicalPersonEmail);
//		setTechnicalPersonPhnNo(technicalPersonPhnNo);
//		setTechnicalPersonFaxNo(technicalPersonFaxNo);
//		setTechnicalPersonAddress(technicalPersonAddress);
//		setSoftwarePersonName(softwarePersonName);
//		setSoftwarePersonEmail(softwarePersonEmail);
//		setSoftwarePersonPhnNo(softwarePersonPhnNo);
//		setSoftwarePersonCellNo(softwarePersonCellNo);
//		setSoftwarePersonAddress(softwarePersonAddress);
//		setEmailForPatientList(emailForPatientList);
//		setEmailForInvoices(emailForInvoices);
//	}

	public ClinicMetadataData(
			String transcriptionsInchargeName,
			String transcriptionsInchargeEmail,
			String transcriptionInchargePhnNo,
			String trancriptionInchargeFaxNo,
			String transcriptionInchargeAddress, String technicalPersonName,
			String technicalPersonEmail, String technicalPersonPhnNo,
			String technicalPersonFaxNo, String technicalPersonAddress,
			String softwarePersonName, String softwarePersonEmail,
			String softwarePersonPhnNo, String softwarePersonCellNo,
			String softwarePersonAddress, String emailForPatientList,
			String emailForInvoices) {
		setTranscriptionsInchargeName(transcriptionsInchargeName);
		setTranscriptionsInchargeEmail(transcriptionsInchargeEmail);
		setTranscriptionInchargePhnNo(transcriptionInchargePhnNo);
		setTrancriptionInchargeFaxNo(trancriptionInchargeFaxNo);
		setTranscriptionInchargeAddress(transcriptionInchargeAddress);
		setTechnicalPersonName(technicalPersonName);
		setTechnicalPersonEmail(technicalPersonEmail);
		setTechnicalPersonPhnNo(technicalPersonPhnNo);
		setTechnicalPersonFaxNo(technicalPersonFaxNo);
		setTechnicalPersonAddress(technicalPersonAddress);
		setSoftwarePersonName(softwarePersonName);
		setSoftwarePersonEmail(softwarePersonEmail);
		setSoftwarePersonPhnNo(softwarePersonPhnNo);
		setSoftwarePersonCellNo(softwarePersonCellNo);
		setSoftwarePersonAddress(softwarePersonAddress);
		setEmailForPatientList(emailForPatientList);
		setEmailForInvoices(emailForInvoices);
	}
	
	public String getMetaDataId() {
		return getAttributeAsString("metaDataId");
	}

	public void setMetaDataId(Integer metaDataId) {
		setAttribute("metaDataId",metaDataId);
	}

	public String getTranscriptionsInchargeName() {
		return getAttributeAsString("transcriptionsInchargeName");
	}

	public void setTranscriptionsInchargeName(String transcriptionsInchargeName) {
		setAttribute("transcriptionsInchargeName",transcriptionsInchargeName);
	}

	public String getTranscriptionsInchargeEmail() {
		return getAttributeAsString("transcriptionsInchargeEmail");
	}

	public void setTranscriptionsInchargeEmail(
			String transcriptionsInchargeEmail) {
		setAttribute("transcriptionsInchargeEmail",transcriptionsInchargeEmail);
	}

	public String getTranscriptionInchargePhnNo() {
		return getAttributeAsString("transcriptionInchargePhnNo");
	}

	public void setTranscriptionInchargePhnNo(String transcriptionInchargePhnNo) {
		setAttribute("transcriptionInchargePhnNo",transcriptionInchargePhnNo);
	}

	public String getTrancriptionInchargeFaxNo() {
		return getAttributeAsString("trancriptionInchargeFaxNo");
	}

	public void setTrancriptionInchargeFaxNo(String trancriptionInchargeFaxNo) {
		setAttribute("trancriptionInchargeFaxNo",trancriptionInchargeFaxNo);
	}

	public String getTranscriptionInchargeAddress() {
		return getAttributeAsString("transcriptionInchargeAddress");
	}

	public void setTranscriptionInchargeAddress(
			String transcriptionInchargeAddress) {
		setAttribute("transcriptionInchargeAddress",transcriptionInchargeAddress);
	}

	public String getTechnicalPersonName() {
		return getAttributeAsString("technicalPersonName");
	}

	public void setTechnicalPersonName(String technicalPersonName) {
		setAttribute("technicalPersonName",technicalPersonName);
	}

	public String getTechnicalPersonEmail() {
		return getAttributeAsString("technicalPersonEmail");
	}

	public void setTechnicalPersonEmail(String technicalPersonEmail) {
		setAttribute("technicalPersonEmail",technicalPersonEmail);
	}

	public String getTechnicalPersonPhnNo() {
		return getAttributeAsString("technicalPersonPhnNo");
	}

	public void setTechnicalPersonPhnNo(String technicalPersonPhnNo) {
		setAttribute("technicalPersonPhnNo",technicalPersonPhnNo);
	}

	public String getTechnicalPersonFaxNo() {
		return getAttributeAsString("technicalPersonFaxNo");
	}

	public void setTechnicalPersonFaxNo(String technicalPersonFaxNo) {
		setAttribute("technicalPersonFaxNo",technicalPersonFaxNo);
	}

	public String getTechnicalPersonAddress() {
		return getAttributeAsString("technicalPersonAddress");
	}

	public void setTechnicalPersonAddress(String technicalPersonAddress) {
		setAttribute("technicalPersonAddress",technicalPersonAddress);
	}

	public String getSoftwarePersonName() {
		return getAttributeAsString("softwarePersonName");
	}

	public void setSoftwarePersonName(String softwarePersonName) {
		setAttribute("softwarePersonName",softwarePersonName);
	}

	public String getSoftwarePersonEmail() {
		return getAttributeAsString("softwarePersonEmail");
	}

	public void setSoftwarePersonEmail(String softwarePersonEmail) {
		setAttribute("softwarePersonEmail",softwarePersonEmail);
	}

	public String getSoftwarePersonPhnNo() {
		return getAttributeAsString("softwarePersonPhnNo");
	}

	public void setSoftwarePersonPhnNo(String softwarePersonPhnNo) {
		setAttribute("softwarePersonPhnNo",softwarePersonPhnNo);
	}

	public String getSoftwarePersonCellNo() {
		return getAttributeAsString("softwarePersonCellNo");
	}

	public void setSoftwarePersonCellNo(String softwarePersonCellNo) {
		setAttribute("softwarePersonCellNo",softwarePersonCellNo);
	}

	public String getSoftwarePersonAddress() {
		return getAttributeAsString("softwarePersonAddress");
	}

	public void setSoftwarePersonAddress(String softwarePersonAddress) {
		setAttribute("softwarePersonAddress",softwarePersonAddress);
	}

	public String getEmailForPatientList() {
		return getAttributeAsString("emailForPatientList");
	}

	public void setEmailForPatientList(String emailForPatientList) {
		setAttribute("emailForPatientList",emailForPatientList);
	}

	public String getEmailForInvoices() {
		return getAttributeAsString("emailForInvoices");
	}

	public void setEmailForInvoices(String emailForInvoices) {
		setAttribute("emailForInvoices",emailForInvoices);
	}
}
