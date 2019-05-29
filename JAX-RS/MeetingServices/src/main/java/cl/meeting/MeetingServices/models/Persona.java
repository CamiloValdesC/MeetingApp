package cl.meeting.MeetingServices.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Persona {
	
	@Indexed(unique = true)
	@Field(value = "Run")
	private	Long	run;
	
	@Field(value = "Dv")
	private String	dv;
	
	@Field(value = "Nombre")
	private String	nombre;
	
	@Field(value = "Paterno")
	private String	paterno;
	
	@Field(value = "Materno")
	private String	materno;
	
	@Indexed(unique = true)
	@Field(value = "Correo")
	private String	correo;
	
	private int		response;
	
	/**************************************************************************************************/
	
	public Persona() {}
	
	public Persona(Long run, String dv, String nombre, String paterno, String materno, String correo, int response) {
		this.run		=	run;
		this.dv			=	dv;
		this.nombre 	= 	nombre;
		this.paterno 	= 	paterno;
		this.materno 	= 	materno;
		this.correo		=	correo;
		this.response	=	response;
	}
	
	/**************************************************************************************************/
	
	public int getResponse() {
		return response;
	}
	public void setResponse(int response) {
		this.response = response;
	}
	
	
	public Long getRun() {
		return run;
	}
	public void setRun(Long run) {
		this.run = run;
	}
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}

}