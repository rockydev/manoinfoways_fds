/**
 * 
 */
package com.manoinfoways.restlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.ext.xml.XmlWriter;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.manoinfoways.ejb.ClinicDataBean;
import com.manoinfoways.model.ClinicConnectionDetails;
import com.manoinfoways.model.ClinicData;
import com.manoinfoways.model.ClinicMetadata;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.DomReader;

/**
 * @author rockydev
 * 
 */
public class ClinicDataResource extends ServerResource {

	private static final int CLINIC_DATA = 0;
	private static final int CONNECTION_DETAILS = 1;
	private static final int METADATA = 2;

	private static ClinicDataBean clinicDataBean;
	private static XStream xmlConverter;

	/**
	 * 
	 */
	public ClinicDataResource() {
		clinicDataBean = new ClinicDataBean();
		xmlConverter = new XStream(new DomDriver());
		xmlConverter.processAnnotations(ClinicData.class);
	}

	@Get("xml")
	/**
	 * Method handling fetch requests through HTTP GET
	 * @return XML formatted as per RestDataSource of SmartGWT (http://www.smartclient.com/smartgwt/javadoc/com/smartgwt/client/data/RestDataSource.html)
	 * @throws SAXException
	 * @throws IOException
	 */
	public String getHandler() throws SAXException, IOException {
		if (getQuery().getFirstValue("dataType") != null) {
			int requestedDataType = Integer.parseInt(getQuery().getFirstValue(
					"dataType"));
			String clinicId = getQuery().getFirstValue("clinicId");
			if (clinicId != null) {
				switch (requestedDataType) {
				case CLINIC_DATA:
					return getClinicData(Integer.parseInt(clinicId));
				case CONNECTION_DETAILS:
					return getClinicConnectionDetails(Integer
							.parseInt(clinicId));
				case METADATA:
					return getClinicMetadata(Integer.parseInt(clinicId));
				default:
					return "<response><status>-1</status><data>Invalid requested data type!</data></response>";
				}
			} else {
				return "<response><status>-1</status><data>Parameter clinicId required!</data></response>";
			}
		} else {
			return represent();
		}
	}

