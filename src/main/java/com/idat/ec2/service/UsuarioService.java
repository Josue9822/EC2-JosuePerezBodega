package com.idat.ec2.service;

import java.util.List;


import com.idat.ec2.model.Usuarios;


public interface UsuarioService {
	

	void guardarUsuarios(Usuarios usuario);
	void actualizarUsuarios(Usuarios usuario);
	void eliminarUsuario(Integer id);
	List<Usuarios> listarUsuarios();
	Usuarios obtenerUsuarios(Integer id);

}
