package co.edu.unicauca.distribuidos.core.services;

import java.util.List;

import co.edu.unicauca.distribuidos.core.models.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public Usuario findById(Integer id);

	public Usuario save(Usuario cliente);

	public Usuario update(Integer id, Usuario cliente);

	public boolean delete(Integer id);

	public boolean Registrar(Usuario nuevoUsuario);
	
	public boolean ExisteEmail(String email);

}
