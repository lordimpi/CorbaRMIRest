package co.edu.unicauca.distribuidos.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.Usuario;
import co.edu.unicauca.distribuidos.core.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioRepository servicioAccesoBaseDatos;

	@Override
	public List<Usuario> findAll() {
		return this.servicioAccesoBaseDatos.findAll();
	}

	@Override
	public Usuario findById(Integer id) {
		return this.servicioAccesoBaseDatos.findById(id);
	}

	@Override
	public Usuario save(Usuario cliente) {
		return this.servicioAccesoBaseDatos.save(cliente);
	}

	@Override
	public Usuario update(Integer id, Usuario cliente) {
		return this.servicioAccesoBaseDatos.update(id, cliente);
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}

	@Override
	public boolean Registrar(Usuario nuevoUsuario) {
		return this.servicioAccesoBaseDatos.Registrar(nuevoUsuario);
	}

	@Override
	public boolean ExisteEmail(String email) {
		return this.servicioAccesoBaseDatos.ExisteEmail(email);
	}
}
