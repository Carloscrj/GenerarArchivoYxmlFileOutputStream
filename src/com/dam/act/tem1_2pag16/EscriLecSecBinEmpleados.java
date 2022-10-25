package com.dam.act.tem1_2pag16;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

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



public class EscriLecSecBinEmpleados {
	static final int TAM_NOM= 10;//definimos la cantidad de caracteres que va a tener cada atributo del String, ecir cada char 2 bytes
	static final int TAM_REG= 36;  //id(4),nombre(20), departamento(int ocupa 4) y salario(double 8)


	public static void main(String[] args) {
		
		Empleado[] empleados = { new Empleado(1,"Alberto", 10, 2000.0),
				 new Empleado(2,"Guillermo", 20, 1500.50),
				 new Empleado(3,"Alejandro", 30, 3000.0),
				 new Empleado(4,"Ana", 20, 2300.60),
				 new Empleado(5,"Patricia", 10, 1900.10),
				 
		};
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("empleados.dat"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("empleados.dat"))){
					
				for (Empleado empleado: empleados) {
					oos.writeObject(empleado);
				}
				
				try {
					while (true) {
						System.out.println((Empleado) ois.readObject());
						
					}
				} catch (EOFException e) {
					System.out.println("FIN DE FICHERO");
				}catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
}

	
	
	




