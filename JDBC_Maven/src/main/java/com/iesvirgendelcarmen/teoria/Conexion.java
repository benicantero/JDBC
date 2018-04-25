package com.iesvirgendelcarmen.teoria;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.util.Properties;

import org.sqlite.SQLiteConfig ;

public class Conexion {
	
	
	private static Connection conexion = null ;
	private Conexion () {}

	public static Connection getConexion (){
		if ( conexion == null ){  
			try {
				Properties p = new Properties(); //Crea el objeto p de propiedades
				p.load(new FileReader("BD.properties")); // carga el fichero leyendo el archivo BD1.properties
				final String URL = p.getProperty("DB_URL"); // creamos los string de donde va a cojer los datos de properties
				final String BD = p.getProperty("BD");
				final String DRIVER = p.getProperty("DRIVER");
				
				Class.forName(DRIVER); // carga el driver
				
				// Configurar el obejto Config para permitir foreign keys (pragma)
				SQLiteConfig config = new SQLiteConfig ();
				config.enforceForeignKeys ( true );
				
				//establecer la conexion
				conexion =DriverManager.getConnection(URL + BD,
								config.toProperties());
				System.out.println("Hay conexion");
			} catch ( ClassNotFoundException |
					SQLException e) {
				e.printStackTrace ();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Runtime.getRuntime().addShutdownHook(new MiShutdownHuk());
		} 
		return conexion ;
			
		
	}
	public static void desconectar (){
		if ( conexion != null )
			try {
				conexion.close ();
			} catch ( SQLException e) {
				e.printStackTrace ();
			}
	}

	
	/*public static Connection getConexion() {
		if(conexion==null) {
			new Conexion();
			Runtime.getRuntime().addShutdownHook(null);
		}
	}*/
	
	static class MiShutdownHuk extends Thread {
		@Override
		public void run () {
			Connection con = Conexion . getConexion ();
			if ( conexion != null ) {
				try {
					System.out.println("Conexion cerrada");
					con.close ();
				} catch ( SQLException e) {
					e.printStackTrace ();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(Conexion.getConexion());
		System.out.println(Conexion.getConexion());
		
	}
}

