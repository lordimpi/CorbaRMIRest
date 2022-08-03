
package servidor.Repositorios;

import java.util.ArrayList;
import servidor.DTO.CancionDTO;

public interface CancionRepositoryInt
{    
    public boolean registrarCancion(CancionDTO objCancion);   
    public ArrayList<CancionDTO> listarCanciones();
}


