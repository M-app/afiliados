
package Main;

import Controladores.ControladorFrmPrincipal;
import Vistas.FrmPrincipal;

public class main {
    
    public static void main(String args[])
    {
        
        
        FrmPrincipal frmPrincipal = new FrmPrincipal();
        ControladorFrmPrincipal controladorPrincipal = new ControladorFrmPrincipal(frmPrincipal);
        frmPrincipal.setVisible(true);
    }
    
}
