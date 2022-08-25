package capaLogica;

public class Cine {

    private static Pelicula[] arregloPelicula = new Pelicula[3];
    private static Cliente[][][] matrizSalas = new Cliente[3][10][8];
    private int indice = 0;

    public Cine() {

    }

    //Retorna el contenido del arreglo
    public static Pelicula[] getArregloPelicula() {
        return arregloPelicula;
    }

    //Retorna la matriz de las salas con los objetos clientes
    public static Cliente[][][] getMatrizSalas() {
        return matrizSalas;
    }

    //Utilizar métodos estáticos para no instanciar
    //Reservación de la película
    public boolean reservacion(int sala, int fila, int columna, Cliente cliente) {

        if (matrizSalas[sala][fila][columna] != null) {
            return false;
        } else {
            matrizSalas[sala][fila][columna] = cliente;
            return true;
        }
    }

    //Elimina la reservacion en una posición dada
    public void eliminarReservacion(int sala, int fila, int columna) {
        matrizSalas[sala][fila][columna] = null;
    }

    // Libera los espacios que no han sido reservados por el cliente, por ejemplo que cerró la ventana sin darle confirmar
    public void liberarReservacion(int sala) {
        for (int i = 0; i < matrizSalas[sala].length; i++) {
            for (int j = 0; j < matrizSalas[sala][i].length; j++) {
                if (matrizSalas[sala][i][j] == null) {
                    continue;
                }

                if (matrizSalas[sala][i][j].getNombreCliente().equals("")) {
                    matrizSalas[sala][i][j] = null;
                }
            }
        }
    }

    // Confirma la reservación del cliente almacenando todos sus datos
    public void confirmarReservacion(Cliente cliente, int sala, int fila, int columna) {
        matrizSalas[sala][fila][columna] = cliente;
    }

    // Retorna true si esa celda de la matriz está ocupada o está disponible
    public boolean estaDisponible(int sala, int fila, int columna) {
        return matrizSalas[sala][fila][columna] == null;
    }

    //Método que agrega la película al arreglo y corrobora que no se agreguen más de 3 películas
    public boolean agregaPelicula(Pelicula pelicula) {
        if (indice <= arregloPelicula.length - 1) {
            arregloPelicula[indice] = pelicula;
            indice++;
            return noSeRepiten(pelicula.getNombrePelicula());
        }
        return false;

    }

    //Método que comprueba que no hayan películas repetidas
    public boolean noSeRepiten(String nombrePelicula) {

        for (int i = 0; i < arregloPelicula.length; i++) {
            if (arregloPelicula[i] != null) {
                if (arregloPelicula[i].getNombrePelicula().equalsIgnoreCase(nombrePelicula)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
   * Este metodo recibe como parametro el nombre de una pelicula y 
   * retorna un entero indicando en que sala se esta proyectando
     */
    public Pelicula obtenerSala(String pelicula) {
        for (int i = 0; i <= indice; i++) {
            if (pelicula.equals(arregloPelicula[i].getNombrePelicula())) {
                return arregloPelicula[i];
            }
        }

        return null;
    }

    //Reporte de la cantidad de asientos ocupados de la sala 1
    public int asientosSala1() {
        int contador = 0;
        for (int i = 0; i < matrizSalas[0].length; i++) {
            for (int j = 0; j < matrizSalas[0][i].length; j++) {
                if (matrizSalas[0][i][j] != null) {
                    contador++;
                }
            }
        }
        return contador;
    }

    //Reporte de la cantidad de asientos ocupados de la sala 2
    public int asientosSala2() {
        int contador = 0;
        for (int i = 0; i < matrizSalas[1].length; i++) {
            for (int j = 0; j < matrizSalas[1][i].length; j++) {
                if (matrizSalas[1][i][j] != null) {
                    contador++;
                }
            }
        }
        return contador;
    }

    //Reporte de la cantidad de asientos ocupados en la sala 3
    public int asientosSala3() {
        int contador = 0;
        for (int i = 0; i < matrizSalas[2].length; i++) {
            for (int j = 0; j < matrizSalas[2][i].length; j++) {
                if (matrizSalas[2][i][j] != null) {
                    contador++;
                }
            }
        }
        return contador;
    }

}
