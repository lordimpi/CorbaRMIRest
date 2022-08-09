package cliente.vista;

import interfaces.ControladorGestorCancionInt;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import models.Usuario;
import servicios.UsuarioServices;
import servidor.DTO.CancionDTOO;
import utilidades.UtilidadesAudio;
import static utilidades.UtilidadesAudio.identificarExtencion;
import utilidades.UtilidadesConsola;

public class Menu {

    private final ControladorGestorCancionInt objRemoto;
    private final UsuarioServices objUsuarioServices = new UsuarioServices();
    private Usuario objUsuario;

    public Menu(ControladorGestorCancionInt objRemoto) {
        this.objRemoto = objRemoto;
    }

    public void ejecutarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("\t===Menu===");
            System.out.println("1. Registrase en el servidor de usuarios");
            System.out.println("2. Iniciar sesion");
            System.out.println("3. Ingresar y enviar datos de la cancion");
            System.out.println("4. Listar datos de las canciones registradas");
            System.out.println("5. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    Opcion1();
                    break;
                case 2:
                    Opcion2();
                    break;
                case 3:
                    Opcion3();
                    break;
                case 4:
                    Opcion4();
                    break;
                case 5:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }

        } while (opcion != 5);
    }

    private void Opcion1() {
        objUsuario = new Usuario();
        System.out.println("\nRegistrando un nuevo Usuario");
        System.out.println("Ingrese el nombre: ");
        objUsuario.setNombre(UtilidadesConsola.leerCadena());
        System.out.println("Ingrese el apellido: ");
        objUsuario.setApellido(UtilidadesConsola.leerCadena());
        System.out.println("Ingrese el correo: ");
        objUsuario.setEmail(UtilidadesConsola.leerCadena());
        System.out.println("Ingrese la contraseña: ");
        objUsuario.setContraseña(UtilidadesConsola.leerCadena());
        objUsuario = objUsuarioServices.registrarUsuario(objUsuario);
        if (objUsuario == null) {
            System.out.println("No se pudo registrar el usuario...\n");
            return;
        }
        System.out.println("Usuario registrado con exito.\n");
    }

    private void Opcion2() {
        objUsuario = new Usuario();
        System.out.println("\nLogin");
        System.out.println("Ingrese el correo: ");
        objUsuario.setEmail(UtilidadesConsola.leerCadena());
        System.out.println("Ingrese la contraseña: ");
        objUsuario.setContraseña(UtilidadesConsola.leerCadena());
        objUsuario = objUsuarioServices.Login(objUsuario);
        if (objUsuario == null) {
            System.out.println("Usuario o Contraseña invalidas...");
            return;
        }
        System.out.println("Bienvenido: " + objUsuario.getNombre() + "\n");
    }

    private void Opcion3() {

        if (objUsuario == null) {
            System.out.println("No ha iniciado sesion...");
            return;
        }

        try {
            System.out.println("Ingrese el nombre de la canción: ");
            String nombrecancion = UtilidadesConsola.leerCadena();

            File file = new File(nombrecancion);
            if (file.exists()) {
                if (identificarExtencion(nombrecancion).equals("mp3")) {

                    CancionDTOO objCancion = UtilidadesAudio.leerMetadatos(nombrecancion);
                    if (objCancion != null) {
                        byte[] arrayBytesCancion = UtilidadesAudio.obtenerBytesCancion(nombrecancion);
                        objCancion.setArrayBytes(arrayBytesCancion);

                        boolean valor = objRemoto.registrarCancion(objCancion, objUsuario.getToken());//invocación del método remoto
                        if (valor) {
                            System.out.println("Registro realizado satisfactoriamente...");
                        } else {
                            System.out.println("no se pudo realizar el registro...");
                        }
                    }
                } else {
                    System.out.println("El unico tipo de cancion que se acepta es mp3");
                }
            } else {
                System.out.println("El archivo " + nombrecancion + " NO Existe");
            }
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente..." + e.getMessage());
        }
    }

    private void Opcion4() {

        if (objUsuario == null) {
            System.out.println("No ha iniciado sesion...");
            return;
        }

        try {

            ArrayList<CancionDTOO> canciones = objRemoto.listarCanciones();
            if (!canciones.isEmpty()) {
                System.out.println("==Canciones==");
                for (CancionDTOO cancion : canciones) {
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
