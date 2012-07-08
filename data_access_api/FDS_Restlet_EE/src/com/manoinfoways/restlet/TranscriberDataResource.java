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

import com.manoinfoways.ejb.TranscriberDataBean;
import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.TranscriberData;
import com.manoinfoways.util.DateConverter;
import com.manoinfoways.util.TimeConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.DomReader;

/**
 * @author rockydev
 * 
 */
public class TranscriberDataResource extends ServerResource {

	private static TranscriberDataBean transcriberDataBean;
	private static XStream xmlConverter;

	private XmlWriter globalWriter = null;
	private String queueStatus = "0";

	/**
	 * 
	 */
	public TranscriberDataResource() {
		transcriberDataBean = new TranscriberDataBean();
		xmlConverter = new XStream(new DomDriver());
		xmlConverter.processAnnotations(TranscriberData.class);
		xmlConverter.registerConverter(new DateConverter());
		xmlConverter.registerConverter(new TimeConverter());
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
		xmlConverter.alias("record", TranscriberData.class);
		xmlWriter.startElement("data");
		xmlConverter.aliasSystemAttribute(null, "class");
		xmlConverter.aliasSystemAttribute(null, "resolves-to");
		for (TranscriberData transcriber : transcriberDataBean
				.getAllTranscribers()) {
			xmlConverter.toXML(transcriber, xmlWriter.getWriter());
		}
		xmlWriter.endElement("data");
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Post("xml")
	public String addTranscriber(DomRepresentation rep) throws IOException,
			SAXException, ParserConfigurationException {

		if (rep.getNode("//transaction") != null) {
			return handleMultipleRequests(rep);
		}

		// To handle delete operation (Since Restdatasource is sending as a
		// POST)
		if (rep.getNode("//request/operationType").getTextContent()
				.equalsIgnoreCase("remove")) {
			return deleteTranscriber(rep);
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
		TranscriberData persistedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", TranscriberData.class);
				TranscriberData transcriber = (TranscriberData) xmlConverter
						.unmarshal(new DomReader(dataNode));

				persistedInstance = transcriberDataBean.persist(transcriber);
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
	public String updateTranscriber(DomRepresentation rep) throws IOException,
			SAXException, ParserConfigurationException {
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

		TranscriberData detachedInstance = null;
		try {
			if (oldData != null) {
				xmlConverter.alias("oldValues", TranscriberData.class);
				TranscriberData transcriber = (TranscriberData) xmlConverter
						.unmarshal(new DomReader(oldData));
				transcriber = updateTranscriberData(transcriber, rep);

				detachedInstance = transcriberDataBean.update(transcriber);
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
				addTranscriber(new DomRepresentation(MediaType.TEXT_XML, doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("update")) {
				updateTranscriber(new DomRepresentation(MediaType.TEXT_XML, doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("remove")) {
				deleteTranscriber(new DomRepresentation(MediaType.TEXT_XML, doc));
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
	private TranscriberData updateTranscriberData(TranscriberData transcriber,
			DomRepresentation rep) throws DOMException, ParseException {
		if (rep.getNode("//request/data/transcriberTypeId") != null) {
			transcriber.setTranscriberTypeId(new Integer(rep.getNode(
					"//request/data/transcriberTypeId").getTextContent()));
		}

		if (rep.getNode("//request/data/userName") != null) {
			transcriber.setUserName(rep.getNode("//request/data/userName")
					.getTextContent());
		}

		if (rep.getNode("//request/data/password") != null) {
			transcriber.setPassword(rep.getNode("//request/data/password")
					.getTextContent());
		}

		if (rep.getNode("//request/data/transcriberName") != null) {
			transcriber.setTranscriberName(rep.getNode(
					"//request/data/transcriberName").getTextContent());
		}

		if (rep.getNode("//request/data/dateofJoining") != null) {
			transcriber.setDateofJoining(new SimpleDateFormat("yyyy-MM-dd")
					.parse(rep.getNode("//request/data/dateofJoining")
							.getTextContent()));
		}

		if (rep.getNode("//request/data/transcriberAddress") != null) {
			transcriber.setTranscriberAddress(rep.getNode(
					"//request/data/transcriberAddress").getTextContent());
		}

		if (rep.getNode("//request/data/transcriberPermanentAddress") != null) {
			transcriber.setTranscriberPermanentAddress(rep.getNode(
					"//request/data/transcriberPermanentAddress")
					.getTextContent());
		}

		if (rep.getNode("//request/data/transcriberPhoneNumber") != null) {
			transcriber.setTranscriberPhoneNumber(rep.getNode(
					"//request/data/transcriberPhoneNumber").getTextContent());
		}

		if (rep.getNode("//request/data/transcriberMobile") != null) {
			transcriber.setTranscriberMobile(rep.getNode(
					"//request/data/transcriberMobile").getTextContent());
		}

		if (rep.getNode("//request/data/transcriberEmail") != null) {
			transcriber.setTranscriberEmail(rep.getNode(
					"//request/data/transcriberEmail").getTextContent());
		}

		if (rep.getNode("//request/data/transcriberQualification") != null) {
			transcriber
					.setTranscriberQualification(rep.getNode(
							"//request/data/transcriberQualification")
							.getTextContent());
		}

		if (rep.getNode("//request/data/transcriberExperience") != null) {
			transcriber.setTranscriberExperience(rep.getNode(
					"//request/data/transcriberExperience").getTextContent());
		}

		if (rep.getNode("//request/data/transcriberDob") != null) {
			transcriber.setTranscriberDob(new SimpleDateFormat("yyyy-MM-dd")
					.parse(rep.getNode("//request/data/transcriberDob")
							.getTextContent()));
		}

		if (rep.getNode("//request/data/transcriberLoginTime") != null) {
			transcriber.setTranscriberLoginTime(new Time(new SimpleDateFormat(
					"HH:mm").parse(
					rep.getNode("//request/data/transcriberLoginTime")
							.getTextContent()).getTime()));
		}
		return transcriber;
	}

	@Delete("xml")
	public String deleteTranscriber(DomRepresentation rep) throws IOException,
			SAXException {

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

		TranscriberData deletedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", TranscriberData.class);

				deletedInstance = transcriberDataBean
						.deleteTranscriberDataById(new Integer(rep.getNode(
								"//request/data/transcriberId")
								.getTextContent()));

				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", TranscriberData.class);
				xmlWriter.startElement("data");
				xmlWriter.dataElement("transcriberId", deletedInstance
						.getTranscriberId().toString());
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

	public String getAllUserNames() {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		try {
			xmlWriter.startDocument();

			xmlWriter.setDataFormat(true);

			xmlWriter.startElement("response");
			try {
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", TranscriberData.class);
				xmlWriter.startElement("data");

				for (TranscriberData transcriberData : (List<TranscriberData>) transcriberDataBean
						.getAllUserNames()) {
					xmlConverter.toXML(transcriberData, xmlWriter.getWriter());
				}
				xmlWriter.endElement("data");
			} catch (Exception e) {
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data", e.getLocalizedMessage());
			}
			xmlWriter.endElement("response");
			xmlWriter.endDocument();

			return xml.toString();
		} catch (SAXException e1) {
			return "";
		}
	}
}
