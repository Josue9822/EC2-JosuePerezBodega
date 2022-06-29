package com.idat.ec2.service;

import java.util.List;


import com.idat.ec2.DTO.ProductosDTORequest;
import com.idat.ec2.DTO.ProductosDTOResponse;



public interface ProductosService {
	
	void guardarProducto(ProductosDTORequest productos);
	void actualizarProducto(ProductosDTORequest productos);
	void eliminarProducto(Integer id);
	List<ProductosDTOResponse> listarProductos();
	ProductosDTOResponse obtenerProducto(Integer id);

}
