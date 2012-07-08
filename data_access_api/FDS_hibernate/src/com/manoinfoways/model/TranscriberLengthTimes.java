package com.manoinfoways.model;

// Generated May 16, 2012 12:58:09 PM by Hibernate Tools 3.4.0.CR1

/**
 * TranscriberLengthTimes generated by hbm2java
 */
public class TranscriberLengthTimes implements java.io.Serializable {

	private Integer transcriberLengthTimesId;
	private TranscriberData transcriberdata;
	private TranscriberTypeData transcribertypedata;
	private Double dictationLength;

	public TranscriberLengthTimes() {
	}

	public TranscriberLengthTimes(TranscriberData transcriberdata,
			TranscriberTypeData transcribertypedata, Double dictationLength) {
		this.transcriberdata = transcriberdata;
		this.transcribertypedata = transcribertypedata;
		this.dictationLength = dictationLength;
	}

	public Integer getTranscriberLengthTimesId() {
		return this.transcriberLengthTimesId;
	}

	public void setTranscriberLengthTimesId(Integer transcriberLengthTimesId) {
		this.transcriberLengthTimesId = transcriberLengthTimesId;
	}

	public TranscriberData getTranscriberdata() {
		return this.transcriberdata;
	}

	public void setTranscriberdata(TranscriberData transcriberdata) {
		this.transcriberdata = transcriberdata;
	}

	public TranscriberTypeData getTranscribertypedata() {
		return this.transcribertypedata;
	}

	public void setTranscribertypedata(TranscriberTypeData transcribertypedata) {
		this.transcribertypedata = transcribertypedata;
	}

	public Double getDictationLength() {
		return this.dictationLength;
	}

	public void setDictationLength(Double dictationLength) {
		this.dictationLength = dictationLength;
	}

}
