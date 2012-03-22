/**
 * 
 */
package com.manoinfoways;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

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
        // Create a router Restlet that routes each call to a new instance of HelloWorldResource.
        Router router = new Router(getContext());

        
        
        // Defines only one route
        router.attach("/clinics", ClinicDataResource.class);
        router.attach("/clinics/{clinicId}", ClinicDataResource.class);
        
        return router;
    }
    
//    public static void main(String[] args) throws Exception {  
//	    // Create a new Component.  
//	    Component component = new Component();  
//	  
//	    // Add a new HTTP server listening on port 8182.  
//	    component.getServers().add(Protocol.HTTP, 80);  
//	  
//	    // Attach the sample application.  
//	    component.getDefaultHost().attach("/fds",  
//	            new FDSRestletApplication());  
//	  
//	    // Start the component.  
//	    component.start();  
//	}  
}
