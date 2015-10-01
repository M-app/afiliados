package Controladores;

import Modelo.AfiliadoDAO;
import Modelo.Departamento;
import Modelo.DepartamentoDAO;
import Modelo.Municipio;
import Modelo.MunicipioDAO;
import Vistas.FrmPrincipal;
import java.awt.event.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;



public class ControladorFrmPrincipal  implements MouseListener, KeyListener , ActionListener, ItemListener{
    
    public FrmPrincipal frmPrincipal;
    DepartamentoDAO departamento = new DepartamentoDAO();
    MunicipioDAO municipio = new MunicipioDAO();
    ArrayList<Departamento> listaDepartamentos = new ArrayList();
    ArrayList<Municipio> listaMunicipios = new ArrayList();
    public ControladorFrmPrincipal(FrmPrincipal frmPrincipal)
    {
        this.frmPrincipal = frmPrincipal;
        // todos los cmd
        this.frmPrincipal.cmdAgregarDepartamento.addMouseListener(this);
        this.frmPrincipal.cmdVerDepartamento.addMouseListener(this);
        this.frmPrincipal.cmdAgregarMunicipio.addMouseListener(this);
        this.frmPrincipal.cmdVerMunicipio.addMouseListener(this);
        this.frmPrincipal.cmdAgregarAfiliado.addMouseListener(this);
        this.frmPrincipal.cmdVerInformacion.addMouseListener(this);
        // Panel buscar
        this.frmPrincipal.jcbBuscar.addMouseListener(this);
        this.frmPrincipal.txtBuscar.addKeyListener(this);
        
        // Panel AgregarDepartamento
        this.frmPrincipal.btnAgregarDepartamento.addActionListener(this);
        
        // Panel AgregarMunicipio
        this.frmPrincipal.btnAgregarMunicipio.addActionListener(this);
        
        // Panel AgregarAfiliado
        this.frmPrincipal.btnAgregarAfiliado.addActionListener(this);
        
        // Combo Box
        llenarComboBoxDepartamento();
        this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.addItemListener(this);
        this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.addItemListener(this);
        this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.addItemListener(this);
        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.addItemListener(this);
        this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.addMouseListener(this);
        this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.addMouseListener(this);
        this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.addMouseListener(this);
        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.addMouseListener(this);
        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItemListener(this);
        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItemListener(this);
        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addMouseListener(this);
        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addMouseListener(this);
        }
    
    private void llenarComboBoxDepartamento()
    {
        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.removeAllItems();
        this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.removeAllItems();
        this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.removeAllItems();
        this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.removeAllItems();
        listaDepartamentos.clear();
        ResultSet datosObtenidos;
        datosObtenidos = departamento.busquedaDepartamentos();
        
       
        try
        {
            while(datosObtenidos.next())
            {
                Departamento nuevoDepartamento = new Departamento(datosObtenidos.getInt("idDepartamento"),
                                                                    datosObtenidos.getString("nombreDepartamento"));
                listaDepartamentos.add(nuevoDepartamento);
            }
            for(int i = 0; i<listaDepartamentos.size();i++)
            {
                this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.addItem(listaDepartamentos.get(i).nombreDepartamento);
                this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.addItem(listaDepartamentos.get(i).nombreDepartamento);
                this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.addItem(listaDepartamentos.get(i).nombreDepartamento);
                this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.addItem(listaDepartamentos.get(i).nombreDepartamento);
            }    
           this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.setSelectedIndex(-1);
           this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.setSelectedIndex(-1);
           this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.setSelectedIndex(-1);
           this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.setSelectedIndex(-1);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los datos: " + e.getMessage()); 
        }
        
    }
    
    
    
