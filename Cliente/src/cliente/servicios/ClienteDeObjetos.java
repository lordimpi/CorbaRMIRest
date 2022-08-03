package cliente.servicios;

import cliente.vista.Menu;
import interfaces.ControladorGestorCancionInt;
import utilidades.UtilidadesConsola;
import utilidades.UtilidadesRegistroC;

public class ClienteDeObjetos {

    private static ControladorGestorCancionInt objRemoto;

    public static void main(String[] args) {

        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();

        objRemoto = (ControladorGestorCancionInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "objServicioGestionCanciones");
        Menu objMenu = new Menu(objRemoto);
        objMenu.ejecutarMenuPrincipal();

    }

}
