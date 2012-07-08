/**
 * 
 */
package com.manoinfoways.restlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;

import org.restlet.ext.xml.DomRepresentation;
import org.restlet.ext.xml.XmlWriter;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.manoinfoways.ejb.ClinicConnectionDetailsBean;
import com.manoinfoways.ejb.ClinicDataBean;
import com.manoinfoways.model.ClinicConnectionDetails;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.DomReader;

/**
 * @author rockydev
 * 
 */
public class ClinicConnectionDetailsResource extends ServerResource {

	private static ClinicConnectionDetailsBean clinicConnectionDetailsBean;
	private static XStream xmlConverter;

	/**
	 * 
	 */
	public ClinicConnectionDetailsResource() {
		clinicConnectionDetailsBean = new ClinicConnectionDetailsBean();
		xmlConverter = new XStream(new DomDriver());
		xmlConverter.processAnnotations(ClinicConnectionDetails.class);
	}

	@Get("xml")
	/**
	 * Method handling fetch requests through HTTP GET
	 * @return XML formatted as per RestDataSource of SmartGWT (http://www.smartclient.com/smartgwt/javadoc/com/smartgwt/client/data/RestDataSource.html)
	 * @throws SAXException
	 * @throws IOException
	 */
	public String represent() {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		ObjectOutputStream out;

		try {
			xmlWriter.startDocument();

			xmlWriter.setDataFormat(true);

			xmlWriter.startElement("response");

			String clinicId = (String) getRequest().getAttributes().get("clinicId");

			if (clinicId != null && clinicId != "") {
				xmlWriter.dataElement("status", "0");
				try {
					xmlConverter.alias("record", ClinicConnectionDetails.class);
					out = xmlConverter.createObjectOutputStream(
							xmlWriter.getWriter(), "data");
					ClinicConnectionDetails connDetails = new ClinicDataBean()
							.findById(Integer.parseInt(clinicId)).getClinicconnectiondetails();
					out.writeObject(connDetails);
					out.close();
				} catch (IOException e) {
					System.out.println("Error: " + e.getLocalizedMessage());
					xmlWriter.dataElement("status", "-1");
					xmlWriter.dataElement("data",
							"Error with XML converter, please retry! ");
				}
			} else {
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data", "Clinic Id cannot be empty!");
			}

			xmlWriter.endElement("response");
			xmlWriter.endDocument();

			return xml.toString();

		} catch (SAXException s) {
			System.out.println("Error: " + s.getLocalizedMessage());
			return null;
		}
	}

	@Post("xml")
	public String addClinicConnectionDetails(DomRepresentation rep)
			throws IOException, SAXException {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		ObjectOutputStream out;

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");

		ClinicConnectionDetails persistedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", ClinicConnectionDetails.class);
//				persistedInstance = clinicConnectionDetailsBean
//						.persist((ClinicConnectionDetails) xmlConverter
//								.unmarshal(new DomReader(dataNode)));

				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", ClinicConnectionDetails.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
//				out.writeObject(persistedInstance);
				out.close();

			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter
						.dataElement("data",
								"Unable to add the Clinic Connection details! Please retry!");
			}
		} catch (Exception ex) {
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", ex.getLocalizedMessage());
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Put("xml")
	public String updateClinicConnectionDetails(DomRepresentation rep)
			throws IOException, SAXException {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		ObjectOutputStream out;

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");

		ClinicConnectionDetails detachedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", ClinicConnectionDetails.class);
				detachedInstance = clinicConnectionDetailsBean
						.merge((ClinicConnectionDetails) xmlConverter
								.unmarshal(new DomReader(dataNode)));

				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", ClinicConnectionDetails.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
				out.writeObject(detachedInstance);
				out.close();
			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter
						.dataElement("data",
								"Unable to update the Clinic Connection details! Please retry!");
			}
		} catch (Exception ex) {
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", ex.getLocalizedMessage());
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Delete("xml")
	public String deleteClinicConnectionDetails(DomRepresentation rep)
			throws IOException, SAXException {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");

		ClinicConnectionDetails deletedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", ClinicConnectionDetails.class);
//				deletedInstance = clinicConnectionDetailsBean
//						.delete((ClinicConnectionDetails) xmlConverter
//								.unmarshal(new DomReader(dataNode)));

				xmlWriter.dataElement("status", "0");
				xmlWriter.startElement("data");
				xmlWriter.startElement("record");
//				xmlWriter.dataElement("clinicConnectionId", new Integer(
//						deletedInstance.getClinicConnectionId()).toString());
				xmlWriter.endElement("record");
				xmlWriter.endElement("data");

			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter
						.dataElement("data",
								"Unable to delete the Clinic Connections details! Please retry!");
			}
		} catch (Exception ex) {
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", ex.getLocalizedMessage());
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

}
