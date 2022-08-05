package servidor.Repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import sop_corba.ControladorCancionIntPackage.CancionDTO;


public class CancionRepository implements CancionRepositoryInt
{  
    private final ArrayList<CancionDTO> listaCanciones;
    
    public CancionRepository()
    {
        this.listaCanciones= new ArrayList();
    }


    @Override
    public List<CancionDTO> listarCanciones() {
        System.out.println("Listando canciones");
        return this.listaCanciones;
    }
    
    private boolean almacenarArchivo(byte array[], String nombreCancion)
    {   int varIncremento=listaCanciones.size()+1;
        boolean bandera=true;
        try {
            File objFile= new File("misCanciones/copia"+nombreCancion+"_"+varIncremento+".mp3");// archivo donde se almacenara la canción
            OutputStream output= new FileOutputStream(objFile);
            output.write(array);// escribiendo la canción en el archivo
            } catch (FileNotFoundException ex) {
            bandera=false;
        } catch (IOException ex) {
             bandera=false;
        }
        
        return bandera;
    }
   
    @Override
    public boolean registrarCancion(CancionDTO objCancion) {
        
        boolean bandera;
        objCancion.id=(this.listaCanciones.size());
        bandera=this.almacenarArchivo(objCancion.getAudio(),objCancion.getTitulo());
        this.listaCanciones.add(objCancion);
        System.out.println("Archivo creado en el servidor de Respaldo");
        System.out.println("Metadatos del archivo: ");
        System.out.println("titulo: " + objCancion.getTitulo());
        System.out.println("Artista: " + objCancion.getArtista());
        //System.out.println("Tipo: " + objCancion.getTipo());
        System.out.println("tamano en KB: " + objCancion.getTamKB());
        return bandera;
    }
       
}
