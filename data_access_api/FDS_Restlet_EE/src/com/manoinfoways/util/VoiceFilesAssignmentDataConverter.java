/**
 * 
 */
package com.manoinfoways.util;

import com.manoinfoways.model.InboundVoiceFilesData;
import com.manoinfoways.model.TranscriberData;
import com.manoinfoways.model.VoiceFilesAssignmentData;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author rockydev
 * 
 */
public class VoiceFilesAssignmentDataConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class clazz) {
		return VoiceFilesAssignmentData.class.isAssignableFrom(clazz);
	}

	@Override
	public void marshal(Object data, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		VoiceFilesAssignmentData fileAssignmentData = (VoiceFilesAssignmentData) data;

		writer.startNode("fileAssignmentId");
		writer.setValue(fileAssignmentData.getFileAssignmentId().toString());
		writer.endNode();
		writer.startNode("fileId");
		writer.setValue(fileAssignmentData.getInboundvoicefilesdata().getFileId().toString());
		writer.endNode();
		writer.startNode("fileName");
		writer.setValue(fileAssignmentData.getInboundvoicefilesdata().getFilePath());
		writer.endNode();
		writer.startNode("transcriberId");
		writer.setValue(fileAssignmentData.getTranscriberdataByTranscriberId()
				.getTranscriberId().toString());
		writer.endNode();
		writer.startNode("editorId");
		writer.setValue(fileAssignmentData.getTranscriberdataByEditorId()
				.getTranscriberId().toString());
		writer.endNode();
		writer.startNode("prooferId");
		writer.setValue(fileAssignmentData.getTranscriberdataByProoferId()
				.getTranscriberId().toString());
		writer.endNode();
		writer.startNode("QAId");
		writer.setValue(fileAssignmentData.getTranscriberdataByQaid()
				.getTranscriberId().toString());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		VoiceFilesAssignmentData fileAssignmentData = new VoiceFilesAssignmentData();

		reader.moveDown();
		if (reader.getNodeName().equalsIgnoreCase("fileAssignmentId")) {
			fileAssignmentData.setFileAssignmentId(Integer.parseInt(reader
					.getValue()));
			reader.moveUp();
			reader.moveDown();
		}	
		
		if (reader.getNodeName().equalsIgnoreCase("fileId")) {
			fileAssignmentData.setInboundvoicefilesdata(new InboundVoiceFilesData(Integer.parseInt(reader
					.getValue())));
			reader.moveUp();
			reader.moveDown();
		}	
		
		if (reader.getNodeName().equalsIgnoreCase("fileName")) {
//			fileAssignmentData.setFileAssignmentId(Integer.parseInt(reader
//					.getValue()));
			reader.moveUp();
			reader.moveDown();
		}	
		
		if (reader.getNodeName().equalsIgnoreCase("transcriberId")) {
			fileAssignmentData.setTranscriberdataByTranscriberId(new TranscriberData(Integer
					.parseInt(reader.getValue())));
			reader.moveUp();
			reader.moveDown();
		}
		
		if (reader.getNodeName().equalsIgnoreCase("editorId")) {
			fileAssignmentData.setTranscriberdataByEditorId(new TranscriberData(Integer
					.parseInt(reader.getValue())));
			reader.moveUp();
			reader.moveDown();
		}
		
		if (reader.getNodeName().equalsIgnoreCase("prooferId")) {
			fileAssignmentData.setTranscriberdataByProoferId(new TranscriberData(Integer
					.parseInt(reader.getValue())));
			reader.moveUp();
			reader.moveDown();
		}
		
		if (reader.getNodeName().equalsIgnoreCase("QAId")) {
			fileAssignmentData.setTranscriberdataByQaid(new TranscriberData(Integer
					.parseInt(reader.getValue())));
			reader.moveUp();
			reader.moveDown();
		}
		
		if (reader.hasMoreChildren())
		{
			System.out.println("Fatal error!");
		}

		return fileAssignmentData;
	}

}
