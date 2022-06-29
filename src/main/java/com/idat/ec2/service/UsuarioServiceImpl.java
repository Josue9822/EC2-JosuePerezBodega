package com.idat.ec2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.ec2.model.Usuarios;
import com.idat.ec2.repository.UsuariosRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuariosRepository repo;

	@Override
	public void guardarUsuarios(Usuarios usuario) {
		
		repo.save(usuario);
		
	}

	@Override
	public void actualizarUsuarios(Usuarios usuario) {
		
		repo.saveAndFlush(usuario);
		
	}

	@Override
	public void eliminarUsuario(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public List<Usuarios> listarUsuarios() {
		
		return repo.findAll();
	}

	@Override
	public Usuarios obtenerUsuarios(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

}
