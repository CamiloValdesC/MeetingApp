package clienteREST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WS_Consumes {
	
	/******************************* METHOD POST *******************************/

	public String consumes_rest_post(String urlToRead, String json){
		String resultPost = null;
		try {
			URL url = new URL(urlToRead);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String file_json = json;
			
			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			osw.write(file_json);
			osw.flush();
			
//			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//			}
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while ((line = rd.readLine()) != null) {
//				System.out.println("WS POST: "+ line);
				resultPost = line;
			}
			rd.close();
			conn.disconnect();
		} catch (Exception e){			 
			 e.printStackTrace();
		}
		return resultPost;
	}
	
	/******************************* METHOD PUT *******************************/
	
	public int consumes_rest_put(String url_ws, String json){
		int ok= 0;
		try {
			URL url = new URL(url_ws);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String file_json = json;	
			
			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8" );
			osw.write(file_json);
			osw.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while ((line = rd.readLine()) != null) {
				//System.out.println(line);
				int retorno	=	0;
				try{
					retorno = Integer.parseInt(line.toString());
				}catch(Exception e){
					retorno =	1;
				}
				if(line.equals("ERROR")){ok= 0;}else{ok= retorno;}
			}
			
			rd.close();
			conn.disconnect();
			
		} catch (Exception e){			 
			 System.out.println(e);
			 ok= 0;
		}	
		return ok;
	}
	
	/******************************* METHOD DELETE *******************************/
	
	public int consumes_rest_delete(String url_ws, String json){
		int ok= 0;
		try {
			URL url = new URL(url_ws);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String file_json = json;	
			
			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8" );
			osw.write(file_json);
			osw.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line;
			while ((line = rd.readLine()) != null) {
				System.out.println(line);				
				if(line.equals("ERROR")){ok= 0;}else{ok= 1;}
			}			
			rd.close();
			conn.disconnect();
		} catch (Exception e){			 
			 System.out.println(e);
			 ok= 0;
		}
		return ok;
	}

}
