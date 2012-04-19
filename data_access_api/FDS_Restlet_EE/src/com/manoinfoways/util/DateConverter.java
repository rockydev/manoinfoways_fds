/**
 * 
 */
package com.manoinfoways.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author rockydev
 * 
 */
public class DateConverter implements Converter {

	public DateConverter() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class clazz) {
		return Date.class.isAssignableFrom(clazz);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		Date date = (Date) value;

		// grabs the formatter
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		// formats and sets the value
		writer.setValue(formatter.format(date));

	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		// creates the calendar
		Date date = new Date();

		// grabs the converter
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		// parses the string and sets the time
		try {
			date = formatter.parse(reader.getValue());
		} catch (ParseException e) {
			throw new ConversionException(e.getMessage(), e);
		}

		// returns the new object
		return date;

	}

}
