/**
 * 
 */
package com.manoinfoways.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
public class TimeConverter implements Converter {


	public TimeConverter() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class clazz) {
		return Time.class.isAssignableFrom(clazz);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		Time time = (Time) value;

		// grabs the formatter
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

		// formats and sets the value
		writer.setValue(formatter.format(time));

	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		// creates the calendar
		Time time;

		// grabs the converter
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

		// parses the string and sets the time
		try {
			time = new Time(formatter.parse(reader.getValue()).getTime());
		} catch (ParseException e) {
			throw new ConversionException(e.getMessage(), e);
		}

		// returns the new object
		return time;

	}

}
