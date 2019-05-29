package cl.meeting.MeetingServices.models;

import org.springframework.data.mongodb.core.mapping.Field;

public class Participante {

	@Field(value = "Run")
	private Long 	run;

	@Field(value = "Dv")
	private	String	dv;

	@Field(value = "Nombre")
	private String 	nombre;

	@Field(value = "Apellido")
	private String 	apellido;

	@Field(value = "Correo")
	private String	correo;
	
	@Field(value = "Fono")
	private	String	fono;
	
	/**************************************************************************************************/
	
	public Participante() {}	
	
	public Participante(Long run, String dv, String nombre, String apellido, String correo, String fono) {
		this.run 		= 	run;
		this.dv 		=	dv;
		this.nombre 	=	nombre;
		this.apellido 	= 	apellido;
		this.correo 	= 	correo;
		this.fono 		= 	fono;
	}
	
	/**************************************************************************************************/
	
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getFono() {
		return fono;
	}
	public void setFono(String fono) {
		this.fono = fono;
	}

}