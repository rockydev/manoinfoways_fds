/**
 * 
 */
package com.manoinfoways.restlet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.List;

import org.restlet.ext.xml.XmlWriter;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.xml.sax.SAXException;

import com.manoinfoways.ejb.TranscriberTypeDataBean;
import com.manoinfoways.model.TranscriberTypeData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author rockydev
 * 
 */
public class TranscriberTypeDataResource extends ServerResource {
	private static TranscriberTypeDataBean transcriberTypeDataBean;
	private static XStream xmlConverter;

	/**
	 * 
	 */
	public TranscriberTypeDataResource() {
		transcriberTypeDataBean = new TranscriberTypeDataBean();
		xmlConverter = new XStream(new DomDriver());
		xmlConverter.processAnnotations(TranscriberTypeData.class);
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
		xmlConverter.alias("record", TranscriberTypeData.class);
		out = xmlConverter.createObjectOutputStream(xmlWriter.getWriter(),
				"data");
		for (TranscriberTypeData transcriberTypeData : (List<TranscriberTypeData>) transcriberTypeDataBean
				.getAllTranscriberTypes()) {
			out.writeObject(transcriberTypeData);
		}
		out.close();

		xmlWriter.endElement("response");
		xmlWriter.endDocument();

		return xml.toString();
	}

}
