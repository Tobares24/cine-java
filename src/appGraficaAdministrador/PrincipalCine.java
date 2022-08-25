/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appGraficaAdministrador;

import appGraficaAdministrador.FrmAdministrarCartelera;
import appGraficaAdministrador.FrmCine;
import appGraficaAdministrador.FrmDefinirCartelera;
import appGraficaCliente.FrmReservar_Comprar;
import appGraficaCliente.JDlgConsultarCartelera;
import appGraficaCliente.JDlgFactura;
import capaLogica.Cliente;

/**
 *
 * @author steve
 */
public class PrincipalCine {

    /**
     * @param args the command line arguments
     */
    //Frames del módulo administrador
    public static FrmCine frmCine = new FrmCine();
    public static FrmAdministrarCartelera frmAdministrar = new FrmAdministrarCartelera();
    public static FrmDefinirCartelera frmDefinirCartelera = new FrmDefinirCartelera();
    public static FrmLogin frmLogin = new FrmLogin();
    public static JDlgEstadosSalas jdlgEstadosSalas = new JDlgEstadosSalas(frmCine, true);
    public static JDlgCantidadAsientosOcupados jdlgCantidadAsientosOcupados = new JDlgCantidadAsientosOcupados(frmCine, true);
    public static JDlgFactura jdlgFactura = new JDlgFactura(frmCine, true);
    public static Cliente clienteActual = new Cliente();

    //Frames del módulo cliente
    public static JDlgConsultarCartelera jdlgConsultarCartelera = new JDlgConsultarCartelera(frmCine, true);
    public static FrmReservar_Comprar frmReservarComprar = new FrmReservar_Comprar();

    public static void main(String[] args) {

        //Centra frames del módulo administrador
        frmAdministrar.setLocationRelativeTo(null);

        frmDefinirCartelera.setLocationRelativeTo(null);
        frmLogin.setLocationRelativeTo(null);

        //Centra los JDialog
        jdlgEstadosSalas.setLocationRelativeTo(null);
        jdlgCantidadAsientosOcupados.setLocationRelativeTo(null);
        jdlgFactura.setLocationRelativeTo(null);
        //Centra frames del módulo cliente
        jdlgConsultarCartelera.setLocationRelativeTo(null);
        frmReservarComprar.setLocationRelativeTo(null);

        //Centra el frame del menú principal
        frmCine.setLocationRelativeTo(null);
        frmCine.setVisible(true);

    }

}
