package clienteREST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

public class WS_Produces {
	
	/******************************* METHOD POST *******************************/

	public String postJSON(String url_ws, String param){		
		StringBuilder result = new StringBuilder();
		try{	      
	      URL url = new URL(url_ws + param);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setDoOutput(true);
	      conn.setRequestMethod("POST");
	      conn.setRequestProperty("Accept", MediaType.APPLICATION_JSON+"; charset=utf-8");
	      conn.setConnectTimeout(10000);
		  conn.setReadTimeout(10000);
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      conn.disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
	      return result.toString();
	}
	
	/******************************* METHOD GET *******************************/
	
	public String getJSON(String url_ws){
		StringBuilder result = new StringBuilder();
	    try{
		  URL url = new URL(url_ws);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setDoOutput(true);
	      conn.setRequestMethod("GET");
	      conn.setRequestProperty("Content-type", "Application/JSON");
	      conn.setRequestProperty("Accept", MediaType.APPLICATION_JSON+";charset=utf-8");
		  conn.setConnectTimeout(10000);
		  conn.setReadTimeout(10000);      
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));	      
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      conn.disconnect();
	     }catch(Exception e){
	    	 System.out.println(e);
	     }
	      return result.toString();	      
	}
	
}
