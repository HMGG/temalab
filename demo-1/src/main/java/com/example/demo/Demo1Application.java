package com.example.demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {

		dbInit();
		SpringApplication.run(Demo1Application.class, args);
	
	}
	
	static Connection conn;
	
	static String dbuser="postgres";
	
	static String dbpass="jelszo";
	
	static String url = "jdbc:postgresql://localhost:5432/template1"; 
	
	private static void dbInit(){
		  try { 
			    Class.forName("org.postgresql.Driver"); 
			    
			    conn = DriverManager.getConnection(url, dbuser, dbpass); 
			    /* 
			    * Add the geometry types to the connection. Note that you 
			    * must cast the connection to the pgsql-specific connection 
			    * implementation before calling the addDataType() method. 
			    */
			    /*((org.postgresql.PGConnection)conn).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
			    ((org.postgresql.PGConnection)conn).addDataType("box3d",Class.forName("org.postgis.PGbox3d"));*/


			    Statement s = conn.createStatement(); 
			    s.execute("CREATE TABLE IF NOT EXISTS posts (id bigint, text varchar, userid bigint, PRIMARY KEY (id));");
			    s.execute("CREATE TABLE IF NOT EXISTS users (id bigint, name varchar, email varchar, PRIMARY KEY (id));");
			/*   ResultSet r = s.executeQuery("select geom,id from geomtable");
			    while( r.next() ) { 
			       
			      //Retrieve the geometry as an object then cast it to the geometry type. 
			      //Print things out. 
			       
			      PGgeometry geom = (PGgeometry)r.getObject(1); 
			      int id = r.getInt(2); 
			      System.out.println("Row " + id + ":");
			      System.out.println(geom.toString()); 
			    }*/
			    s.close(); 
			    conn.close();
			  } 
			catch( Exception e ) { 
			  e.printStackTrace(); 
			  } 
	}
}