	private String represent() throws SAXException, IOException {

		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		ObjectOutputStream out;

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		xmlWriter.dataElement("status", "0");
		xmlConverter.alias("record", ClinicData.class);
		out = xmlConverter.createObjectOutputStream(xmlWriter.getWriter(),
				"data");
		for (ClinicData clinicData : (List<ClinicData>) clinicDataBean
				.getAllClinicData()) {
			out.writeObject(clinicData);
		}
		out.close();

		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Post("xml")
	public String addClinic(DomRepresentation rep) throws IOException,
			SAXException {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		ObjectOutputStream out;

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");

		ClinicData persistedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", ClinicData.class);
				ClinicData clinic = (ClinicData) xmlConverter
						.unmarshal(new DomReader(dataNode));

				// Ensuring that the clinicAbbr is always upper case
				clinic.setClinicAbbr(clinic.getClinicAbbr().toUpperCase());

				persistedInstance = clinicDataBean.persist(clinic);
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", ClinicData.class);
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
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Put("xml")
	public String updateClinic(DomRepresentation rep) throws IOException,
			SAXException {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		ObjectOutputStream out;

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");

		ClinicData detachedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", ClinicData.class);
				ClinicData clinic = (ClinicData) xmlConverter
						.unmarshal(new DomReader(dataNode));

				// Ensuring that the clinicAbbr is always upper case
				clinic.setClinicAbbr(clinic.getClinicAbbr().toUpperCase());

				detachedInstance = clinicDataBean.persist(clinic);
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", ClinicData.class);
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
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", ex.getLocalizedMessage());
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	@Delete("xml")
	public String deleteClinic(DomRepresentation rep) throws IOException,
			SAXException {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		Element dataNode = (Element) rep.getNode("//request/data");

		ClinicData deletedInstance = null;
		try {
			if (dataNode != null) {
				xmlConverter.alias("data", ClinicData.class);
				deletedInstance = clinicDataBean
						.deleteClinicDataById(new Integer(rep.getNode(
								"//request/data/clinicId").getTextContent()));

				xmlWriter.dataElement("status", "0");
				xmlWriter.startElement("data");
				xmlWriter.startElement("record");
				xmlWriter
						.dataElement("clinicAbbr",
								new Integer(deletedInstance.getClinicAbbr())
										.toString());
				xmlWriter.endElement("record");
				xmlWriter.endElement("data");

			} else // Sending error message
			{
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to delete the Clinic data! Please retry!");
			}
		} catch (Exception ex) {
			xmlWriter.dataElement("status", "-1");
			xmlWriter.dataElement("data", ex.getLocalizedMessage());
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

	public String getClinicAbbrs() {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		ObjectOutputStream out;

		try {
			xmlWriter.startDocument();

			xmlWriter.setDataFormat(true);

			xmlWriter.startElement("response");

			try {
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", ClinicData.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");

				for (ClinicData clinicData : (List<ClinicData>) clinicDataBean
						.getClinicAbbrs()) {
					out.writeObject(clinicData);
				}
				out.close();
			} catch (Exception e) {
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data", e.getLocalizedMessage());
			}

			xmlWriter.endElement("response");
			xmlWriter.endDocument();

			return xml.toString();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public String getClinicData(int clinicId) {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		try {
			xmlWriter.startDocument();

			xmlWriter.setDataFormat(true);

			xmlWriter.startElement("response");

			ClinicData clinicData = clinicDataBean.findById(clinicId);

			if (clinicData != null) {
				xmlWriter.dataElement("status", "0");
				xmlWriter.startElement("data");
				xmlConverter.alias("record", ClinicData.class);
				xmlConverter.omitField(ClinicData.class,
						"clinicConnectionDetails");
				xmlConverter.omitField(ClinicData.class, "clinicMetadata");
				xmlConverter.toXML(clinicData, xmlWriter.getWriter());
				xmlWriter.endElement("data");
			} else {
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to retrieve clinic data for "
								+ new Integer(clinicId).toString());
			}

			xmlWriter.endElement("response");
			xmlWriter.endDocument();
			return xml.toString();
		} catch (SAXException e) {
			e.printStackTrace();
			return "<response><status>-1</status><data>Server side error! Please contact admin.</data></response>";
		}
	}

	public String getClinicConnectionDetails(int clinicId) {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		try {
			xmlWriter.startDocument();

			xmlWriter.setDataFormat(true);

			xmlWriter.startElement("response");

			ClinicConnectionDetails connDetails = (ClinicConnectionDetails) ((HibernateProxy) clinicDataBean
					.findById(clinicId).getClinicconnectiondetails())
					.getHibernateLazyInitializer().getImplementation();

			if (connDetails != null) {
				xmlWriter.dataElement("status", "0");
				xmlWriter.startElement("data");
				xmlConverter.alias("record", ClinicConnectionDetails.class);
				xmlConverter.aliasSystemAttribute(null, "class");
				xmlConverter.toXML(connDetails, xmlWriter.getWriter());
				xmlWriter.startElement("data");
			} else {
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to retrieve clinic connection details for "
								+ new Integer(clinicId).toString());
			}

			xmlWriter.endElement("response");
			xmlWriter.endDocument();
			return xml.toString();
		} catch (SAXException e) {
			e.printStackTrace();
			return "<response><status>-1</status><data>Server side error! Please contact admin.</data></response>";
		}
	}

	private String getClinicMetadata(int clinicId) {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);

		try {
			xmlWriter.startDocument();

			xmlWriter.setDataFormat(true);

			xmlWriter.startElement("response");

			ClinicMetadata metadata = (ClinicMetadata) ((HibernateProxy) clinicDataBean
					.findById(clinicId).getClinicmetadata())
					.getHibernateLazyInitializer().getImplementation();
			if (metadata != null) {
				xmlWriter.dataElement("status", "0");
				xmlWriter.startElement("data");
				xmlConverter.alias("record", ClinicMetadata.class);
				xmlConverter.toXML(metadata, xmlWriter.getWriter());
				xmlWriter.startElement("data");
			} else {
				xmlWriter.dataElement("status", "-1");
				xmlWriter.dataElement("data",
						"Unable to retrieve clinic metadata for "
								+ new Integer(clinicId).toString());
			}

			xmlWriter.endElement("response");
			xmlWriter.endDocument();
			return xml.toString();
		} catch (SAXException e) {
			e.printStackTrace();
			return "<response><status>-1</status><data>Server side error! Please contact admin.</data></response>";
		}
	}
}
