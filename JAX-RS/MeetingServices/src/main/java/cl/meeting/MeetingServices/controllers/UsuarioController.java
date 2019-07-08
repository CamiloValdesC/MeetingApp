package cl.meeting.MeetingServices.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.meeting.MeetingServices.models.Meeting;
import cl.meeting.MeetingServices.models.Persona;
import cl.meeting.MeetingServices.models.Response;
import cl.meeting.MeetingServices.models.Usuario;
import cl.meeting.MeetingServices.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController{
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioRepository repositoryUsuario;
	    
	/*******************************************  POST_  *******************************************/
	 
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public List<Object> createUsuarioAdministrador(@Valid @RequestBody Usuario usuario) {
		//repositoryUsuario.deleteAll();
		List<Object> dataResult	=	new ArrayList<>();
		try {
			usuario.set_id(ObjectId.get());
			dataResult.add(new Response(1, "OK"));
			dataResult.add(this.repositoryUsuario.save(usuario));
		}catch(Exception ex) {
			dataResult.clear();
			dataResult.add(new Response(0, ex.getMessage()));
		}
        return dataResult;
	}
  
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Persona getUsuarioLogin(@Valid @RequestBody Usuario usuario) {
		Usuario returnUsuario = this.repositoryUsuario.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword());
		Persona persona = new Persona();
		if(null != returnUsuario) {
			persona = returnUsuario.getPersona();
			persona.setResponse(1);
		}else {
			persona = new Persona(0L, "-", "-", "-", "-", "-", 0);
		}
		return persona;		
	}
	
	@RequestMapping(value = "/addMeeting/{username}", method = RequestMethod.POST)
	public Usuario addMeetingToUsuario(@PathVariable String username, @RequestBody Meeting meeting) {
		Usuario usuario 	=	this.repositoryUsuario.findByUsername(username);
		meeting.setCodMeeting(ObjectId.get().toHexString());
		List<Meeting> meetingList = new ArrayList<>();
		if(null != usuario.getMeeting()) {
			meetingList = usuario.getMeeting();
		}
		if(null != meeting) {
			meetingList.add(meeting);
		}
		usuario.setMeeting(meetingList);
		return this.repositoryUsuario.save(usuario);
	}
	
	/********************************************  GET  ********************************************/
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Usuario> getAllUsuarios() {
		return this.repositoryUsuario.findAll();
	}
		
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public Usuario getUsuario(@PathVariable("username") String username) {
		return this.repositoryUsuario.findByUsername(username);
	}
	
	@RequestMapping(value = "/{username}/{password}", method = RequestMethod.GET)
	public Persona getUsuario(@PathVariable("username") String username, @PathVariable("password") String password) {
		Usuario usuario = this.repositoryUsuario.findByUsernameAndPassword(username, password);
		Persona persona = new Persona();
		if(null != usuario) {
			persona = usuario.getPersona();
			persona.setResponse(1);
		}else {
			persona = new Persona(0L, "-", "-", "-", "-", "-", 0);
		}
		return persona;
	}
		
	@RequestMapping(value = "/meeting/{username}", method = RequestMethod.GET)
	public List<Meeting> getMeeting(@PathVariable("username") String username) {
		return this.repositoryUsuario.findByUsername(username).getMeeting();
	}
	
	@RequestMapping(value = "/meeting/{username}/{idMeeting}", method = RequestMethod.GET)
	public Meeting getMeeting(@PathVariable("username") String username, @PathVariable("idMeeting") String idMeeting) {
		List<Meeting> 	meetingList	=	this.repositoryUsuario.findByUsername(username).getMeeting();
		for(Meeting meeting : meetingList) {
			if(meeting.getCodMeeting().equals(idMeeting)) {
				return meeting;
			}
		}
		return null;
	}
    
	/*******************************************  POST_  *******************************************/
  
	
			
	//  @RequestMapping(value = "/", method = RequestMethod.POST)
	//  public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
	//    	usuario.set_id(ObjectId.get());
	//    	repositoryUsuario.save(usuario);
	//    	return usuario;
	//  }
  
		/********************************************  PUT  ********************************************/
  
	//  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	//  public void modifyUsuarioById(@PathVariable("run") Long run, @Valid @RequestBody Usuario usuario) {
	//		usuario.set_id(id);
	//		repositoryUsuario.save(usuario);
	//  }
  
	/******************************************  DELETE  ******************************************/
  
	//  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	//  public void deleteUsuario(@PathVariable ObjectId id) {
	//	  	repositoryUsuario.delete(repositoryUsuario.findBy_id(id));
	//  }
	  
}
