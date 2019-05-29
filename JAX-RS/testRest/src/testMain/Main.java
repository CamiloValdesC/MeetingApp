package testMain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelin.Usuario;
import modelin.UsuarioData;

public class Main {
	
	public void mainClass() {
		
		String 	ws 				=	"http://186.64.122.131:8080/MeetingServices/usuario/";
        String	metodo_rest		=	"login";
        String	paramsGET		=	"/Gabriel/g@br13l";
        
        String	resultGET			=	new UsuarioData().LoginGET(ws, paramsGET);
		System.out.println(resultGET);
		
		/********************************************************************************************************************/
		
		System.out.println("\n*************************************************************************************************\n");
		
		/********************************************************************************************************************/
		
		Usuario	usuario			=	new Usuario("Gabriel", "g@br13l");
		
		Gson 	gson 			=	new GsonBuilder().create();
		String 	paramsPOST 		=	gson.toJson(usuario);
		System.out.println(paramsPOST);		
		
		String	resultPOST			=	new UsuarioData().LoginPOST(ws, metodo_rest, paramsPOST);
		System.out.println(resultPOST);
		
	}
	
	/*************************************************************************************************************************************************************************/
	
	public static void main(String args[]) {
		
		String 	ws 				=	"http://186.64.122.131:8080/MeetingServices/usuario/";
        String	metodo_rest		=	"login";
        String	paramsGET		=	"/Gabriel/g@br13l";
        
        String	resultGET			=	new UsuarioData().LoginGET(ws, paramsGET);
        System.out.println("GET");
        System.out.println(resultGET);
        
        /********************************************************************************************************************/
        
        System.out.println("\n*************************************************************************************************\n");
		
		/********************************************************************************************************************/
		
		Usuario	usuario			=	new Usuario("Gabriel", "g@br13l");
		
		Gson 	gson 			=	new GsonBuilder().create();
		String 	paramsPOST 		=	gson.toJson(usuario);
		System.out.println(paramsPOST);		
		
		String	resultPOST			=	new UsuarioData().LoginPOST(ws, metodo_rest, paramsPOST);
		System.out.println("POST");
		System.out.println(resultPOST);
		
	}

}
