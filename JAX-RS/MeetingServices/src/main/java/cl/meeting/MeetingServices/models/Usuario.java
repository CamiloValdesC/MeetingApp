package cl.meeting.MeetingServices.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="MeetingCondom")
public class Usuario {
	
	@Id
	private ObjectId _id;
  
	@Indexed(unique = true)
	@Field(value = "Username")
	private	String 	username;
	
	@Field(value = "Password")
	private	String 	password;

	@Field(value = "Persona")
	private Persona	persona;
	
	@Field(value = "Perfil")
	private Perfil 	perfil;
	
	@Field(value = "Meeting")
	private List<Meeting> meeting;
	  
	/**************************************************************************************************/
	
	public Usuario() {}
	
	public Usuario(String username) {
		this.username 	=	username;
	}
	
	public Usuario(String username, String password) {
		this.username 	=	username;
		this.password 	=	password;
	}
	
	public Usuario(String username, String password, Persona persona) {
		this.username 	=	username;
		this.password 	=	password;
		this.persona	=	persona;
	}
	  
	public Usuario(ObjectId _id, String username, String password, Persona persona, Perfil perfil) {
		this._id		=	_id;
		this.username 	=	username;
		this.password 	=	password;
		this.persona	=	persona;
		this.perfil 	= 	perfil;
	}
	
	/**************************************************************************************************/
		
	public String get_id() {
		return _id.toHexString();
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public List<Meeting> getMeeting() {
		return meeting;
	}
	public void setMeeting(List<Meeting> meeting) {
		this.meeting = meeting;
	}

}