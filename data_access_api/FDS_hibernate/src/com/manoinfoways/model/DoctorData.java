package com.manoinfoways.model;
// Generated Mar 22, 2012 6:27:47 PM by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;

/**
 * DoctorData generated by hbm2java
 */
public class DoctorData  implements java.io.Serializable {


     private Integer doctorId;
     private ClinicData clinicData;
     private String doctorName;
     private String doctorTollFreeId;
     private String doctorResourceId;
     private String doctorComputerTagsIp;
     private Set<VoiceFilesData> voiceFilesDatas = new HashSet<VoiceFilesData>(0);

    public DoctorData() {
    }

    public DoctorData(ClinicData clinicData, String doctorName, String doctorTollFreeId, String doctorResourceId, String doctorComputerTagsIp, Set<VoiceFilesData> voiceFilesDatas) {
       this.clinicData = clinicData;
       this.doctorName = doctorName;
       this.doctorTollFreeId = doctorTollFreeId;
       this.doctorResourceId = doctorResourceId;
       this.doctorComputerTagsIp = doctorComputerTagsIp;
       this.voiceFilesDatas = voiceFilesDatas;
    }
   
    public Integer getDoctorId() {
        return this.doctorId;
    }
    
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
    public ClinicData getClinicdata() {
        return this.clinicData;
    }
    
    public void setClinicdata(ClinicData clinicData) {
        this.clinicData = clinicData;
    }
    public String getDoctorName() {
        return this.doctorName;
    }
    
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public String getDoctorTollFreeId() {
        return this.doctorTollFreeId;
    }
    
    public void setDoctorTollFreeId(String doctorTollFreeId) {
        this.doctorTollFreeId = doctorTollFreeId;
    }
    public String getDoctorResourceId() {
        return this.doctorResourceId;
    }
    
    public void setDoctorResourceId(String doctorResourceId) {
        this.doctorResourceId = doctorResourceId;
    }
    public String getDoctorComputerTagsIp() {
        return this.doctorComputerTagsIp;
    }
    
    public void setDoctorComputerTagsIp(String doctorComputerTagsIp) {
        this.doctorComputerTagsIp = doctorComputerTagsIp;
    }
    public Set<VoiceFilesData> getVoicefilesdatas() {
        return this.voiceFilesDatas;
    }
    
    public void setVoicefilesdatas(Set<VoiceFilesData> voiceFilesDatas) {
        this.voiceFilesDatas = voiceFilesDatas;
    }




}


