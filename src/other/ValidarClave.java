/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other;



public class ValidarClave {

    private static int contador = 0;

    public static boolean validacion(String clave) {
        contador++;//Cantidad de intentos
        int lClave = Integer.parseInt(clave);//Conversión de String a int
        int unidades = 1, decenas = 1, centenas = 1, unidadesMillar = 1;//Variables para sacar los dígitos en esas posiciones


            unidades = lClave % 10;//Se extraen unidades
            decenas = (lClave / 10) % 10;//Se extraen decenas
            centenas = (lClave / 100) % 10;//Se extraen centenas
            unidadesMillar = lClave / 1000;//Se extraen unidades de millar
            
            //Cada dígito debe ser el doble del anterior
            //Si el doble tiene 2 dígitos se toma el dígito de las unidades
            if (((unidadesMillar * 2) % 10 == centenas) && ((centenas * 2) % 10 == decenas) && ((decenas * 2) % 10 == unidades)) {
                contador = 0;
                return true;
            }
        
        return false;
    }
    
    public static int getContador(){
        return contador;
    }
}
