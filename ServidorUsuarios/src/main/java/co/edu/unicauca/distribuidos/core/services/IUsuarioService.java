package co.edu.unicauca.distribuidos.core.services;

import java.util.List;

import co.edu.unicauca.distribuidos.core.models.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public Usuario findById(String id);

	public Usuario save(Usuario cliente);

	public Usuario update(String id, Usuario cliente);

	public boolean delete(String id);

	public Usuario Registrar(Usuario nuevoUsuario);

	public Usuario Login(String email, String contraseña);
	
	public boolean ValidarToken(String token);

	public boolean ExisteEmail(String email);

}
