/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import capaLogica.*;
import javax.swing.JOptionPane;

/**
 *
 * @author steve
 */
public class Validaciones {

    private static String errorMessage;

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        Validaciones.errorMessage = errorMessage;
    }

    public static boolean hayNulos(String nombrePelicula, String hora, String minutos, String urlImagen) {
        if (nombrePelicula.isEmpty() || hora.isEmpty() || hora.isEmpty()) {
            Validaciones.setErrorMessage("Hay datos nulos");
            return true;
        }

        if (urlImagen == null) {
            Validaciones.setErrorMessage("Debe seleccionar una foto");
            return true;
        }

        return false;
    }

    public static boolean validaFormato(String nombrePelicula) {
        if (!(nombrePelicula.matches("[a-zA-z]{3,20}"))) {
            Validaciones.setErrorMessage("El formato del nombre de la película debe ser de al menos 3 letras");
            return true;
        }
        return false;
    }

    public static boolean esEnteroYFormatoCorrecto(String hora, String minutos) {
        int vHora = Integer.parseInt(hora);
        int vMinutos = Integer.parseInt(minutos);

        try {
            int vHoraValidar = Integer.parseInt(hora);
            int vMinutosValidar = Integer.parseInt(minutos);
        } catch (Exception e) {
            Validaciones.setErrorMessage("Debe digitar solo datos numéricos");
            return true;
        }

        if (vHora > 23) {
            Validaciones.setErrorMessage("El formato de la hora es incorrecta, debe ser entre 00 y 23 horas");
            return true;
        }

        if (vMinutos > 59) {
            Validaciones.setErrorMessage("El formato de los minutos es incorrecto, debe ser entre 00 y 59");
            return true;
        }
        return false;
    }

    public static boolean hayNulosCompra(String nombre, String numeroTarjeta, String codigoSeguridad, String asientos) {

        if (nombre.isEmpty()) {
            Validaciones.setErrorMessage("El nombre es requerido");
            return true;
        }

        //
        if (numeroTarjeta.isEmpty()) {
            Validaciones.setErrorMessage("El número de tarjeta es requerido");
            return true;
        }

        //
        if (codigoSeguridad.isEmpty()) {
            Validaciones.setErrorMessage("El código de seguridad es requerido");
            return true;
        }

        //
        if (asientos.isEmpty()) {
            Validaciones.setErrorMessage("Todavía no ha seleccionado ningún asiento. Por favor seleccione sus asientos");
            return true;
        }
        return false;
    }

    public static boolean validaTarjeta(boolean esVisa, boolean esMasterCard, String numTarjeta, String codigo,
            int annio, int mes) {
        Cliente cliente = new Cliente();

        //Valida que el código de seguridad sea digitado en números
        try {
            int codeSeguridad = Integer.parseInt(codigo);
        } catch (Exception e) {
            Validaciones.setErrorMessage("Debe digitar datos de tipo númerico");
            return true;
        }

        if (esVisa) {
            if (!numTarjeta.matches("[4]{1}[0-9]{15}")) {
                Validaciones.setErrorMessage("Lo sentimos, su número de tarjeta no es VISA");
                return true;
            }
            cliente.setTipotarjeta(TipoTarjeta.Visa);
        }
        if (esMasterCard) {
            if (!numTarjeta.matches("[51-55]{2}[0-9]{14}")) {
                Validaciones.setErrorMessage("Lo sentimos, su número de tarjeta no es MASTERCARD");
                return true;
            }
            cliente.setTipotarjeta(TipoTarjeta.Mastercard);
        }

        //Validación del formato de la tarjeta
        if (!numTarjeta.matches("[0-9]{16}")) {
            Validaciones.setErrorMessage("Formato de tarjeta inválido, vuelva a digitarla nuevamente");
            return true;
        }

        //Validación si el número de tarjeta es válido/inválido
        if (!cliente.validaTarjeta(numTarjeta)) {
            Validaciones.setErrorMessage("Número de tarjeta inválido, vuelva digitarla nuevamente");
            return true;
        }

        //Validación de la fecha de vencimiento
        if (!cliente.validacionFechaVencimiento(annio, mes)) {
            Validaciones.setErrorMessage("Verifique la fecha de caducidad que consta en la tarjeta, si ha expirado"
                    + "\npor favor, digite otro número de tarjeta");
            return true;
        }
        return false;
    }
}
