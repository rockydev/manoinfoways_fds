/**
 * 
 */
package com.project.fms.admin.widgets.data;

/**
 * @author rdevinen
 *
 */
public class SessionData {
	
	private static SessionData _instance = null;
	
	private int status;
	
	public SessionData()
	{
		
	}
	
	public static SessionData getInstance()
	{
		if (_instance == null)
		{
			_instance = new SessionData();
		}
		
		return _instance;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
