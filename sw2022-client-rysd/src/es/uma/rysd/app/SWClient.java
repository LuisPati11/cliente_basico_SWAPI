package es.uma.rysd.app;

import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.Gson;

import es.uma.rysd.entities.*;

public class SWClient {
	// TODO: Complete el nombre de la aplicaci�n
    private final String app_name = "PatiApp";
    private final int year = 2022;
    
    private final String url_api = "https://swapi.dev/api/";

    // M�todos auxiliares facilitados
    
    // Obtiene la URL del recurso id del tipo resource
	public String generateEndpoint(String resource, Integer id){
		return url_api + resource + "/" + id + "/";
	}
	
	// Dada una URL de un recurso obtiene su ID
	public Integer getIDFromURL(String url){
		String[] parts = url.split("/");

		return Integer.parseInt(parts[parts.length-1]);
	}
	
	// Consulta un recurso y devuelve cu�ntos elementos tiene
	public int getNumberOfResources(String resource){ 
			URL servicio = null;
			HttpsURLConnection connection = null;
			InputStream in = null;
		// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse
		
    	// TODO: Cree la URL correspondiente: https://swapi.dev/api/{recurso}/ reemplazando el recurso por el par�metro 
			try {
				servicio = new URL("https://swapi.dev/api/"+resource+"/");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	// TODO: Cree la conexi�n a partir de la URL
			try {
				connection = (HttpsURLConnection) servicio.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	// TODO: A�ada las cabeceras User-Agent y Accept (vea el enunciado)
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", app_name+"-"+year);
    	// TODO: Indique que es una petici�n GET
			try {
				connection.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	// TODO: Compruebe que el c�digo recibido en la respuesta es correcto
			try {
				if (connection.getResponseCode()<200 || connection.getResponseCode()>=300 ) {
					System.out.println("Se ha producido un error");
					return 0;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	// TODO: Deserialice la respuesta a ResourceCountResponse
        Gson parser = new Gson();
		try {
			in = connection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // TODO: Obtenga el InputStream de la conexi�n
        ResourceCountResponse c = parser.fromJson(new InputStreamReader(in), ResourceCountResponse.class);
        // TODO: Devuelva el n�mero de elementos
        connection.disconnect();
        return c.count;
	}
	
	public Person getPerson(String urlname) {
    	Person p = null;
    	HttpsURLConnection connection = null;
    	InputStream in = null;
    	// Por si acaso viene como http la pasamos a https
    	urlname = urlname.replaceAll("http:", "https:");
    	URL servicio = null;
		try {
			servicio = new URL (urlname);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse
		    	
    	// TODO: Cree la conexi�n a partir de la URL recibida
    	try {
			connection = (HttpsURLConnection) servicio.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: A�ada las cabeceras User-Agent y Accept (vea el enunciado)
    	connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("User-Agent", app_name+"-"+year);
    	// TODO: Indique que es una petici�n GET
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: Compruebe que el c�digo recibido en la respuesta es correcto
		try {
			if (connection.getResponseCode()<200 || connection.getResponseCode()>=300 ) {
				System.out.println("Se ha producido un error");
				return null;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: Deserialice la respuesta a Person
		  Gson parser = new Gson();
			try {
				in = connection.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // TODO: Obtenga el InputStream de la conexi�n
	         p = parser.fromJson(new InputStreamReader(in), Person.class);
	        // TODO: Devuelva el n�mero de elementos
	        connection.disconnect();
	        
		
        // TODO: Para las preguntas 2 y 3 (no necesita completar esto para la pregunta 1)
    	// TODO: A partir de la URL en el campo homreworld obtenga los datos del planeta y almac�nelo en atributo homeplanet
	    
	        p.homeplanet=getPlanet(p.homeworld);
	        
    	return p;
	}

	public Planet getPlanet(String urlname) {
    	Planet p = null;
    	HttpsURLConnection connection = null;
    	InputStream in = null;
    	// Por si acaso viene como http la pasamos a https
    	urlname = urlname.replaceAll("http:", "https:");
    	URL servicio = null;
    	try {
			servicio = new URL (urlname);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse
		    	
    	// TODO: Cree la conexi�n a partir de la URL recibida
    	try {
			connection = (HttpsURLConnection) servicio.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: A�ada las cabeceras User-Agent y Accept (vea el enunciado)
    	connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("User-Agent", app_name+"-"+year);
    	// TODO: Indique que es una petici�n GET
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: Compruebe que el c�digo recibido en la respuesta es correcto
		try {
			if (connection.getResponseCode()<200 || connection.getResponseCode()>=300 ) {
				System.out.println("Se ha producido un error");
				return null;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: Deserialice la respuesta a Planet
		Gson parser = new Gson();
		try {
			in = connection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // TODO: Obtenga el InputStream de la conexi�n
         p = parser.fromJson(new InputStreamReader(in), Planet.class);
        // TODO: Devuelva el n�mero de elementos
        connection.disconnect();
        
        return p;
	}

	public Person search(String name){
    	SearchResponse p = null;
    	HttpsURLConnection connection = null;
    	URL servicio = null;
    	InputStream in = null;
    	String nombre = null;
		try {
			nombre = URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	String urlname = "https://swapi.dev/api/people/?search="+nombre;
		try {
			servicio = new URL (urlname);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	// TODO: Trate de forma adecuada las posibles excepciones que pueden producirse
		    	
    	// TODO: Cree la conexi�n a partir de la URL (url_api + name tratado - vea el enunciado)
		try {
			connection = (HttpsURLConnection) servicio.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: A�ada las cabeceras User-Agent y Accept (vea el enunciado)
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("User-Agent", app_name+"-"+year);
    	// TODO: Indique que es una petici�n GET
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: Compruebe que el c�digo recibido en la respuesta es correcto
		try {
			if (connection.getResponseCode()<200 || connection.getResponseCode()>=300 ) {
				System.out.println("Se ha producido un error");
				return null;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	// TODO: Deserialice la respuesta a SearchResponse -> Use la primera posici�n del array como resultado
		Gson parser = new Gson();
		try {
			in = connection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // TODO: Obtenga el InputStream de la conexi�n
         p = parser.fromJson(new InputStreamReader(in), SearchResponse.class);
        // TODO: Devuelva el n�mero de elementos
        
        // TODO: Para las preguntas 2 y 3 (no necesita completar esto para la pregunta 1)
    	// TODO: A partir de la URL en el campo homreworld obtenga los datos del planeta y almac�nelo en atributo homeplanet
        Person per=null;
       
        if(p.count ==0) {
        	  connection.disconnect();
        	return null;
        }
        else {
        	per=p.results[0];
            per.homeplanet=getPlanet(per.homeworld);
            return per;
        }
    }
	
}
