package utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ctrls.PersonaData;
import ctrls.UsuarioData;
import model.Persona;
import model.Usuario;

public class Main {
	
	public 	static final Logger logger = Logger.getLogger(Main.class);
	
	/**********************************************************************************************************************************************/
	
	public int countCollection(MongoClient mongo, String db_name, String collection){
		Long count	=	0L;
		//MongoClient mongo		=	MongoCliente.crearConexion(); //ABRIR CONEXION
		if(mongo != null){
			//SI NO EXISTE BASE DE DATOS, LA CREA
			MongoDatabase db = mongo.getDatabase(db_name);
			
			//CREA TABLA SI NO EXISTE Y AGREGA DATOS
			MongoCollection<Document> table = db.getCollection(collection);
	        
	        //CANTIDAD DE DATOS EN LA TABLA
			count = table.countDocuments();
			
		}else{
			logger.warn("ERROR CONEXION MONGO_DB (COUNT COLLECTION)");
		}
		
		return count.intValue();
	}
	
	/**********************************************************************************************************************************************/
	
	public static void insertarData(){
		
		List<Object> objPersona = new ArrayList<>();
		
		objPersona.add(new Persona(24938748, "7", "Antonia"	, "Valdés"));		
		objPersona.add(new Persona(24014255, "4", "Gabriel"	, "Valdés"));
		objPersona.add(new Persona(16663930, "1", "Camilo"	, "Valdés"));
		objPersona.add(new Persona(16115151, "3", "Valeska"	, "Tapia" ));
		
		List<Object> objUsuario = new ArrayList<>();
		
		objUsuario.add(new Usuario("antonia@valdes.cl"	, "antonia"	));		
		objUsuario.add(new Usuario("gabriel@valdes.cl"	, "gabriel"	));
		objUsuario.add(new Usuario("camilo@valdes.cl"	, "camilo"	));
		objUsuario.add(new Usuario("valeska@tapia.cl"	, "valeska" ));
		
		
		int contador_persona = 1;
		for(Object object : objPersona){
			Persona persona = (Persona) object;
			persona.setIdPersona(contador_persona++);
		}
		
		contador_persona = 1;
		int contador_usuario = 1;
		for(Object object : objUsuario){
			Usuario usuario = (Usuario) object;
			usuario.setIdPersona(contador_persona ++);
			usuario.setIdUsuario(contador_usuario ++);
		}
		

		new PersonaData().insertNewDocCollection(objPersona);
		new UsuarioData().insertNewDocCollection(objUsuario);

	}
	
	/**********************************************************************************************************************************************/
	
	public static void main(String args[]){
		
		
	}
	
}
