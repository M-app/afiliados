package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DepartamentoDAO {
    
    String nombreDepartamento;
    
    // variables de coneccion
    Conexion conexion = new Conexion();
    Connection conn;
    ResultSet consulta;
    
    public void nuevoDepartamento(String nombreDepartamento)
    {
        this.nombreDepartamento = nombreDepartamento;
        try
        {
            conexion.conectar();
            conn = conexion.obtenerConexion(); 
            Statement comando = conn.createStatement(); 
            comando.executeUpdate("insert into departamento() values(null,'"+nombreDepartamento+"')");
            conn.close();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de ingresar un nuevo departamento " + e.getMessage());
        }
    }
    public ResultSet busquedaDepartamentos()
    {
        try
        {
            conexion.conectar(); 
            conn = conexion.obtenerConexion();
            Statement comando = conn.createStatement(); 
            consulta = comando.executeQuery("select idDepartamento,nombreDepartamento from departamento");
            return consulta; 
        }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los datos:_ " + e.getMessage());
            }
        return consulta;
    }
    
}
