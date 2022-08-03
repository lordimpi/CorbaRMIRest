package cliente.vista;

import cliente.utilidades.UtilidadesAudio;
import static cliente.utilidades.UtilidadesAudio.identificarExtencion;
import cliente.utilidades.UtilidadesConsola;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import servidor.DTO.CancionDTO;
import servidor.controladores.ControladorGestorCancionInt;

public class Menu {

    private final ControladorGestorCancionInt objRemoto;

    public Menu(ControladorGestorCancionInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Registrar Cancion");
            System.out.println("2. Listar Canciones");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1 ->
                    Opcion1();
                case 2 ->
                    Opcion2();
                case 3 ->
                    System.out.println("Salir...");
                default ->
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 3);
    }

    private void Opcion1() 
    {
        try
        {
               System.out.println("Ingrese el nombre de la canción: ");
               String nombrecancion=UtilidadesConsola.leerCadena();
               
               File file=new File(nombrecancion);
               if (file.exists()) 
               {                
                    if (identificarExtencion(nombrecancion).equals("mp3")) 
                    {

                        CancionDTO objCancion= UtilidadesAudio.leerMetadatos(nombrecancion);
                        if(objCancion!=null)
                        {
                          byte[] arrayBytesCancion=UtilidadesAudio.obtenerBytesCancion(nombrecancion);              
                          objCancion.setArrayBytes(arrayBytesCancion);

                           boolean valor = objRemoto.registrarCancion(objCancion);//invocación del método remoto
                           if(valor)
                                   System.out.println("Registro realizado satisfactoriamente...");
                           else
                                   System.out.println("no se pudo realizar el registro...");
                        }
                    } else {
                     System.out.println("El unico tipo de cancion que se acepta es mp3");                    
                 }                                   
            } else {
                System.out.println("El archivo "+nombrecancion+" NO Existe");                
            }   
        }
        catch(RemoteException e)
        {
                System.out.println("La operacion no se pudo completar, intente nuevamente..."+e.getMessage());
        } 
    }

    private void Opcion2() {
        try {

            ArrayList<CancionDTO> canciones = objRemoto.listarCanciones();
            if (!canciones.isEmpty()) {
                System.out.println("==Canciones==");
                for (CancionDTO cancion : canciones) {
                    System.out.println("Id: " + cancion.getId());
                    System.out.println("Titulo " + cancion.getTitulo());
                    System.out.println("Artista " + cancion.getArtista());
                    System.out.println("Tipo: " + cancion.getTipo());
                    System.out.println("Tamaño " + cancion.getTamKB() + " KB");
                    System.out.println("");
                }
            } else {
                System.out.println("No hay canciones registradas");
            }

        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }

    }

}
