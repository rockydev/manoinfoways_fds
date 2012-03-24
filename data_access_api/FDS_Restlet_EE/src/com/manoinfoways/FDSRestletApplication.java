/**
 * 
 */
package com.manoinfoways;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

import com.manoinfoways.restlet.ClinicDataResource;

/**
 * @author rockydev
 * 
 */
public class FDSRestletApplication extends Application {

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a new instance of
		// HelloWorldResource.
		Router router = new Router(getContext());
		router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);

		/*
		 * Restlet to handle "/clinics/allabbrs" route which return all the
		 * clinicAbbrs within the db as list of ClinicData objects which contain
		 * only the clinicId and clinicAbbr fields
		 */
		Restlet allClinicAbbrs = new Restlet(getContext()) {

			@Override
			public void handle(Request request, Response response) {
				String message = new ClinicDataResource().getClinicAbbrs();
				System.out.println(request.getMethod());
				response.setEntity(message, MediaType.APPLICATION_XML);
			}
		};

		// Defines only one route

		router.attach("/clinics/allabbrs", allClinicAbbrs);
		router.attach("/clinics", ClinicDataResource.class);

		return router;
	}

}
