package ctrls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoDatabase;

import dataBase.MongoCliente;

public class ComunData implements IData{
	
	public 	static final Logger logger		=	Logger.getLogger(ComunData.class);

	@Override
	public List<Object> getCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getCollectionFind(Set<Entry<String, Object>> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult insertNewDocCollection(List<Object> lista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> deleteAllCollection(String db_name) {
		MongoClient mongo	=	MongoCliente.crearConexion(); //ABRIR CONEXION
		List<Object> sb		=	new ArrayList<>();
		try {
			//FORMATO FECHA
			SimpleDateFormat 	dfh 	= 	new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat 	dff 	= 	new SimpleDateFormat("dd/MM/yyyy");
			Date 				date	=	new Date();
			if(mongo != null){
				try{
					sb.add(0);
					//SI NO EXISTE BASE DE DATOS, LA CREA
					MongoDatabase db = mongo.getDatabase(db_name);
					
					//ELIMINAR BASE DE DATOS
					logger.debug("START: " 	+ dfh.format(date) + " hrs. " + dff.format(date) + "\n - DROP DATA_BASE: " + db_name);
					sb.add("START: " 	+ dfh.format(date) + " hrs. " + dff.format(date));
					db.drop();
					logger.debug("FINISHED: " + dfh.format(date) + " hrs. " + dff.format(date) + "\n - PROCESS COMPLETE");
					sb.add("FINISHED: " + dfh.format(date) + " hrs. " + dff.format(date));
							
				}catch(Exception e){
					logger.error(e);
					sb.clear();
					sb.add(1);
					sb.add(e.getMessage());
				}
				
			}else{
				logger.warn("ERROR CONEXION MONGO_DB (DROP DATABASE)");
				sb.clear();
				sb.add(-1);
				sb.add("ERROR CONEXION MONGO_DB (DROP DATABASE " + db_name + ")");
			}
		}catch(Exception ex) {
			sb.clear();
			sb.add(-2);
			sb.add(ex.getMessage());
		}
		
		return sb;
	}

}
