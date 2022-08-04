package co.edu.unicauca.distribuidos.core.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.Usuario;

@Service
public class UsuarioRepository {

	private ArrayList<Usuario> listaDeUsuarios;

	public UsuarioRepository() {
		this.listaDeUsuarios = new ArrayList<Usuario>();
		cargarClientes();
	}

	public List<Usuario> findAll() {
		System.out.println("Invocando a listarclientes");
		return this.listaDeUsuarios;
	}

	public Usuario findById(String id) {
		System.out.println("Invocando a consultar un cliente");
		Usuario objCliente = null;

		for (Usuario cliente : listaDeUsuarios) {
			if (cliente.getId() == id) {
				objCliente = cliente;
				break;
			}
		}

		return objCliente;
	}

	public Usuario save(Usuario cliente) {
		System.out.println("Invocando a almacenar cliente");
		Usuario objCliente = null;
		if (this.listaDeUsuarios.add(cliente)) {
			objCliente = cliente;
		}

		return objCliente;
	}

	public Usuario update(String id, Usuario cliente) {
		System.out.println("Invocando a actualizar un cliente");
		Usuario objCliente = null;

		for (int i = 0; i < this.listaDeUsuarios.size(); i++) {
			if (this.listaDeUsuarios.get(i).getId() == id) {
				this.listaDeUsuarios.set(i, cliente);
				objCliente = cliente;
				break;
			}
		}

		return objCliente;
	}

	public boolean delete(String id) {
		System.out.println("Invocando a eliminar un cliente");
		boolean bandera = false;

		for (int i = 0; i < this.listaDeUsuarios.size(); i++) {
			if (this.listaDeUsuarios.get(i).getId() == id) {
				this.listaDeUsuarios.remove(i);
				bandera = true;
				break;
			}
		}

		return bandera;
	}

	public Usuario Registrar(Usuario nuevoUsuario){
		System.out.println("Invocando a registrar usuario");
		if (ExisteEmail(nuevoUsuario.getEmail())) {
			System.out.println("Ya existe un usuario con el email ingresado");
			return null;
		}
		nuevoUsuario.setId(UUID.randomUUID().toString());
		nuevoUsuario.setToken(UUID.randomUUID().toString());
		nuevoUsuario.setCreateAt(new Date());
		listaDeUsuarios.add(nuevoUsuario);
		return nuevoUsuario;
	}

	public Usuario Login(String email, String contraseña){
		System.out.println("Invocando a Login");
		Usuario user = null;
		for (Usuario item : listaDeUsuarios) {
			if (item.getEmail().equals(email) && item.getContraseña().equals(contraseña)) {
				user = item;
			}
		}
		return user;
	}

	public boolean ExisteEmail(String email){
		for (Usuario item : listaDeUsuarios) {
			if (item.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public boolean ValidarToken(String token){
		for (Usuario item : listaDeUsuarios) {
			if (item.getToken().equals(token)) {
				return true;
			}
		}
		return false;
	}

	private void cargarClientes() {
		Usuario objCliente1 = new Usuario(UUID.randomUUID().toString(), "Juan", "Perez", "juan@unicauca.edu.co", new Date(),UUID.randomUUID().toString(),"oracle");
		this.listaDeUsuarios.add(objCliente1);
		Usuario objCliente2 = new Usuario(UUID.randomUUID().toString(), "Catalina", "Lopez", "catalina@unicauca.edu.co", new Date(),UUID.randomUUID().toString(),"oracle");
		this.listaDeUsuarios.add(objCliente2);
		Usuario objCliente3 = new Usuario(UUID.randomUUID().toString(), "Sandra", "Sanchez", "Sandra@unicauca.edu.co", new Date(), UUID.randomUUID().toString(),"oracle");
		this.listaDeUsuarios.add(objCliente3);

	}

}
