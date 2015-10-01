package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
    public static Connection conn; 
    
    public String conectar()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection("jdbc:mysql://localhost/afiliados","root",""); 
           
            return "Se ha conectado correctamente a la base de datos"; 
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null," NO Se ha conectado a la base de datos " + e.getMessage());
            return "Ha ocurrido un error al tratar de conectarse a la base de datos "+ e.getMessage(); 
        }
    }
    
    public Connection obtenerConexion()
    {
        return conn; 
    }

}
