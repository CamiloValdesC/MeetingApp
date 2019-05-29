package modelin;

import clienteREST.WS_Consumes;
import clienteREST.WS_Produces;

public class UsuarioData {
	
	public String LoginPOST(String ws, String metodo_rest, String json){		
		String address_service	=	ws + metodo_rest + "/";
		String result_rest 		=	new WS_Consumes().consumes_rest_post(address_service, json);
	    return result_rest;
	}
	
	public String LoginGET(String ws_metodo_rest, String params){		
		String address_service	=	ws_metodo_rest + params;
		String result_rest 		=	new WS_Produces().getJSON(address_service);
	    return result_rest;
	}

}
