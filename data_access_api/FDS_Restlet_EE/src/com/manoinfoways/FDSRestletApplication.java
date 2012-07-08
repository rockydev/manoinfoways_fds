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

import com.manoinfoways.model.TranscriberLengthTimes;
import com.manoinfoways.restlet.ClinicDataResource;
import com.manoinfoways.restlet.DoctorDataResource;
import com.manoinfoways.restlet.TranscriberDataResource;
import com.manoinfoways.restlet.TranscriberDoctorPriorityResource;
import com.manoinfoways.restlet.TranscriberLengthTimesResource;
import com.manoinfoways.restlet.TranscriberTypeDataResource;
import com.manoinfoways.restlet.VoiceFileAssignmentDataResource;

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
		 * Restlet to handle "/clinics/allabbrs" route which returns all the
		 * clinicAbbrs within the db as a list of ClinicData objects which
		 * contain only the clinicId and clinicAbbr fields
		 */
		Restlet allClinicAbbrs = new Restlet(getContext()) {

			@Override
			public void handle(Request request, Response response) {
				String message = new ClinicDataResource().getClinicAbbrs();
				response.setEntity(message, MediaType.APPLICATION_XML);
			}
		};
		
		/*
		 * Restlet to handle "/doctors/allabbrs" route which returns all the
		 * Doctor Abbrs within the db as a list of DoctorData objects which
		 * contain only the doctorId and doctorAbbr fields
		 */
		Restlet allDoctorAbbrs = new Restlet(getContext()) {

			@Override
			public void handle(Request request, Response response) {
				String message = new DoctorDataResource().getDoctorAbbrs();
				response.setEntity(message, MediaType.APPLICATION_XML);
			}
		};
		
		/*
		 * Restlet to handle "/transcribers/usernames" route which returns all the
		 * Transcriber usernames within the db as a list of DoctorData objects which
		 * contain only the doctorId and doctorAbbr fields
		 */
		Restlet allTranscriberUserNames = new Restlet(getContext()) {

			@Override
			public void handle(Request request, Response response){
				String message = new TranscriberDataResource().getAllUserNames();
				response.setEntity(message, MediaType.APPLICATION_XML);
			}
		};

		/*
		 * Restlet to handle "/clinics/{clinicId}/doctors/allabbrs" route which
		 * return all the doctorAbbrs for the given clinicId within the db as a
		 * list of DcotorData objects which contain only the doctorId and
		 * doctorAbbr fields
		 */
		Restlet allClinicDoctorAbbrs = new Restlet(getContext()) {

			@Override
			public void handle(Request request, Response response) {
				String message = new DoctorDataResource()
						.getDoctorAbbrs((String) request.getAttributes().get(
								"clinicId"));
				response.setEntity(message, MediaType.APPLICATION_XML);
			}
		};
		
		/*
		 * Restlet to handle "/clinics/{clinicId}" route which
		 * return all the doctorAbbrs for the given clinicId within the db as a
		 * list of DcotorData objects which contain only the doctorId and
		 * doctorAbbr fields
		 */
		Restlet getClinicData = new Restlet(getContext()) {

			@Override
			public void handle(Request request, Response response) {
				String message = new ClinicDataResource()
						.getClinicData(new Integer((String) request.getAttributes().get(
								"clinicId")));
				response.setEntity(message, MediaType.APPLICATION_XML);
			}
		};
		
		router.attach("/clinics/allabbrs", allClinicAbbrs);
		// router.attach("/clinics/{clinicId}/conndetails",ClinicConnectionDetailsResource.class);
		// router.attach("/clinics/{clinicId}/metadata",
		// ClinicMetadataResource.class);
		// router.attach("/clinics/{clinicId}/reqs",
		// ClinicRequirementsResource.class);
		router.attach("/clinics/{clinicId}/doctors/allabbrs", allClinicDoctorAbbrs);
		router.attach("/clinics/{clinicId}/doctors", DoctorDataResource.class);
		router.attach("/clinics/{clinicId}",getClinicData);
		router.attach("/clinics", ClinicDataResource.class);
		router.attach("/transcribers/{transcriberId}/priorities",TranscriberDoctorPriorityResource.class);
		router.attach("/transcribers/{transcriberId}/lengths",TranscriberLengthTimesResource.class);
		router.attach("/transcribers/types",TranscriberTypeDataResource.class);
		router.attach("/transcribers/usernames",allTranscriberUserNames);
		router.attach("/transcribers",TranscriberDataResource.class);
		router.attach("/doctors/allabbrs",allDoctorAbbrs);
		router.attach("/voicefileassignments",VoiceFileAssignmentDataResource.class);

		return router;
	}
}
