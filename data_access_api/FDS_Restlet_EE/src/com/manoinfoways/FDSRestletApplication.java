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

import com.manoinfoways.restlet.ClinicConnectionDetailsResource;
import com.manoinfoways.restlet.ClinicDataResource;
import com.manoinfoways.restlet.ClinicMetadataResource;
import com.manoinfoways.restlet.ClinicRequirementsResource;

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
				response.setEntity(message, MediaType.APPLICATION_XML);
			}
		};
		
		
		router.attach("/clinics/allabbrs", allClinicAbbrs);
		router.attach("/clinics/{clinicId}/conndetails",ClinicConnectionDetailsResource.class);
		router.attach("/clinics/{clinicId}/metadata", ClinicMetadataResource.class);
		router.attach("/clinics/{clinicId}/reqs", ClinicRequirementsResource.class);
		router.attach("/clinics", ClinicDataResource.class);

		return router;
	}

}
