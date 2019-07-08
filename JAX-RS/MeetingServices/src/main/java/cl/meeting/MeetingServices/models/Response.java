package cl.meeting.MeetingServices.models;

import java.io.Serializable;

public class Response implements Serializable{
	private static final long serialVersionUID = -4725184504754580190L;
	
	private int 	code;
	private	String 	msje;
	
	/**************************************************************************************************/
	
	public Response() {}	
	
	public Response(int code, String msje) {
		this.code = code;
		this.msje = msje;
	}
	
	/**************************************************************************************************/
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsje() {
		return msje;
	}
	public void setMsje(String msje) {
		this.msje = msje;
	}
	
}