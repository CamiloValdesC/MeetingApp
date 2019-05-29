package store_ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import ctrls.ComunData;
import ctrls.PersonaData;
import ctrls.UsuarioData;
import utils.Main;

@Path("/wsp")
@Produces({MediaType.APPLICATION_JSON + ";charset=UTF-8"})
public class ProducesWS {
	
	/************************************************** PERSONAS **************************************************/
	
	@GET	
	@Path("/listarPersonas")
	public Response traerPersonas(){
		List<Object> data = new PersonaData().getCollection();
		GenericEntity<List<Object>> entity = new GenericEntity<List<Object>>(data) {};
		Response response =	Response.status(201).entity(entity).build();		
		return response;
	}
	
	@GET	
	@Path("/traerPersona")
	public Response traerPersona(@QueryParam("run") int 	  run,
								 @QueryParam("nom") String nom){
		HashMap<String, Object> map = new HashMap<>();
		map.put("Run"	, run);
		map.put("Nombre", nom);
		Set<Entry<String, Object>> set = map.entrySet();
		
		List<Document> data = new PersonaData().getCollectionFind(set);
		GenericEntity<List<Document>> entity = new GenericEntity<List<Document>>(data) {};
		Response response =	Response.status(201).entity(entity).build();		
		return response;
	}
	
	/************************************************** USUARIOS **************************************************/
	
	@GET	
	@Path("/listarUsuarios")
	public Response traerUsuarios(){
		List<Object> data	= 	new UsuarioData().getCollection();
		GenericEntity<List<Object>> entity = new GenericEntity<List<Object>>(data) {};
		Response response 	= 	Response.status(201).entity(entity).build();		
		return response;
	}
	
	@GET	
	@Path("/traerUsuario")
	public Response traerUsuario(@QueryParam("username") String username,
								 @QueryParam("password") String password){
		HashMap<String, Object> map = new HashMap<>();
		map.put("Username", username);
		map.put("Password", password);
		Set<Entry<String, Object>> set = map.entrySet();
		
		List<Document> data = new UsuarioData().getCollectionFind(set);
		GenericEntity<List<Document>> entity = new GenericEntity<List<Document>>(data) {};
		Response response =	Response.status(201).entity(entity).build();		
		return response;
	}
		
	/********************************************** DELETE COLLECTION **********************************************/
	
	@GET
	@Path("/deleteCollection")
	public Response deleteCollection(@QueryParam("db") String db_name){
		List<Object>	data	=	new ComunData().deleteAllCollection(db_name);
		GenericEntity<List<Object>> entity = new GenericEntity<List<Object>>(data) {};
		Response response 	= 	Response.status(201).entity(entity).build();
		return response;
	}	
	
	/***************************************************************************************************************/
	
	@GET
	@Path("/test")
	public Response test(){
		Main.insertarData();
		List<String> status = new ArrayList<>();
		status.add("ok");
		Response response 	= 	Response.status(201).entity(status).build();		
		return response;
	}

}