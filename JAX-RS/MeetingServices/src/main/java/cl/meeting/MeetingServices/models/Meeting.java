package cl.meeting.MeetingServices.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Meeting {
	
	@Field(value = "CodMeeting")
	private String	codMeeting;
		
	@Field(value = "Descripcion")
	private String 	descripcion;
	
	@Field(value = "Ubicacion")
	private String 	ubicacion;
	
	@Field(value = "FechaHoraIni")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date 	fechaHoraIni;
	
	@Field(value = "FechaHoraFin")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date	fechaHoraFin;
	
	@Field(value = "Participante")
	private List<Participante> participante;

	/**************************************************************************************************/
	
	public Meeting() {}
	
	public Meeting(String codMeeting) {
		this.codMeeting		=	codMeeting;
	}
	
	public Meeting(String codMeeting, String descripcion, String ubicacion, Date fechaHoraIni, Date fechaHoraFin) {
		this.codMeeting		=	codMeeting;
		this.descripcion 	= 	descripcion;
		this.ubicacion 		= 	ubicacion;
		this.fechaHoraIni 	= 	fechaHoraIni;
		this.fechaHoraFin 	= 	fechaHoraFin;
	}
	
	public Meeting(String codMeeting, String descripcion, String ubicacion, Date fechaHoraIni, Date fechaHoraFin, List<Participante> participante) {
		this.codMeeting		=	codMeeting;
		this.descripcion 	= 	descripcion;
		this.ubicacion 		= 	ubicacion;
		this.fechaHoraIni 	= 	fechaHoraIni;
		this.fechaHoraFin 	= 	fechaHoraFin;
		this.participante 	= 	participante;
	}

	/**************************************************************************************************/
	
	public String getCodMeeting() {
		return codMeeting;
	}
	public void setCodMeeting(String codMeeting) {
		this.codMeeting = codMeeting;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Date getFechaHoraIni() {
		return fechaHoraIni;
	}
	public void setFechaHoraIni(Date fechaHoraIni) {
		this.fechaHoraIni = fechaHoraIni;
	}
	public Date getFechaHoraFin() {
		return fechaHoraFin;
	}
	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
	public List<Participante> getParticipante() {
		return participante;
	}
	public void setParticipante(List<Participante> participante) {
		this.participante = participante;
	}

}