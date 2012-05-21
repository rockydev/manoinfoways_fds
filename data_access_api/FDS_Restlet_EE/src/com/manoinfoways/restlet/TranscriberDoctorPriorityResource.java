/**
 * 
 */
package com.manoinfoways.restlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import sun.security.krb5.internal.UDPClient;

import com.manoinfoways.ejb.TranscriberDoctorPriorityBean;
import com.manoinfoways.model.ClinicData;
import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.TranscriberData;
import com.manoinfoways.model.TranscriberDoctorPriority;
import com.manoinfoways.model.TranscriberTypeData;
import com.manoinfoways.util.TranscriberDoctorPriorityConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.DomReader;

/**
 * @author rockydev
 * 
 */
public class TranscriberDoctorPriorityResource extends ServerResource {

	private static TranscriberDoctorPriorityBean transcriberDoctorPriorityBean;
	private static XStream xmlConverter;

	private XmlWriter globalWriter = null;
	private String queueStatus = "0";

	/**
	 * 
	 */
	public TranscriberDoctorPriorityResource() {
		transcriberDoctorPriorityBean = new TranscriberDoctorPriorityBean();
		xmlConverter = new XStream(new DomDriver());
		xmlConverter.processAnnotations(TranscriberDoctorPriority.class);
		xmlConverter
				.registerConverter(new TranscriberDoctorPriorityConverter());
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

		String transcriberId = (String) getRequest().getAttributes().get(
				"transcriberId");

		String transcriberTypeId = (String) getRequest().getAttributes().get(
				"transcriberTypeId");

		String priority = (String) getRequest().getAttributes().get("priority");

		if (transcriberId != null && transcriberId != "") {
			xmlWriter.dataElement("status", "0");
			xmlConverter.alias("record", TranscriberDoctorPriority.class);
			xmlWriter.startElement("data");
			for (TranscriberDoctorPriority transcriberPriority : transcriberDoctorPriorityBean
					.getTranscriberPriorities(new Integer(transcriberId),
							(transcriberTypeId != null) ? new Integer(
									transcriberTypeId) : null,
							(priority != null) ? new Integer(priority) : null)) {
				xmlConverter.toXML(transcriberPriority, xmlWriter.getWriter());
			}
			xmlWriter.endElement("data");
		} else {
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", "Clinic Id cannot be empty!");
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Post("xml")
	public String addTranscriberPriority(DomRepresentation rep)
			throws IOException, SAXException, ParserConfigurationException {

		if (rep.getNode("//transaction") != null) {
			return handleMultipleRequests(rep);
		}

		// To handle delete operation (Since Restdatasource is sending as a
		// POST)
		if (rep.getNode("//request/operationType").getTextContent()
				.equalsIgnoreCase("remove")) {
			return deleteTranscriberPriority(rep);
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
		TranscriberDoctorPriority persistedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", TranscriberDoctorPriority.class);
				TranscriberDoctorPriority transcriberPriority = (TranscriberDoctorPriority) xmlConverter
						.unmarshal(new DomReader(dataNode));
				transcriberPriority.setTranscriberdata(new TranscriberData(
						Integer.parseInt((String) getRequest().getAttributes()
								.get("transcriberId"))));
				persistedInstance = transcriberDoctorPriorityBean
						.persist(transcriberPriority);
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", TranscriberDoctorPriority.class);
				xmlWriter.startElement("data");
				xmlConverter.toXML(persistedInstance, xmlWriter.getWriter());
				xmlWriter.endElement("data");

			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to add the Clinic data! Please retry!");
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
	public String updateTranscriberPriority(DomRepresentation rep)
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

		TranscriberDoctorPriority detachedInstance = null;
		try {
			if (oldData != null) {
				xmlConverter
						.alias("oldValues", TranscriberDoctorPriority.class);
				TranscriberDoctorPriority transcriberPriority = (TranscriberDoctorPriority) xmlConverter
						.unmarshal(new DomReader(oldData));

				transcriberPriority = updateTranscriberPriorityData(
						transcriberPriority, rep);
				transcriberPriority.setTranscriberdata(new TranscriberData(
						Integer.parseInt((String) getRequest().getAttributes()
								.get("transcriberId"))));
				detachedInstance = transcriberDoctorPriorityBean
						.attachDirty(transcriberPriority);
				xmlWriter.dataElement("status", "0");

				if (this.globalWriter != null) {
					xmlWriter.dataElement("queueStatus", queueStatus);
				}

				xmlConverter.alias("record", TranscriberDoctorPriority.class);
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
						"Unable to update the Clinic data! Please retry!");
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
				addTranscriberPriority(new DomRepresentation(
						MediaType.TEXT_XML, doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("update")) {
				updateTranscriberPriority(new DomRepresentation(
						MediaType.TEXT_XML, doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("remove")) {
				deleteTranscriberPriority(new DomRepresentation(
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
	 * Method to update the given TranscriberDoctorPriority object with the
	 * given values in the xml
	 * 
	 * @param doctor
	 * @param rep
	 * @return
	 */
	private TranscriberDoctorPriority updateTranscriberPriorityData(
			TranscriberDoctorPriority transcriberPriority, DomRepresentation rep) {

		if (rep.getNode("//request/data/transcriberId") != null) {
			transcriberPriority.setTranscriberdata(new TranscriberData(Integer
					.parseInt(rep.getNode("//request/data/transcriberId")
							.getTextContent())));
		}

		if (rep.getNode("//request/data/transcriberTypeId") != null) {
			transcriberPriority.setTranscribertypedata(new TranscriberTypeData(
					Integer.parseInt(rep.getNode(
							"//request/data/transcriberTypeId")
							.getTextContent())));
		}

		if (rep.getNode("//request/data/doctorId") != null) {
			transcriberPriority.setDoctordata(new DoctorData(Integer
					.parseInt(rep.getNode("//request/data/doctorId")
							.getTextContent())));
		}

		if (rep.getNode("//request/data/priority") != null) {
			transcriberPriority.setPriority(Integer.parseInt(rep.getNode(
					"//request/data/priority").getTextContent()));
		}

		return transcriberPriority;
	}

	@Delete("xml")
	public String deleteTranscriberPriority(DomRepresentation rep)
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

		TranscriberDoctorPriority deletedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", TranscriberDoctorPriority.class);

				deletedInstance = transcriberDoctorPriorityBean
						.deleteByPriorityId(new Integer(rep.getNode(
								"//request/data/priorityId").getTextContent()));

				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", TranscriberDoctorPriority.class);
				xmlWriter.startElement("data");
				xmlConverter.toXML(deletedInstance, xmlWriter.getWriter());
				xmlWriter.endElement("data");
			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to delete the Priority data! Please retry!");
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
