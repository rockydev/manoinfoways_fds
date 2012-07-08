/**
 * 
 */
package com.project.fms.admin.widgets.data;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.data.Record;

/**
 * @author rdevinen
 * 
 */
public class TranscriberData extends Record {

	public TranscriberData(String transcriberTypeId,
			String userName, String password, String transcriberName,
			String dateofJoining, String transcriberAddress,
			String transcriberPermanentAddress, String transcriberPhnNuber,
			String transcriberMobile, String transcriberEmail,
			String transcriberQualification, String transcriberExperience,
			String transcriberDob, String transcriberLoginTime) {
		setTranscriberTypeId(transcriberTypeId);
		setUserName(userName);
		setPassword(password);
		setTranscriberName(transcriberName);
		setDateofJoining(dateofJoining);
		setTranscriberAddress(transcriberAddress);
		setTranscriberPermanentAddress(transcriberPermanentAddress);
		setTranscriberPhnNuber(transcriberPhnNuber);
		setTranscriberMobile(transcriberMobile);
		setTranscriberEmail(transcriberEmail);
		setTranscriberQualification(transcriberQualification);
		setTranscriberExperience(transcriberExperience);
		setTranscriberDob(transcriberDob);
		setTranscriberLoginTime(transcriberLoginTime);
	}

	public String getTranscriberId() {
		return getAttributeAsString("transcriberId");
	}

	public void setTranscriberId(String transcriberId) {
		setAttribute("transcriberId", transcriberId);
	}

	public String getTranscriberTypeId() {
		return getAttributeAsString("transcriberTypeId");
	}

	public void setTranscriberTypeId(String transcriberTypeId) {
		setAttribute("transcriberTypeId", transcriberTypeId);
	}

	public String getUserName() {
		return getAttributeAsString("userName");
	}

	public void setUserName(String userName) {
		setAttribute("userName", userName);
	}

	public String getPassword() {
		return getAttributeAsString("password");
	}

	public void setPassword(String password) {
		setAttribute("password", password);
	}

	public String getTranscriberName() {
		return getAttributeAsString("transcriberName");
	}

	public void setTranscriberName(String transcriberName) {
		setAttribute("transcriberName", transcriberName);
	}

	public String getDateofJoining() {
//		return DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.S z").format(getAttributeAsDate("dataofJoining"));
		 return getAttributeAsString("dateofJoining");
	}

	public void setDateofJoining(String dateofJoining) {
		setAttribute("dateofJoining",dateofJoining);
	}

	public String getTranscriberAddress() {
		return getAttributeAsString("transcriberAddress");
	}

	public void setTranscriberAddress(String transcriberAddress) {
		setAttribute("transcriberAddress", transcriberAddress);
	}

	public String getTranscriberPermanentAddress() {
		return getAttributeAsString("transcriberPermanentAddress");
	}

	public void setTranscriberPermanentAddress(
			String transcriberPermanentAddress) {
		setAttribute("transcriberPermanentAddress", transcriberPermanentAddress);
	}

	public String getTranscriberPhnNuber() {
		return getAttributeAsString("transcriberPhnNuber");
	}

	public void setTranscriberPhnNuber(String transcriberPhnNuber) {
		setAttribute("transcriberPhnNuber", transcriberPhnNuber);
	}

	public String getTranscriberMobile() {
		return getAttributeAsString("transcriberMobile");
	}

	public void setTranscriberMobile(String transcriberMobile) {
		setAttribute("transcriberMobile", transcriberMobile);
	}

	public String getTranscriberEmail() {
		return getAttributeAsString("transcriberEmail");
	}

	public void setTranscriberEmail(String transcriberEmail) {
		setAttribute("transcriberEmail", transcriberEmail);
	}

	public String getTranscriberQualification() {
		return getAttributeAsString("transcriberQualification");
	}

	public void setTranscriberQualification(String transcriberQualification) {
		setAttribute("transcriberQualification", transcriberQualification);
	}

	public String getTranscriberExperience() {
		return getAttributeAsString("transcriberExperience");
	}

	public void setTranscriberExperience(String transcriberExperience) {
		setAttribute("transcriberExperience", transcriberExperience);
	}

	public String getTranscriberDob() {
//		return  DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss.S z").format((getAttributeAsDate("transcriberDob")));
		return getAttributeAsString("transcriberDob");
	}

	public void setTranscriberDob(String transcriberDob) {
		setAttribute("transcriberDob",transcriberDob);
	}

	public String getTranscriberLoginTime() {
//		return DateTimeFormat.getFormat("HH:mm:ss.S z").format(getAttributeAsDate("transcriberLoginTime"));
		return getAttributeAsString("transcriberLoginTime");
	}

	public void setTranscriberLoginTime(String transcriberLoginTime) {
		setAttribute("transcriberLoginTime", transcriberLoginTime);
	}
}
