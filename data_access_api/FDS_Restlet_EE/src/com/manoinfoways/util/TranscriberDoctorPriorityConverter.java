/**
 * 
 */
package com.manoinfoways.util;

import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.TranscriberData;
import com.manoinfoways.model.TranscriberDoctorPriority;
import com.manoinfoways.model.TranscriberTypeData;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author rockydev
 * 
 */
public class TranscriberDoctorPriorityConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class clazz) {
		return TranscriberDoctorPriority.class.isAssignableFrom(clazz);
	}

	@Override
	public void marshal(Object data, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		TranscriberDoctorPriority transcriberPriority = (TranscriberDoctorPriority) data;

		writer.startNode("priorityId");
		writer.setValue(transcriberPriority.getPriorityId().toString());
		writer.endNode();
		writer.startNode("transcriberId");
		writer.setValue(transcriberPriority.getTranscriberdata()
				.getTranscriberId().toString());
		writer.endNode();
		writer.startNode("transcriberTypeId");
		writer.setValue(transcriberPriority.getTranscribertypedata()
				.getTranscriberTypeId().toString());
		writer.endNode();
		writer.startNode("doctorId");
		writer.setValue(transcriberPriority.getDoctordata().getDoctorId()
				.toString());
		writer.endNode();
		writer.startNode("priority");
		writer.setValue(transcriberPriority.getPriority().toString());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		TranscriberDoctorPriority transcriberPriority = new TranscriberDoctorPriority();

		reader.moveDown();
		if (reader.getNodeName().equalsIgnoreCase("priorityId")) {
			transcriberPriority.setPriorityId(Integer.parseInt(reader
					.getValue()));
			reader.moveUp();
			reader.moveDown();
		}	
		
		if (reader.getNodeName().equalsIgnoreCase("transcriberId")) {
			transcriberPriority.setTranscriberdata(new TranscriberData(Integer
					.parseInt(reader.getValue())));
			reader.moveUp();
			reader.moveDown();
		}
		
		transcriberPriority.setDoctordata(new DoctorData(Integer
				.parseInt(reader.getValue())));
		reader.moveUp();
		reader.moveDown();
		
		transcriberPriority.setTranscribertypedata(new TranscriberTypeData(
				Integer.parseInt(reader.getValue())));
		reader.moveUp();
		reader.moveDown();
		
		transcriberPriority.setPriority(Integer.parseInt(reader
				.getValue()));
		
		if (reader.hasMoreChildren())
		{
			System.out.println("Fatal error!");
		}

		return transcriberPriority;
	}

}
