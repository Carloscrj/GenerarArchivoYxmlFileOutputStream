package com.dam.act.tem1_2pag16;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.dam.javabeans.Empleado;

public class GenerarXml1 {

	public static void main(String[] args) {
		Empleado[] empleados = { new Empleado(1,"Alberto", 10, 2000.0),
				 new Empleado(2,"Guillermo", 20, 1500.50),
				 new Empleado(3,"Alejandro", 30, 3000.0),
				 new Empleado(4,"Ana", 20, 2300.60),
				 new Empleado(5,"Patricia", 10, 1900.10),
				 
		};
		
		try {
			//instanciamos el DBF:
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			//Crear el parser:
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//DOMImplementation nos facilita el método createDocument para crear un documento XML
			DOMImplementation implementation = builder.getDOMImplementation();
			
			//Crear un documento vacío indicando el nombre del nodo raíz:
			Document document = implementation.createDocument(null, "empleados",null);
			
			//Asignar la versión del XML:
			document.setXmlVersion("1.0");

			
			//Crear elementos y añadirlos al nodo raíz:
			Element elemento = document.createElement("empleado");
			
			for (Empleado empleado: empleados) {
				
				document.getDocumentElement().appendChild(elemento);
				//Es hijo de 'elemento' empleado:
				Element elemento2 = document.createElement("id");							
				Text texto = document.createTextNode(String.valueOf(empleado.getId()));
				elemento2.appendChild(texto);
				elemento.appendChild(elemento2);
				
				elemento2 = document.createElement("nombre");
				texto = document.createTextNode(empleado.getNombre());
				elemento2.appendChild(texto);
				elemento.appendChild(elemento2);
				
				elemento2 = document.createElement("dep");
				texto = document.createTextNode(String.valueOf(empleado.getDepartamento()));
				elemento2.appendChild(texto);
				elemento.appendChild(elemento2);
				
				elemento2 = document.createElement("salario");
				texto = document.createTextNode(String.valueOf(empleado.getSalario()));
				elemento2.appendChild(texto);
				elemento.appendChild(elemento2);
				
				document.getDocumentElement().appendChild(elemento);
				elemento = document.createElement("empleado");
			}

			
			//Una vez hemos generado la estructura, crear la fuente XML a partir del documento:
			Source scr = new DOMSource(document);
			
			//Crear el resultado en el fichero xml:
			Result result = new StreamResult(new File("empleados1.xml"));
			
			//Obtenemos un TransformerFactory:
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");//para indicar que la tabulación son 4 espacios
			transformer.transform(scr,  result);

			

		} catch (ParserConfigurationException e) {
			
		} catch (TransformerConfigurationException e) {
			// TODO: handle exception
		} catch (TransformerFactoryConfigurationError e) {
			// TODO: handle exception
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
