package capaLogica;

import java.util.Calendar;
import other.TipoTarjeta;
import appGraficaCliente.JDlgReservar;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

public class Cliente {

    Pelicula pelicula;
    private String nombreCliente;
    private int contador = 1000;
    private int numeroReservacion;
    private String numTarjeta;
    private TipoTarjeta tipotarjeta;

    //Fecha de vencimiento de la tarjeta
    private int annio;
    private int mes;

    private double precioTotal;

    private int totalBoletos;

    private String tiquetesComprados = "";
    private String formatoFecha = "";
    private String formatoHora = "";
    private String asientosReservados = JDlgReservar.getAsientosReservados();

    public Cliente(String nombreCliente, String numTarjeta, int annio, int mes, int codeSeguridad, double precioTotal) {
        this.nombreCliente = nombreCliente;
        this.numTarjeta = numTarjeta;
        this.annio = annio;
        this.mes = mes;
        this.precioTotal = 0;
        this.numeroReservacion = contador;
        this.setPrecioTotal(precioTotal);
        pelicula = new Pelicula();
        contador++;

    }

    public Cliente() {
        pelicula = new Pelicula();
        this.nombreCliente = "";
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getTotalBoletos() {
        return totalBoletos;
    }

    public void setTotalBoletos(int totalBoletos) {
        this.totalBoletos = totalBoletos;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setTipotarjeta(TipoTarjeta tipotarjeta) {
        this.tipotarjeta = tipotarjeta;
    }

    public TipoTarjeta getTipotarjeta() {
        return tipotarjeta;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setAsientosReservados(String asientosReservados) {
        this.asientosReservados = asientosReservados;
    }

    //Método de validación del número de tarjeta(algoritmo de luhn)
    public boolean validaTarjeta(String numTarjeta) {
        numTarjeta.trim();
        int[] digitosTarjeta;
        digitosTarjeta = new int[16];

        int suma = 0;
        int digitoUnidadesSuma = 0;
        int inverso;

        for (int i = 0; i < digitosTarjeta.length; i++) {
            digitosTarjeta[i] = Character.getNumericValue(numTarjeta.charAt(i));
        }
        int ultimoDigitoTarjeta = digitosTarjeta[15];
        for (int i = 0; i < digitosTarjeta.length - 1; i++) {
            inverso = digitosTarjeta[digitosTarjeta.length - i - 2];

            if (i % 2 == 0) {
                inverso = inverso * 2;
            }
            if (inverso > 9) {
                inverso = inverso - 9;
            }
            suma = suma + inverso;
        }
        suma = suma * 9;
        digitoUnidadesSuma = suma % 10;
        if (ultimoDigitoTarjeta == digitoUnidadesSuma) {
            return true;
        }
        return false;
    }

    //Método de validación para la fecha de vencimiento de la tarjeta
    public boolean validacionFechaVencimiento(int pAnio, int pMes) {
        Calendar calendario = Calendar.getInstance();
        int vMes = calendario.get(Calendar.MONTH) + 1;
        int vAnio = calendario.get(Calendar.YEAR);

        if (pAnio >= pAnio && pMes >= pMes) {
            return true;
        }
        return false;
    }

    //Retorna el formato de la hora(factura)
    public String formatoHora() {
        return pelicula.getHora() + ":" + pelicula.getMinutos();
    }

    // Método que calcula cuánto va a pagar el cliente
    public double totalPagar(int cantidadNino, int cantidadAdulto, int cantidadAdultoMayor) {
        int precioNinnio = pelicula.getNINNIO();
        int precioClienteRegular = pelicula.getCLIENTE_REGULAR();
        int precioAdultoMayor = pelicula.getADULTO_MAYOR();
        return cantidadNino * precioNinnio + cantidadAdulto * precioClienteRegular + cantidadAdultoMayor * precioAdultoMayor;
    }

    // Método que valida que el usuario no compre más de cuatro boletos
    public boolean validarCantidadBoletos(int cantidadNino, int cantidadAdulto, int cantidadAdultoMayor) {
        return cantidadNino + cantidadAdulto + cantidadAdultoMayor > 4;
    }

    //Cantidad total de los tiquetes seleccionados
    public int totalTiquetes(int cantidadNino, int cantidadAdulto, int cantidadAdultoMayor) {
        return totalBoletos = cantidadNino + cantidadAdulto + cantidadAdultoMayor;
    }

    //Validacón para escoger los boletos
    public boolean maximoTiquetesSeleccionados(int cantidadAdultoMayor, int cantidadNino, int cantidadAdulto) {
        return cantidadNino + cantidadAdulto + cantidadAdultoMayor == 4;
    }

    //Cantidad de tiquetes seleccionados(factura)
    public String cantTiquetesComprados(int cantidadNino, int cantidadAdulto, int cantidadAdultoMayor) {
        return tiquetesComprados = "\nTiquetes comprados: " + cantidadAdulto + " regular " + ", "
                + cantidadAdultoMayor + " adulto mayor " + ", " + cantidadNino + " niño ";
    }

    //Factura
    public String factura() {
        String vHilera = "";
        vHilera += ("\tCine ....\t"
                + "\n"
                + "\nNúmero de reservación: " + numeroReservacion
                + "\nFecha: " + pelicula.formatoFecha()
                + "\nNombre del cliente: " + nombreCliente
                + "\nTarjeta" + getTipotarjeta() + "No. " + "xxxx-xxxx-xxxx-" + numTarjeta.substring(12)
                + "\n"
                + "\nSala No. " + pelicula.getNumeroSala()
                + "\nPelícula: " + pelicula.getNombrePelicula()
                + "\nHorario: " + formatoHora()
                + "\nTiquetes comprados: " + totalBoletos
                + "\nAsientos seleccionados: " + asientosReservados.replace("-", "")
                + "\n"
                + "\n"
                + "\nTotal pagado: " + this.getPrecioTotal()
                + "\n"
                + "\n\t¡Qué disfruten su película!\t");

        return vHilera;
    }
}
