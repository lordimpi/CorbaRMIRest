package servidor.Repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import servidor.DTO.CancionDTOO;

public class CancionRepository implements CancionRepositoryInt {

    private final ArrayList<CancionDTOO> listaCanciones;

    public CancionRepository() {
        this.listaCanciones = new ArrayList();
    }

    private boolean almacenarArchivo(byte array[]) {
        boolean bandera = true;
        try {
            File objFile = new File("misCanciones/cancion_" + (listaCanciones.size() + 1) + ".mp3");// archivo donde se almacenara la canción
            OutputStream output = new FileOutputStream(objFile);
            output.write(array);// escribiendo la canción en el archivo
        } catch (FileNotFoundException ex) {
            bandera = false;
        } catch (IOException ex) {
            bandera = false;
        }

        return bandera;
    }

    @Override
    public boolean registrarCancion(CancionDTOO objCancion) {

        boolean bandera;
        objCancion.setId(listaCanciones.size() + 1);
        bandera = this.almacenarArchivo(objCancion.getArrayBytes());
        this.listaCanciones.add(objCancion);
        System.out.println("");
        System.out.println("Archivo creado en el servidor de canciones");
        System.out.println("Metadatos del archivo: ");
        System.out.println("titulo: " + objCancion.getTitulo());
        System.out.println("Artista: " + objCancion.getArtista());
        System.out.println("Tipo: " + objCancion.getTipo());
        System.out.println("tamaño: " + objCancion.getTamKB() + " KB");
        System.out.println("");
        return bandera;
    }

    public ArrayList<CancionDTOO> listarCanciones() {
        return this.listaCanciones;
    }

}
