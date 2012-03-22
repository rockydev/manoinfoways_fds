/**
 * 
 */
package com.manoinfoways.restlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.List;

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
import com.manoinfoways.model.ClinicData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.DomReader;

/**
 * @author rockydev
 * 
 */
public class ClinicDataResource extends ServerResource {

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
	public String represent() throws SAXException, IOException {
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);
		
		ObjectOutputStream out;
		
		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");

		String clinicId = (String) getRequest().getAttributes().get("clinicId");
		if (clinicId != null) {
			ClinicData clinicData = clinicDataBean.findById(Integer.parseInt(clinicId));
			if (clinicData != null) {
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", ClinicData.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
				out.writeObject(clinicData);
				out.close();
			} else {
				xmlWriter.dataElement("status", "-4");
				xmlWriter.startElement("errors");
				xmlWriter.startElement("clinicId");
				xmlWriter
						.dataElement("InvalidClinicId",
								"The clinicId given is either invalid or not found in the database");
				xmlWriter.endElement("clinicId");
				xmlWriter.endElement("errors");
			}
		} else {
			xmlWriter.dataElement("status", "0");
			xmlConverter.alias("record", ClinicData.class);
			out = xmlConverter.createObjectOutputStream(xmlWriter.getWriter(),
					"data");
			for (ClinicData clinicData : (List<ClinicData>) clinicDataBean
					.getAllClinicData()) {
				out.writeObject(clinicData);
			}
			out.close();
		}
		
		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}
	
	@Post("xml")
	public String addClinic(DomRepresentation rep) throws IOException, SAXException
	{
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);
		
		ObjectOutputStream out;
		
		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");
		
		Element dataNode = (Element) rep.getNode("//request/data");
		
		
		ClinicData persistedInstance = null;
		try{
			if (dataNode != null)
			{
				xmlConverter.alias("data", ClinicData.class);
				persistedInstance = clinicDataBean.persist((ClinicData) xmlConverter.unmarshal(new DomReader(dataNode)));
				
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", ClinicData.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
				out.writeObject(persistedInstance);
				out.close();
			}
			else //Sending error message
			{
				xmlWriter.dataElement("status", "-1"); 
				xmlWriter
						.dataElement("data",
								"Unable to add the Clinic data! Please retry!");
			}
		}
		catch(Exception ex)
		{
			xmlWriter.dataElement("status", "-1"); 
			xmlWriter
					.dataElement("data",
							ex.getLocalizedMessage());
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();
		
		return xml.toString();
	}
	
	@Put("xml")
	public String updateClinic(DomRepresentation rep) throws IOException, SAXException
	{
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);
		
		ObjectOutputStream out;
		
		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");
		
		Element dataNode = (Element) rep.getNode("//request/data");
		
		
		ClinicData detachedInstance = null;
		try{
			if (dataNode != null)
			{
				xmlConverter.alias("data", ClinicData.class);
				detachedInstance = clinicDataBean.merge((ClinicData) xmlConverter.unmarshal(new DomReader(dataNode)));
				
				xmlWriter.dataElement("status", "0");
				xmlConverter.alias("record", ClinicData.class);
				out = xmlConverter.createObjectOutputStream(
						xmlWriter.getWriter(), "data");
				out.writeObject(detachedInstance);
				out.close();
			}
			else //Sending error message
			{
				xmlWriter.dataElement("status", "-1"); 
				xmlWriter
						.dataElement("data",
								"Unable to update the Clinic data! Please retry!");
			}
		}
		catch(Exception ex)
		{
			xmlWriter.dataElement("status", "-1"); 
			xmlWriter
					.dataElement("data",
							ex.getLocalizedMessage());
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();
		
		return xml.toString();
	}
	
	@Delete("xml")
	public String deleteClinic(DomRepresentation rep) throws IOException, SAXException
	{
		StringWriter xml = new StringWriter();
		XmlWriter xmlWriter = new XmlWriter(xml);
		
		xmlWriter.startDocument();
		xmlWriter.setDataFormat(true);

		xmlWriter.startElement("response");
		
		Element dataNode = (Element) rep.getNode("//request/data");
		
		
		ClinicData deletedInstance = null;
		try{
			if (dataNode != null)
			{
				xmlConverter.alias("data", ClinicData.class);
				deletedInstance = clinicDataBean.delete((ClinicData) xmlConverter.unmarshal(new DomReader(dataNode)));
				
				xmlWriter.dataElement("status", "0");
				xmlWriter.startElement("data");
				xmlWriter.startElement("record");
				xmlWriter.dataElement("clinicId", new Integer(deletedInstance.getClinicId()).toString());
				xmlWriter.endElement("record");
				xmlWriter.endElement("data");
				
			}
			else //Sending error message
			{
				xmlWriter.dataElement("status", "-1"); 
				xmlWriter
						.dataElement("data",
								"Unable to delete the Clinic data! Please retry!");
			}
		}
		catch(Exception ex)
		{
			xmlWriter.dataElement("status", "-1"); 
			xmlWriter
					.dataElement("data",
							ex.getLocalizedMessage());
		}
		xmlWriter.endElement("response");
		xmlWriter.endDocument();
		
		return xml.toString();
	}
}
