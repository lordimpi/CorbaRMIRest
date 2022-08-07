
package servidor.Repositorios;

import java.util.ArrayList;
import servidor.DTO.CancionDTOO;

public interface CancionRepositoryInt
{    
    public boolean registrarCancion(CancionDTOO objCancion);   
    public ArrayList<CancionDTOO> listarCanciones();
}


