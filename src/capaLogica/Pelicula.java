/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaLogica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author steve
 */
public class Pelicula {

    private String nombrePelicula;
    private int numeroSala;
    private Date fecha;
    private int hora;
    private int minutos;
    private final int CLIENTE_REGULAR = 2800;
    private final int ADULTO_MAYOR = 2300;
    private final int NINNIO = 2000;
    private String urlImagen;

    public Pelicula(String nombrePelicula, int numeroSala, Date fecha, int hora, int minutos, String urlImagen) {
        this.nombrePelicula = nombrePelicula;
        this.numeroSala = numeroSala;
        this.fecha = fecha;
        this.hora = hora;
        this.minutos = minutos;
        this.urlImagen = urlImagen;
    }

    public Pelicula() {
        this.nombrePelicula = "";
        this.numeroSala = 0;
        this.fecha = null;
        this.hora = 0;
        this.minutos = 0;
        this.urlImagen = "";
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    @Override
    public String toString() {
        return this.nombrePelicula + " \nSala: " + this.numeroSala;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getCLIENTE_REGULAR() {
        return CLIENTE_REGULAR;
    }

    public int getADULTO_MAYOR() {
        return ADULTO_MAYOR;
    }

    public int getNINNIO() {
        return NINNIO;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String formatoFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

    //Valida que el usuario haga la reservación 6 horas antes de que inicie la película
    public void restriccionHoras() {
        Calendar calendario = Calendar.getInstance();

        int pHoras = calendario.get(Calendar.HOUR_OF_DAY);
        int pMinutos = calendario.get(Calendar.MINUTE);

    }

}
