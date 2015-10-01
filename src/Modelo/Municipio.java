package Modelo;


public class Municipio {
    
    public int idDepartamento; 
    public int idMunicipio; 
    public String nombreMunicipio;
    
    public Municipio(int idDepartamento,int idMunicipio, String nombreMunicipio)
    {
        this.idDepartamento = idDepartamento; 
        this.idMunicipio = idMunicipio; 
        this.nombreMunicipio = nombreMunicipio;
    }
}
