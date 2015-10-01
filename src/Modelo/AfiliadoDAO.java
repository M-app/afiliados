package Modelo;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class AfiliadoDAO {
    String nombreAfiliado;
    String genero; 
    String fecha; 
    int idDepartamento; 
    int idMunicipio; 
    
    //variables de conexion
    Conexion conexion = new Conexion(); 
    Connection conn;
    
    public void nuevoAfiliado(String nombreAfiliado,String genero, String fecha, int idDepartamento, int idMunicipio)
    {
        this.nombreAfiliado = nombreAfiliado; 
        this.genero = genero; 
        this.fecha = fecha; 
        this.idDepartamento = idDepartamento; 
        this.idMunicipio = idMunicipio; 
        
        try
        {
            conexion.conectar();
            conn = conexion.obtenerConexion(); 
            Statement comando = conn.createStatement(); 
            comando.executeUpdate("insert into afiliado values(null,'"+nombreAfiliado+"','"+genero+"','"+fecha+"','"+idDepartamento+"','"+idMunicipio+"')");
            conn.close();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar ingresar un nuevo afiliado " + e.getMessage());
        }
    }
}