    public String fechaActual()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
       //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento)
        {
            listaMunicipios.clear();
             this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.removeAllItems();
                this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.removeAllItems();
        }
        if(e.getSource() == this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento)
        {
            listaMunicipios.clear();
             this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.removeAllItems();
                this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.removeAllItems();
        }
        if(e.getSource() == this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento)
        {
            listaMunicipios.clear();
             this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.removeAllItems();
                this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.removeAllItems();
        }
        if(e.getSource() == this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento)
        {
            listaMunicipios.clear();
             this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.removeAllItems();
                this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.removeAllItems();
        }
        if(e.getSource() == this.frmPrincipal.jcbBuscar )
        {
            this.frmPrincipal.pnlAgregarDepartamento.setVisible(false);
            this.frmPrincipal.pnlVerDepartamento.setVisible(false);
            this.frmPrincipal.pnlAgregarMunicipio.setVisible(false);
            this.frmPrincipal.pnlVerMunicipio.setVisible(false);
            this.frmPrincipal.pnlAgregarAfiliado.setVisible(false);
            this.frmPrincipal.pnlVerInformacionAfiliado.setVisible(false);
            this.frmPrincipal.pnlBuscar.setVisible(true);
            
        }
        if(e.getSource() == this.frmPrincipal.cmdAgregarDepartamento)
        {
            this.frmPrincipal.pnlAgregarDepartamento.setVisible(true);
            this.frmPrincipal.pnlVerDepartamento.setVisible(false);
            this.frmPrincipal.pnlAgregarMunicipio.setVisible(false);
            this.frmPrincipal.pnlVerMunicipio.setVisible(false);
            this.frmPrincipal.pnlAgregarAfiliado.setVisible(false);
            this.frmPrincipal.pnlVerInformacionAfiliado.setVisible(false);
            this.frmPrincipal.pnlBuscar.setVisible(false);
            
        }
        if(e.getSource() == this.frmPrincipal.cmdVerDepartamento)
        {
            this.frmPrincipal.pnlAgregarDepartamento.setVisible(false);
            this.frmPrincipal.pnlVerDepartamento.setVisible(true);
            this.frmPrincipal.pnlAgregarMunicipio.setVisible(false);
            this.frmPrincipal.pnlVerMunicipio.setVisible(false);
            this.frmPrincipal.pnlAgregarAfiliado.setVisible(false);
            this.frmPrincipal.pnlVerInformacionAfiliado.setVisible(false);
            this.frmPrincipal.pnlBuscar.setVisible(false);
             
            
        }
        if(e.getSource() == this.frmPrincipal.cmdAgregarMunicipio)
        {
            this.frmPrincipal.pnlAgregarDepartamento.setVisible(false);
            this.frmPrincipal.pnlVerDepartamento.setVisible(false);
            this.frmPrincipal.pnlAgregarMunicipio.setVisible(true);
            this.frmPrincipal.pnlVerMunicipio.setVisible(false);
            this.frmPrincipal.pnlAgregarAfiliado.setVisible(false);
            this.frmPrincipal.pnlVerInformacionAfiliado.setVisible(false);
            this.frmPrincipal.pnlBuscar.setVisible(false);
            
        }
        if(e.getSource() == this.frmPrincipal.cmdVerMunicipio)
        {
            this.frmPrincipal.pnlAgregarDepartamento.setVisible(false);
            this.frmPrincipal.pnlVerDepartamento.setVisible(false);
            this.frmPrincipal.pnlAgregarMunicipio.setVisible(false);
            this.frmPrincipal.pnlVerMunicipio.setVisible(true);
            this.frmPrincipal.pnlAgregarAfiliado.setVisible(false);
            this.frmPrincipal.pnlVerInformacionAfiliado.setVisible(false);
            this.frmPrincipal.pnlBuscar.setVisible(false);
           
        }
        if(e.getSource() == this.frmPrincipal.cmdAgregarAfiliado)
        {
            this.frmPrincipal.pnlAgregarDepartamento.setVisible(false);
            this.frmPrincipal.pnlVerDepartamento.setVisible(false);
            this.frmPrincipal.pnlAgregarMunicipio.setVisible(false);
            this.frmPrincipal.pnlVerMunicipio.setVisible(false);
            this.frmPrincipal.pnlAgregarAfiliado.setVisible(true);
            this.frmPrincipal.pnlVerInformacionAfiliado.setVisible(false);
            this.frmPrincipal.pnlBuscar.setVisible(false);
           
        }
        if(e.getSource() == this.frmPrincipal.cmdVerInformacion)
        {
            this.frmPrincipal.pnlAgregarDepartamento.setVisible(false);
            this.frmPrincipal.pnlVerDepartamento.setVisible(false);
            this.frmPrincipal.pnlAgregarMunicipio.setVisible(false);
            this.frmPrincipal.pnlVerMunicipio.setVisible(false);
            this.frmPrincipal.pnlAgregarAfiliado.setVisible(false);
            this.frmPrincipal.pnlVerInformacionAfiliado.setVisible(true);
            this.frmPrincipal.pnlBuscar.setVisible(false);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.frmPrincipal.pnlAgregarDepartamento.setVisible(false);
        this.frmPrincipal.pnlVerDepartamento.setVisible(false);
        this.frmPrincipal.pnlAgregarMunicipio.setVisible(false);
        this.frmPrincipal.pnlVerMunicipio.setVisible(false);
        this.frmPrincipal.pnlAgregarAfiliado.setVisible(false);
        this.frmPrincipal.pnlVerInformacionAfiliado.setVisible(false);
        this.frmPrincipal.pnlBuscar.setVisible(true);
        this.frmPrincipal.lblBuscar.setText("Buscando " + this.frmPrincipal.txtBuscar.getText() + " ...");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.frmPrincipal.btnAgregarDepartamento)
        {
            DepartamentoDAO departamento = new DepartamentoDAO();
            departamento.nuevoDepartamento(this.frmPrincipal.txtNombreDepartamento.getText());
            llenarComboBoxDepartamento();
        }
        if(e.getSource() == this.frmPrincipal.btnAgregarMunicipio)
        {
            MunicipioDAO municipio = new MunicipioDAO();
            municipio.nuevoMunicipio(this.frmPrincipal.txtNombreMunicipio.getText(), this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.getSelectedIndex()+1);
        }
        if(e.getSource() == this.frmPrincipal.btnAgregarAfiliado)
        {
            AfiliadoDAO afiliado = new AfiliadoDAO();
            String genero = ""; 
            if(this.frmPrincipal.rbtnHombre.isSelected())
            {
                genero = "Hombre";
            }
            else if(this.frmPrincipal.rbtnMujer.isSelected())
            {
                genero = "Mujer";
            }
            afiliado.nuevoAfiliado(this.frmPrincipal.txtNombreAfiliado.getText(), genero, fechaActual(), this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.getSelectedIndex() +1, this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.getSelectedIndex()+1);
        }
        if(e.getSource() == this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento)
        {
            
            if(this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.getSelectedIndex() !=-1)
            {
                ResultSet datosObtenidos;
                datosObtenidos = municipio.busquedaMunicipios(this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.getSelectedIndex()+1,listaMunicipios);
                listaMunicipios.clear();
                this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.removeAllItems();
            this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.removeAllItems();
                try
                {
                    while(datosObtenidos.next())
                    {
                        Municipio nuevoMunicipio = new Municipio(datosObtenidos.getInt("idDepartamento"),
                                                                            datosObtenidos.getInt("idMunicipio"),
                                                                                datosObtenidos.getString("nombreMunicipio"));
                        listaMunicipios.add(nuevoMunicipio);
                    }
                    for(int i = 0; i<listaMunicipios.size();i++)
                    {
                        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                    }    
                    this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.setSelectedIndex(-1);
                    this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.setSelectedIndex(-1);
                }catch(Exception ev)
                {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los __datos: " + ev.getMessage()); 
                }
            
        }
    }
        if(e.getSource() == this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento)
        {
            
            if(this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.getSelectedIndex() !=-1)
            {
                ResultSet datosObtenidos;
                datosObtenidos = municipio.busquedaMunicipios(this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.getSelectedIndex()+1,listaMunicipios);
                listaMunicipios.clear();
                this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.removeAllItems();
                this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.removeAllItems();
                try
                {
                    while(datosObtenidos.next())
                    {
                        Municipio nuevoMunicipio = new Municipio(datosObtenidos.getInt("idDepartamento"),
                                                                            datosObtenidos.getInt("idMunicipio"),
                                                                                datosObtenidos.getString("nombreMunicipio"));
                        listaMunicipios.add(nuevoMunicipio);
                    }
                    for(int i = 0; i<listaMunicipios.size();i++)
                    {
                        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                    }    
                    this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.setSelectedIndex(-1);
                    this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.setSelectedIndex(-1);
                }catch(Exception ev)
                {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los datos: " + ev.getMessage()); 
                }
            
        }
    }
        if(e.getSource() == this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento)
        {
            
            if(this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.getSelectedIndex() !=-1)
            {
                ResultSet datosObtenidos;
                datosObtenidos = municipio.busquedaMunicipios(this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.getSelectedIndex()+1,listaMunicipios);
                listaMunicipios.clear();
                this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.removeAllItems();
            this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.removeAllItems();
                try
                {
                    while(datosObtenidos.next())
                    {
                        Municipio nuevoMunicipio = new Municipio(datosObtenidos.getInt("idDepartamento"),
                                                                            datosObtenidos.getInt("idMunicipio"),
                                                                                datosObtenidos.getString("nombreMunicipio"));
                        listaMunicipios.add(nuevoMunicipio);
                    }
                    for(int i = 0; i<listaMunicipios.size();i++)
                    {
                        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                    }    
                    this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.setSelectedIndex(-1);
                    this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.setSelectedIndex(-1);
                }catch(Exception ev)
                {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los datos: " + ev.getMessage()); 
                }
    
        }
    }
        
