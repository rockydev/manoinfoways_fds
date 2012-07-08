/**
 * 
 */
package com.manoinfoways.util;

import com.manoinfoways.model.TranscriberData;
import com.manoinfoways.model.TranscriberLengthTimes;
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
public class TranscriberLengthTimesConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class clazz) {
		return TranscriberLengthTimes.class.isAssignableFrom(clazz);
	}

	@Override
	public void marshal(Object data, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		TranscriberLengthTimes transcriberPriority = (TranscriberLengthTimes) data;

		writer.startNode("transcriberLengthTimesId");
		writer.setValue(transcriberPriority.getTranscriberLengthTimesId()
				.toString());
		writer.endNode();
		writer.startNode("transcriberId");
		writer.setValue(transcriberPriority.getTranscriberdata()
				.getTranscriberId().toString());
		writer.endNode();
		writer.startNode("transcriberTypeId");
		writer.setValue(transcriberPriority.getTranscribertypedata()
				.getTranscriberTypeId().toString());
		writer.endNode();
		writer.startNode("dictationLength");
		writer.setValue(transcriberPriority.getDictationLength().toString());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		TranscriberLengthTimes transcriberPriority = new TranscriberLengthTimes();

		reader.moveDown();
		if (reader.getNodeName().equalsIgnoreCase("transcriberLengthTimesId")) {
			transcriberPriority.setTranscriberLengthTimesId((Integer
					.parseInt(reader.getValue())));
			reader.moveUp();
			reader.moveDown();
		}

		if (reader.getNodeName().equalsIgnoreCase("transcriberId")) {
			transcriberPriority.setTranscriberdata(new TranscriberData(Integer
					.parseInt(reader.getValue())));
			reader.moveUp();
			reader.moveDown();
		}

		if (reader.getNodeName().equalsIgnoreCase("transcriberTypeId")) {
			transcriberPriority.setTranscribertypedata(new TranscriberTypeData(
					Integer.parseInt(reader.getValue())));
			reader.moveUp();
			reader.moveDown();
		}

		if (reader.getNodeName().equalsIgnoreCase("dictationLengths")) {
			transcriberPriority.setDictationLength(Double.parseDouble(reader
					.getValue()));
		}

		if (reader.hasMoreChildren()) {
			System.out.println("Fatal error!");
		}

		return transcriberPriority;
	}

}
