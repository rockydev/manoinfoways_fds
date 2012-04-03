/**
 * 
 */
package com.manoinfoways.restlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.manoinfoways.ejb.DoctorDataBean;
import com.manoinfoways.model.ClinicData;
import com.manoinfoways.model.DoctorData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.DomReader;

/**
 * @author rockydev
 * 
 */
public class DoctorDataResource extends ServerResource {

	private static DoctorDataBean doctorDataBean;
	private static XStream xmlConverter;
	
	private XmlWriter globalWriter = null;

	/**
	 * 
	 */
	public DoctorDataResource() {
		doctorDataBean = new DoctorDataBean();
		xmlConverter = new XStream(new DomDriver());
		xmlConverter.processAnnotations(DoctorData.class);
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

		String clinicId = (String) getRequest().getAttributes().get("clinicId");

		if (clinicId != null && clinicId != "") {
			xmlWriter.dataElement("status", "0");
			xmlConverter.alias("record", DoctorData.class);
			out = xmlConverter.createObjectOutputStream(xmlWriter.getWriter(),
					"data");
			for (DoctorData doctor : doctorDataBean.getClinicDoctors(Integer
					.parseInt(clinicId))) {
				out.writeObject(doctor);
			}
			out.close();
		} else {
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", "Clinic Id cannot be empty!");
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Post("xml")
	public String addDoctor(DomRepresentation rep) throws IOException,
			SAXException, ParserConfigurationException {
		
		if (rep.getNode("//transaction") != null) {
			return handleMultipleRequests(rep);
		}
		
		// To handle delete operation (Since Restdatasource is sending as a POST
		if (rep.getNode("//request/operationType").getTextContent()
				.equalsIgnoreCase("remove")) {
			return deleteDoctor(rep);
		}

		StringWriter xml = new StringWriter();
		ObjectOutputStream out;
		XmlWriter xmlWriter = null;
		
		if (this.globalWriter == null)
		{
			xmlWriter = new XmlWriter(xml);

			xmlWriter.startDocument();
			xmlWriter.setDataFormat(true);
		}
		else
		{
			xmlWriter = this.globalWriter;
		}

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");
		DoctorData persistedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", DoctorData.class);
				DoctorData doctor = (DoctorData) xmlConverter
						.unmarshal(new DomReader(dataNode));
				doctor.setClinicdata(new ClinicData(new Integer(
						(String) getRequest().getAttributes().get("clinicId"))));

				// Ensuring the doctorAbbr is always in upper case
				doctor.setDoctorAbbr(doctor.getDoctorAbbr().toUpperCase());

				persistedInstance = doctorDataBean.persist(doctor);
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", DoctorData.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
				out.writeObject(persistedInstance);
				out.close();

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
		
		if (this.globalWriter == null)
		{
			xmlWriter.endDocument();
			return xml.toString();
		}
		else
		{
			return "";
		}
	}

	@Put("xml")
	public String updateDoctor(DomRepresentation rep) throws IOException,
			SAXException, ParserConfigurationException {
		if (rep.getNode("//transaction") != null) {
			return handleMultipleRequests(rep);
		}
		
		StringWriter xml = new StringWriter();
		ObjectOutputStream out;
		XmlWriter xmlWriter = null;
		
		if (this.globalWriter == null)
		{
			xmlWriter = new XmlWriter(xml);

			xmlWriter.startDocument();
			xmlWriter.setDataFormat(true);
		}
		else
		{
			xmlWriter = this.globalWriter;
		}

		xmlWriter.startElement("response");

		Element oldData = (Element) rep.getNode("//request/oldValues");

		DoctorData detachedInstance = null;
		try {
			if (oldData != null) {
				xmlConverter.alias("oldValues", DoctorData.class);
				DoctorData doctor = (DoctorData) xmlConverter
						.unmarshal(new DomReader(oldData));
				doctor.setClinicdata(new ClinicData(new Integer(
						(String) getRequest().getAttributes().get("clinicId"))));
				doctor = updateDoctorData(doctor, rep);

				// Ensuring the doctorAbbr is always in upper case
				doctor.setDoctorAbbr(doctor.getDoctorAbbr().toUpperCase());

				detachedInstance = doctorDataBean.update(doctor);
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", DoctorData.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
				out.writeObject(detachedInstance);
				out.close();
			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to update the Clinic data! Please retry!");
			}
		} catch (Exception ex) {
			xmlWriter.dataElement("status", "-2");
			xmlWriter.dataElement("data", ex.getMessage());
			ex.printStackTrace();
		}
		
		xmlWriter.endElement("response");
		
		if (this.globalWriter == null)
		{
			xmlWriter.endDocument();
			return xml.toString();
		}
		else
		{
			return "";
		}

	}
	
	private String handleMultipleRequests(DomRepresentation rep)
			throws IOException, SAXException, ParserConfigurationException {

		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);
		
		//Setting the global writer, for generating queued responses
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
				addDoctor(new DomRepresentation(
						MediaType.TEXT_XML, doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("update")) {
				updateDoctor(new DomRepresentation(
						MediaType.TEXT_XML, doc));
			} else if (rep.getNode(baseXpath + "/operationType")
					.getTextContent().equalsIgnoreCase("remove")) {
				deleteDoctor(new DomRepresentation(
						MediaType.TEXT_XML, doc));
			}
		}
		
		xmlWriter.endElement("responses");
		xmlWriter.endDocument();
		
		//Setting the global writer back to null
		this.globalWriter = null;
		
		return xml.toString();
		
	}

	/**
	 * Method to update the given DoctorData object with the given values in the
	 * xml
	 * 
	 * @param doctor
	 * @param rep
	 * @return
	 */
	private DoctorData updateDoctorData(DoctorData doctor, DomRepresentation rep) {
		if (rep.getNode("//request/data/doctorAbbr") != null) {
			doctor.setDoctorAbbr(rep.getNode("//request/data/doctorAbbr")
					.getTextContent());
		}

		if (rep.getNode("//request/data/doctorComputerTagsIp") != null) {
			doctor.setDoctorComputerTagsIp(rep.getNode(
					"//request/data/doctorComputerTagsIp").getTextContent());
		}

		if (rep.getNode("//request/data/doctorName") != null) {
			doctor.setDoctorName(rep.getNode("//request/data/doctorName")
					.getTextContent());
		}

		if (rep.getNode("//request/data/doctorTollFreeId") != null) {
			doctor.setDoctorTollFreeId(rep.getNode(
					"//request/data/doctorTollFreeId").getTextContent());
		}

		if (rep.getNode("//request/data/doctorResourceId") != null) {
			doctor.setDoctorResourceId(rep.getNode(
					"//request/data/doctorResourceId").getTextContent());
		}

		return doctor;
	}

	@Delete("xml")
	public String deleteDoctor(DomRepresentation rep) throws IOException,
			SAXException {
		
		StringWriter xml = new StringWriter();
		ObjectOutputStream out;
		XmlWriter xmlWriter = null;
		
		if (this.globalWriter == null)
		{
			xmlWriter = new XmlWriter(xml);

			xmlWriter.startDocument();
			xmlWriter.setDataFormat(true);
		}
		else
		{
			xmlWriter = this.globalWriter;
		}

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");

		DoctorData deletedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", DoctorData.class);

				deletedInstance = doctorDataBean
						.deleteDoctorDataById(new Integer(rep.getNode(
								"//request/data/doctorId").getTextContent()));

				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", DoctorData.class);
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
		
		if (this.globalWriter == null)
		{
			xmlWriter.endDocument();
			return xml.toString();
		}
		else
		{
			return "";
		}
	}

	public String getDoctorAbbrs(String clinicId) {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		ObjectOutputStream out;

		try {
			xmlWriter.startDocument();

			xmlWriter.setDataFormat(true);

			xmlWriter.startElement("response");
			if (clinicId != null && clinicId != "") {
				try {
					xmlWriter.dataElement("status", "0");
					xmlConverter.alias("record", DoctorData.class);
					out = xmlConverter.createObjectOutputStream(
							xmlWriter.getWriter(), "data");

					for (DoctorData clinicData : (List<DoctorData>) doctorDataBean
							.getDoctorAbbrs(Integer.parseInt(clinicId))) {
						out.writeObject(clinicData);
					}
					out.close();
				} catch (Exception e) {
					xmlWriter.dataElement("status", "-1");
					xmlWriter.dataElement("data", e.getLocalizedMessage());
				}
			} else {
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data", "Clinic Id cannot be empty!");
			}
			xmlWriter.endElement("response");
			xmlWriter.endDocument();

			return xml.toString();
		} catch (SAXException e1) {
			return "";
		}
	}
}