        if(e.getSource() == this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento)
        {
            
            if(this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.getSelectedIndex() !=-1)
            {
                
                ResultSet datosObtenidos;
                datosObtenidos = municipio.busquedaMunicipios(this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.getSelectedIndex()+1,listaMunicipios);
                listaMunicipios.clear();
                this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.removeAllItems();
            this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.removeAllItems();
                try
                {
                    while(datosObtenidos.next())
                    {
                        Municipio nuevoMunicipio = new Municipio(datosObtenidos.getInt("idDepartamento"),
                                                                            datosObtenidos.getInt("idMunicipio"),
                                                                                datosObtenidos.getString("nombreMunicipio"));
                        listaMunicipios.add(nuevoMunicipio);
                    }
                    for(int i = 0; i<listaMunicipios.size();i++)
                    {
                        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                    }    
                    this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.setSelectedIndex(-1);
                    this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.setSelectedIndex(-1);
                }catch(Exception ev)
                {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los datos: " + ev.getMessage()); 
                }
    
        }
    }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(e.getSource() == this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento)
        {
            
            if(this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.getSelectedIndex() !=-1)
            {
                ResultSet datosObtenidos;
                datosObtenidos = municipio.busquedaMunicipios(this.frmPrincipal.jcbAgregarAfiliadoSeleccionarDepartamento.getSelectedIndex()+1,listaMunicipios);
                listaMunicipios.clear();
                try
                {
                    while(datosObtenidos.next())
                    {
                        Municipio nuevoMunicipio = new Municipio(datosObtenidos.getInt("idDepartamento"),
                                                                            datosObtenidos.getInt("idMunicipio"),
                                                                                datosObtenidos.getString("nombreMunicipio"));
                        listaMunicipios.add(nuevoMunicipio);
                    }
                    for(int i = 0; i<listaMunicipios.size();i++)
                    {
                        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                    }    
                    this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.setSelectedIndex(-1);
                    this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.setSelectedIndex(-1);
                }catch(Exception ev)
                {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los __datos: " + ev.getMessage()); 
                }
            
        }
    }
        if(e.getSource() == this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento)
        {
            
            if(this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.getSelectedIndex() !=-1)
            {
                ResultSet datosObtenidos;
                datosObtenidos = municipio.busquedaMunicipios(this.frmPrincipal.jcbAgregarMunicipioSeleccionarDepartamento.getSelectedIndex()+1,listaMunicipios);
                listaMunicipios.clear();
                try
                {
                    while(datosObtenidos.next())
                    {
                        Municipio nuevoMunicipio = new Municipio(datosObtenidos.getInt("idDepartamento"),
                                                                            datosObtenidos.getInt("idMunicipio"),
                                                                                datosObtenidos.getString("nombreMunicipio"));
                        listaMunicipios.add(nuevoMunicipio);
                    }
                    for(int i = 0; i<listaMunicipios.size();i++)
                    {
                        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                    }    
                    this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.setSelectedIndex(-1);
                    this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.setSelectedIndex(-1);
                }catch(Exception ev)
                {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los datos: " + ev.getMessage()); 
                }
            
        }
    }
        if(e.getSource() == this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento)
        {
            
            if(this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.getSelectedIndex() !=-1)
            {
                ResultSet datosObtenidos;
                datosObtenidos = municipio.busquedaMunicipios(this.frmPrincipal.jcbVerDepartamentoSeleccionarDepartamento.getSelectedIndex()+1,listaMunicipios);
                listaMunicipios.clear();
                try
                {
                    while(datosObtenidos.next())
                    {
                        Municipio nuevoMunicipio = new Municipio(datosObtenidos.getInt("idDepartamento"),
                                                                            datosObtenidos.getInt("idMunicipio"),
                                                                                datosObtenidos.getString("nombreMunicipio"));
                        listaMunicipios.add(nuevoMunicipio);
                    }
                    for(int i = 0; i<listaMunicipios.size();i++)
                    {
                        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                    }    
                    this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.setSelectedIndex(-1);
                    this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.setSelectedIndex(-1);
                }catch(Exception ev)
                {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los datos: " + ev.getMessage()); 
                }
    
        }
    }
        
