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
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.manoinfoways.ejb.VoiceFilesAssignmentDataBean;
import com.manoinfoways.model.VoiceFilesAssignmentData;
import com.manoinfoways.util.VoiceFilesAssignmentDataConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.DomReader;

/**
 * @author rockydev
 * 
 */
public class VoiceFileAssignmentDataResource extends ServerResource {

	private static VoiceFilesAssignmentDataBean voiceFileAssignmentDataBean;
	private static XStream xmlConverter;

	private XmlWriter globalWriter = null;
	private String queueStatus = "0";

	/**
	 * 
	 */
	public VoiceFileAssignmentDataResource() {
		voiceFileAssignmentDataBean = new VoiceFilesAssignmentDataBean();
		xmlConverter = new XStream(new DomDriver());
		xmlConverter.processAnnotations(VoiceFilesAssignmentData.class);
		xmlConverter.registerConverter(new VoiceFilesAssignmentDataConverter());

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

		ObjectOutputStream out;

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		xmlWriter.dataElement("status", "0");
		xmlConverter.alias("record", VoiceFilesAssignmentData.class);
		out = xmlConverter.createObjectOutputStream(xmlWriter.getWriter(),
				"data");
		for (VoiceFilesAssignmentData fileAssignment : voiceFileAssignmentDataBean
				.getVoiceFileAssignments()) {
			out.writeObject(fileAssignment);
		}
		out.close();
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	
	@Put("xml")
	public String updateVoiceFileAssignment(DomRepresentation rep) throws IOException,
			SAXException, ParserConfigurationException {
		if (rep.getNode("//transaction") != null) {
			return handleMultipleRequests(rep);
		}

		StringWriter xml = new StringWriter();
		ObjectOutputStream out;
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

		VoiceFilesAssignmentData detachedInstance = null;
		try {
			if (oldData != null) {
				xmlConverter.alias("oldValues", VoiceFilesAssignmentData.class);
				VoiceFilesAssignmentData fileAssignment = (VoiceFilesAssignmentData) xmlConverter
						.unmarshal(new DomReader(oldData));
				fileAssignment = updateVoiceFilesAssignmentData(fileAssignment, rep);

				detachedInstance = voiceFileAssignmentDataBean.update(fileAssignment);
				xmlWriter.dataElement("status", "0");

				if (this.globalWriter != null) {
					xmlWriter.dataElement("queueStatus", queueStatus);
				}

				xmlConverter.alias("record", VoiceFilesAssignmentData.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
				out.writeObject(detachedInstance);
				out.close();
			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");

				if (this.globalWriter != null) {
					queueStatus = "-1";
					xmlWriter.dataElement("queueStatus", queueStatus);
				}

				xmlWriter.dataElement("data",
						"Unable to update the File Assignment data! Please retry!");
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
			if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("update")) {
				updateVoiceFileAssignment(new DomRepresentation(MediaType.TEXT_XML, doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("remove")) {
				deleteVoiceFileAssignment(new DomRepresentation(MediaType.TEXT_XML, doc));
			}
		}

		xmlWriter.endElement("responses");
		xmlWriter.endDocument();

		// Setting the global writer back to null
		this.globalWriter = null;

		return xml.toString();

	}

	/**
	 * Method to update the given VoiceFilesAssignmentData object with the given
	 * values in the xml
	 * 
	 * @param doctor
	 * @param rep
	 * @return
	 */
	private VoiceFilesAssignmentData updateVoiceFilesAssignmentData(
			VoiceFilesAssignmentData fileAssignment, DomRepresentation rep) {
//		if (rep.getNode("//request/data/doctorAbbr") != null) {
//			doctor.setDoctorAbbr(rep.getNode("//request/data/doctorAbbr")
//					.getTextContent());
//		}
//
//		if (rep.getNode("//request/data/doctorComputerTagsIp") != null) {
//			doctor.setDoctorComputerTagsIp(rep.getNode(
//					"//request/data/doctorComputerTagsIp").getTextContent());
//		}
//
//		if (rep.getNode("//request/data/doctorName") != null) {
//			doctor.setDoctorName(rep.getNode("//request/data/doctorName")
//					.getTextContent());
//		}
//
//		if (rep.getNode("//request/data/doctorTollFreeId") != null) {
//			doctor.setDoctorTollFreeId(rep.getNode(
//					"//request/data/doctorTollFreeId").getTextContent());
//		}
//
//		if (rep.getNode("//request/data/doctorResourceId") != null) {
//			doctor.setDoctorResourceId(rep.getNode(
//					"//request/data/doctorResourceId").getTextContent());
//		}

		return fileAssignment;
	}

	@Delete("xml")
	public String deleteVoiceFileAssignment(DomRepresentation rep) throws IOException,
			SAXException {

		StringWriter xml = new StringWriter();
		ObjectOutputStream out;
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

		VoiceFilesAssignmentData deletedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", VoiceFilesAssignmentData.class);

				deletedInstance = voiceFileAssignmentDataBean
						.deleteVoiceFilesAssignmentDataById(new Integer(rep
								.getNode("//request/data/fileAssignmentId")
								.getTextContent()));

				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", VoiceFilesAssignmentData.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
				out.writeObject(deletedInstance);
				out.close();
			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to delete the Doctor data! Please retry!");
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
