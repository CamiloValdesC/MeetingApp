package cl.meeting.MeetingServices.models;

import org.springframework.data.mongodb.core.mapping.Field;

public class Perfil {
	
	@Field(value = "Descripcion")
	private String	descripcion;
	
	/**************************************************************************************************/
	
	public Perfil() {}
	
	public Perfil(String descripcion) {
		this.descripcion 	=	descripcion;
	}
	
	/**************************************************************************************************/
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}