/**
 * 
 */
package com.project.fms.user.data;


/**
 * @author deadlydeo
 *
 */
public class SessionHandler {

	private static SessionHandler sessionInstance = null;
	private String userId;
	private String userRole;
	private int typeId;
	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public SessionHandler(){
		
	}
	
	public static SessionHandler getSessionInstance(){
		if (sessionInstance == null)
		{
			sessionInstance = new SessionHandler();
		}
		
		return sessionInstance;
	}
}
