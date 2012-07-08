/**
 * 
 */
package com.manoinfoways.restlet;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.restlet.data.MediaType;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.ext.xml.XmlWriter;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.manoinfoways.ejb.TranscriberLengthTimesBean;
import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.TranscriberData;
import com.manoinfoways.model.TranscriberLengthTimes;
import com.manoinfoways.model.TranscriberTypeData;
import com.manoinfoways.util.DateConverter;
import com.manoinfoways.util.TimeConverter;
import com.manoinfoways.util.TranscriberLengthTimesConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.DomReader;

/**
 * @author rockydev
 * 
 */
public class TranscriberLengthTimesResource extends ServerResource {

	private static TranscriberLengthTimesBean transcriberLengthTimesBean;
	private static XStream xmlConverter;

	private XmlWriter globalWriter = null;
	private String queueStatus = "0";

	/**
	 * 
	 */
	public TranscriberLengthTimesResource() {
		transcriberLengthTimesBean = new TranscriberLengthTimesBean();
		xmlConverter = new XStream(new DomDriver());
		xmlConverter.processAnnotations(TranscriberLengthTimes.class);
		xmlConverter.registerConverter(new TranscriberLengthTimesConverter());
	}

	@Get("xml")
	/**
	 * Method handling fetch requests through HTTP GET
	 * @return XML formatted as per RestDataSource of SmartGWT (http://www.smartclient.com/smartgwt/javadoc/com/smartgwt/client/data/RestDataSource.html)
	 * @throws SAXException
	 * @throws IOException
	 */
	public String represent() throws SAXException, IOException {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		xmlWriter.dataElement("status", "0");
		xmlConverter.alias("record", TranscriberLengthTimes.class);
		xmlWriter.startElement("data");
		xmlConverter.aliasSystemAttribute(null, "class");
		xmlConverter.aliasSystemAttribute(null, "resolves-to");

		Integer transcriberId = Integer.parseInt((String) getRequest()
				.getAttributes().get("transcriberId"));

		for (TranscriberLengthTimes transcriberLength : transcriberLengthTimesBean
				.getTranscriberLengths(transcriberId)) {
			xmlConverter.toXML(transcriberLength, xmlWriter.getWriter());
		}
		xmlWriter.endElement("data");
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Post("xml")
	public String addTranscriberLength(DomRepresentation rep)
			throws IOException, SAXException, ParserConfigurationException {

		if (rep.getNode("//transaction") != null) {
			return handleMultipleRequests(rep);
		}

		// To handle delete operation (Since Restdatasource is sending as a
		// POST)
		if (rep.getNode("//request/operationType").getTextContent()
				.equalsIgnoreCase("remove")) {
			return deleteTranscriberLength(rep);
		}

		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = null;

		if (this.globalWriter == null) {
			xmlWriter = new XmlWriter(xml);

			xmlWriter.startDocument();
			xmlWriter.setDataFormat(true);
		} else {
			xmlWriter = this.globalWriter;
		}

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");
		TranscriberLengthTimes persistedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", TranscriberData.class);
				TranscriberLengthTimes transcriber = (TranscriberLengthTimes) xmlConverter
						.unmarshal(new DomReader(dataNode));

				transcriber.setTranscriberdata(new TranscriberData(
						Integer.parseInt((String) getRequest().getAttributes()
								.get("transcriberId"))));

				persistedInstance = transcriberLengthTimesBean
						.persist(transcriber);
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", TranscriberData.class);
				xmlWriter.startElement("data");
				xmlConverter.toXML(persistedInstance, xmlWriter.getWriter());
				xmlWriter.endElement("data");
			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to add the Transcriber data! Please retry!");
			}
		} catch (Exception ex) {
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", ex.getLocalizedMessage());
		}

		xmlWriter.endElement("response");

