package control.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import control.IndexServlet;

public class Log {
	
//	public static final Logger logger = LogManager.getLogger(IndexServlet.class);
	
	public static void log() {
		
		String ruta = "C:/Users/Miguel/OneDrive - Grupo VASS/FCT-Serbatic/CURSO JAVA/WEB/ProyectoTIENDASerbatic/src/main/resources/log4j/log4j.properties";
		PropertyConfigurator.configure(ruta);
	}
	
}
