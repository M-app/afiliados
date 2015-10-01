package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class MunicipioDAO {
    
    String nombreMunicipio; 
    int idDepartamento;
    
    // variables de conexion
    Conexion conexion = new Conexion(); 
    Connection conn;
    ResultSet consulta; 
    
    public void nuevoMunicipio(String nombreMunicipio, int idDepartamento)
    {
        this.nombreMunicipio = nombreMunicipio; 
        this.idDepartamento = idDepartamento; 
        
        try
        {
            conexion.conectar();
            conn = conexion.obtenerConexion();
            Statement comando = conn.createStatement();
            comando.executeUpdate("insert into municipio values(null,'"+nombreMunicipio+"','"+idDepartamento+"')");
            conn.close();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error al ingresar un nuevo municipio " + e.getMessage()); 
        }
        
    }
    public ResultSet busquedaMunicipios(int idDepartamento, ArrayList<Municipio> listaMunicipios)
    {
        listaMunicipios.clear();
        try
        {
            conexion.conectar(); 
            conn = conexion.obtenerConexion();
            Statement comando = conn.createStatement(); 
            consulta = comando.executeQuery("select * from municipio where idDepartamento='"+idDepartamento+"'");
            return consulta; 
        }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los municipios " + e.getMessage());
            }
        return consulta;
    }
}
