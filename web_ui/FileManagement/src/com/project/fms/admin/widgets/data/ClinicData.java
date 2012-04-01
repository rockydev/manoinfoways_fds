package com.project.fms.admin.widgets.data;

import com.smartgwt.client.data.Record;
  
@SuppressWarnings("unused")
public class ClinicData extends Record {  
  
    public ClinicData() {  
    }  
  
    public ClinicData(String clinicAbbr, String clinicName, String addressLine1, 
    		String addressLine2, String location, String country, String zipcode, 
    		ClinicConnectionDetailsData connDetails, ClinicMetadataData metadata) {  
        setClinicAbbr(clinicAbbr);  
        setClinicName(clinicName);
        setAddressLine1(addressLine1);
        setAddressLine2(addressLine2);
        setLocation(location);
        setCountry(country);  
        setZipcode(zipcode);
        setClinicConnectionDetailsData(connDetails);
        setClinicMetadataData(metadata);
    }  

	public ClinicData(String clinicId) {
		setClinicId(clinicId);
	}
    
	private void setClinicConnectionDetailsData(
			ClinicConnectionDetailsData connDetails) {
    	setAttribute("clinicConnectionDetails",connDetails);
	}
	
	private ClinicConnectionDetailsData getClinicConnectionDetailsData()
	{
		return (ClinicConnectionDetailsData) getAttributeAsObject("clinicConnectionDetails");
	}
	
	private void setClinicMetadataData(ClinicMetadataData metadata) {
		setAttribute("clinicMetadata", metadata);
	}
	
	private ClinicMetadataData getClinicMetadataData()
	{
		return (ClinicMetadataData) getAttributeAsObject("clinicMetadata");
	}
	
	public void setClinicName(String clinicName) {
		setAttribute("clinicName", clinicName);
		
	}
    
    public String getClinicName() {  
        return getAttributeAsString("clinicName");  
    }
    
    public void setAddressLine1(String addressLine1) {  
        setAttribute("addressLine1", addressLine1);  
    }  
  
    public String getAddressLine1() {  
        return getAttributeAsString("addressLine1");  
    }  
    
    public void setAddressLine2(String addressLine2) {  
        setAttribute("addressLine2", addressLine2);  
    }  
  
    public String getAddressLine2() {  
        return getAttributeAsString("addressLine2");  
    }  
    
	public void setCountry(String country) {  
        setAttribute("country", country);  
    }  
  
    public String getCountry() {  
        return getAttributeAsString("country");  
    }  
    
    public void setLocation(String location) {  
        setAttribute("location", location);  
    }  
  
    public String getLocation() {  
        return getAttributeAsString("location");  
    }  
    
    public void setClinicAbbr(String clinicAbbr) {  
        setAttribute("clinicAbbr", clinicAbbr);  
    }  
  
    public String getClinicAbbr() {  
        return getAttributeAsString("clinicAbbr");  
    }  
  
    public void setClinicId(String clinicId) {  
        setAttribute("clinicId", clinicId);  
    }  
  
    public String getClinicId() {  
        return getAttributeAsString("clinicId");  
    }  
  
    public void setZipcode(String zipcode) {  
        setAttribute("zipcode", zipcode);  
    }  
  
    public String getZipcode() {  
        return getAttributeAsString("zipcode");  
    }  
  
    public String getFieldValue(String field) {  
        return getAttributeAsString(field);  
    }  
}  