        if(e.getSource() == this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento)
        {
            
            if(this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.getSelectedIndex() !=-1)
            {
                
                ResultSet datosObtenidos;
                datosObtenidos = municipio.busquedaMunicipios(this.frmPrincipal.jcbVerMunicipioSeleccionarDepartamento.getSelectedIndex()+1,listaMunicipios);
                listaMunicipios.clear();
                try
                {
                    while(datosObtenidos.next())
                    {
                        Municipio nuevoMunicipio = new Municipio(datosObtenidos.getInt("idDepartamento"),
                                                                            datosObtenidos.getInt("idMunicipio"),
                                                                                datosObtenidos.getString("nombreMunicipio"));
                        listaMunicipios.add(nuevoMunicipio);
                    }
                    for(int i = 0; i<listaMunicipios.size();i++)
                    {
                        this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                        this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.addItem(listaMunicipios.get(i).nombreMunicipio);
                    }    
                    this.frmPrincipal.jcbAgregarAfiliadoSeleccionarMunicipio.setSelectedIndex(-1);
                    this.frmPrincipal.jcbVerMunicipioSeleccionarMunicipio.setSelectedIndex(-1);
                }catch(Exception ev)
                {
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error al tratar de buscar los datos: " + ev.getMessage()); 
                }
    
        }
    }

    } 
    
}
