package testSoap;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.SOAPEnvelope;
import org.json.simple.parser.JSONParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

public class Consumir {

		@SuppressWarnings({"rawtypes"})
		public String test(String ws, String metodo, String[] xml){
			String soap 		=	null;
			try{
				Service service = 	new Service(new QName(ws+"?wsdl"));
				Call call 		=	new Call(service);
				URL _url 		= 	new URL(ws);
				
				call.setTargetEndpointAddress(_url);
				call.setOperationName(metodo);
				call.setSOAPActionURI("http://tempuri.org/" + metodo);
				
				SOAPEnvelope _soapenv 	= 	new SOAPEnvelope();
				_soapenv.addNamespaceDeclaration("ser", "ServiciosPAE");			
				SOAPHeader header 		= 	_soapenv.getHeader();
				header.removeNamespaceDeclaration("SOAP-ENV");
				header.setPrefix("soapenv");
				SOAPBody body 				= 	_soapenv.getBody();
				SOAPElement soapElement 	= 	body.addChildElement(metodo, "ser");	
				
				soapElement.addChildElement("Empcod", "ser").addTextNode(xml[0]);
				soapElement.addChildElement("Eaanocon", "ser").addTextNode(xml[1]);
				soapElement.addChildElement("Eamescon", "ser").addTextNode(xml[2]);
				soapElement.addChildElement("Colcod", "ser").addTextNode(xml[3]);
				
				System.out.println(_soapenv);
				
				System.out.println("\n************************************************************************************************************\n");
				
				
				call.setTimeout(Integer.valueOf(15000));
				call.setReturnClass(Vector.class);
				call.setReturnType(XMLType.SOAP_VECTOR);
				
				Object[] params = _soapenv.getBodyElements().toArray();						
				Vector _vobj	= (Vector) call.invoke(params);
				soap			= _vobj.get(0).toString();
				
			}catch(SOAPException e){
				e.printStackTrace();
			}catch(MalformedURLException e){
				e.printStackTrace();
			}catch(AxisFault e){
				System.out.println("Connection timed out: " + metodo);
			}catch(RemoteException e) {
				e.printStackTrace();
			}
			
			return soap;
		}
		
		
		public String convertJSON(String xml) {
			try{
				XMLSerializer 	xmlserializer 	= 	new XMLSerializer();
				xmlserializer.setRemoveNamespacePrefixFromElements(true);
				JSON 			json 		=	xmlserializer.read(xml);
				JSONParser 		jsonParser 	= 	new JSONParser();
				Object 			object 	   	= 	null;		
				String			jsonString	=	json.toString();
								jsonString	=	jsonString	.replace("[[", "")
															.replace("]]", "");
								jsonString	=	"{\"Result\": [" + jsonString + "]}";
								object 		= 	jsonParser.parse(jsonString);
								
				JSONObject 		jsonObject 	= 	(JSONObject) object;
				JSONArray		jsonArray	=	(JSONArray) jsonObject.get("Result");
				
				System.out.println(jsonArray.toJSONString());
				System.out.println("\n************************************************************************************************************\n");
				
				
				for(int i=0; i< jsonArray.size(); i++){
				   	JSONObject 	json_Object 		=	(JSONObject) jsonArray.get(i);
				   	String		TipoAsignacion		=	(String) json_Object.get("TipoAsignacion").toString().trim();
				   	String		Establecimiento		=	(String) json_Object.get("Establecimiento").toString().trim();
				   	String		Estrato				=	(String) json_Object.get("Estrato").toString().trim();
				   	String		LetraPrograma		=	(String) json_Object.get("LetraPrograma").toString().trim();
				   	String		NumeroPrograma		=	(String) json_Object.get("NumeroPrograma").toString().trim();
				   	String		RegionSIGPAE		=	(String) json_Object.get("RegionSIGPAE").toString().trim();
				   	String		ProvinciaSIGPAE		=	(String) json_Object.get("ProvinciaSIGPAE").toString().trim();
				   	String		ComSIGPAE			=	(String) json_Object.get("ComSIGPAE").toString().trim();
				   	String		UTE					=	(String) json_Object.get("UTE").toString().trim();
				   	String		LineaProducto		=	(String) json_Object.get("LineaProducto").toString().trim();
				   	String		Licitacion			=	(String) json_Object.get("Licitacion").toString().trim();
				   	String		CodConcesionario	=	(String) json_Object.get("CodConcesionario").toString().trim();
				   	String		NombreConcesionario	=	(String) json_Object.get("NombreConcesionario").toString().trim();
				   	String		Servicio			=	(String) json_Object.get("Servicio").toString().trim();
				   	
				   	System.out.println(TipoAsignacion + " - " + Establecimiento + " - " + Estrato + " - " + LetraPrograma + " - " + NumeroPrograma + " - " + RegionSIGPAE + " - " + ProvinciaSIGPAE + " - " + ComSIGPAE + " - " + UTE + " - " + LineaProducto + " - " + Licitacion + " - " + CodConcesionario + " - " + NombreConcesionario + " - " + Servicio);
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return	null;
		}
	
	
	public static void main(String args[]) {
		String 		ws	 	=	"http://10.162.14.38:8080/ServiciosPAEJavaEnvironment/servlet/com.serviciospae.aservicioa";
		String 		metodo 	= 	"Execute";
		String[] 	array 	= 	{"1", "2018", "5", "2101"};
		
		String 		xml		=	new Consumir().test(ws, metodo, array);
		System.out.println(xml);
		
		System.out.println("\n************************************************************************************************************\n");
				
		xml	=	xml.replace(" xmlns=\"ServiciosPAE\"", "");
		xml	=	xml.replace("<ServicioA.ExecuteResponse>", "<ServicioA.ExecuteResponse type=\"String\">");
		
		new Consumir().convertJSON(xml);
				
	}
	
}