		if (this.globalWriter == null) {
			xmlWriter.endDocument();
			return xml.toString();
		} else {
			return "";
		}
	}

	@Put("xml")
	public String updateTranscriberLength(DomRepresentation rep)
			throws IOException, SAXException, ParserConfigurationException {
		if (rep.getNode("//transaction") != null) {
			return handleMultipleRequests(rep);
		}

		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = null;

		if (this.globalWriter == null) {
			xmlWriter = new XmlWriter(xml);

			xmlWriter.startDocument();
			xmlWriter.setDataFormat(true);
		} else {
			xmlWriter = this.globalWriter;
		}

		xmlWriter.startElement("response");

		Element oldData = (Element) rep.getNode("//request/oldValues");

		TranscriberLengthTimes detachedInstance = null;
		try {
			if (oldData != null) {
				xmlConverter.alias("oldValues", TranscriberData.class);
				TranscriberLengthTimes transcriber = (TranscriberLengthTimes) xmlConverter
						.unmarshal(new DomReader(oldData));
				transcriber = updateTranscriberData(transcriber, rep);

				detachedInstance = transcriberLengthTimesBean
						.update(transcriber);
				xmlWriter.dataElement("status", "0");

				if (this.globalWriter != null) {
					xmlWriter.dataElement("queueStatus", queueStatus);
				}

				xmlConverter.alias("record", TranscriberData.class);
				xmlWriter.startElement("data");
				xmlConverter.toXML(detachedInstance, xmlWriter.getWriter());
				xmlWriter.endElement("data");
			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");

				if (this.globalWriter != null) {
					queueStatus = "-1";
					xmlWriter.dataElement("queueStatus", queueStatus);
				}

				xmlWriter.dataElement("data",
						"Unable to update the Transcriber data! Please retry!");
			}
		} catch (Exception ex) {
			xmlWriter.dataElement("status", "-2");
			xmlWriter.dataElement("data", ex.getMessage());
			ex.printStackTrace();
		}

		xmlWriter.endElement("response");

		if (this.globalWriter == null) {
			xmlWriter.endDocument();
			return xml.toString();
		} else {
			return "";
		}

	}

	private String handleMultipleRequests(DomRepresentation rep)
			throws IOException, SAXException, ParserConfigurationException {

		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		// Setting the global writer, for generating queued responses
		this.globalWriter = xmlWriter;

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("responses");

		for (int index = 1; index <= rep.getNodes(
				"//transaction/operations/request").getLength(); index++) {
			System.out.println("Index: "
					+ new Integer(index).toString()
					+ " => "
					+ rep.getNode(
							"//transaction/operations/request["
									+ new Integer(index).toString()
									+ "]/operationType").getTextContent());

			String baseXpath = "//transaction/operations/request["
					+ new Integer(index).toString() + "]";
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			doc.appendChild(doc.importNode(rep.getNode(baseXpath), true));
			if (rep.getNode(baseXpath + "/operationType").getTextContent()
					.equalsIgnoreCase("add")) {
				addTranscriberLength(new DomRepresentation(MediaType.TEXT_XML,
						doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("update")) {
				updateTranscriberLength(new DomRepresentation(
						MediaType.TEXT_XML, doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("remove")) {
				deleteTranscriberLength(new DomRepresentation(
						MediaType.TEXT_XML, doc));
			}
		}

		xmlWriter.endElement("responses");
		xmlWriter.endDocument();

		// Setting the global writer back to null
		this.globalWriter = null;

		return xml.toString();

	}

	/**
	 * Method to update the given {@link TranscriberData} object with the given
	 * values in the xml
	 * 
	 * @param transcriber
	 * @param rep
	 * @return
	 * @throws ParseException
	 * @throws DOMException
	 */
	private TranscriberLengthTimes updateTranscriberData(
			TranscriberLengthTimes transcriber, DomRepresentation rep)
			throws DOMException, ParseException {
		 if (rep.getNode("//request/data/transcriberTypeId") != null) {
		 transcriber.setTranscribertypedata(new TranscriberTypeData(Integer.parseInt(rep.getNode(
		 "//request/data/transcriberTypeId").getTextContent())));
		 }
		
		 if (rep.getNode("//request/data/dictationLength") != null) {
		 transcriber.setDictationLength(Double.parseDouble((rep.getNode(
				 "//request/data/dictationLength").getTextContent())));
		 }
		
		return null;
	}

	@Delete("xml")
	public String deleteTranscriberLength(DomRepresentation rep)
			throws IOException, SAXException {

		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = null;

		if (this.globalWriter == null) {
			xmlWriter = new XmlWriter(xml);

			xmlWriter.startDocument();
			xmlWriter.setDataFormat(true);
		} else {
			xmlWriter = this.globalWriter;
		}

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");

		TranscriberLengthTimes deletedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", TranscriberData.class);

				deletedInstance = transcriberLengthTimesBean
						.deleteTranscriberLengthTimesById(new Integer(rep
								.getNode("//request/data/transcriberId")
								.getTextContent()));

				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", TranscriberData.class);
				xmlWriter.startElement("data");
				xmlWriter.dataElement("transcriberId", deletedInstance
						.getTranscriberdata().toString());
				// xmlConverter.toXML(deletedInstance, xmlWriter.getWriter());
				xmlWriter.endElement("data");
			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to delete the Transcriber data! Please retry!");
			}
		} catch (Exception ex) {
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", ex.getLocalizedMessage());
			ex.printStackTrace();
		}

		xmlWriter.endElement("response");

		if (this.globalWriter == null) {
			xmlWriter.endDocument();
			return xml.toString();
		} else {
			return "";
		}
	}
}
