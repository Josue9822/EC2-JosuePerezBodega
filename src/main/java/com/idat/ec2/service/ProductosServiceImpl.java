package com.idat.ec2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.ec2.DTO.ProductosDTORequest;
import com.idat.ec2.DTO.ProductosDTOResponse;
import com.idat.ec2.model.Productos;
import com.idat.ec2.repository.ProductoRepository;


@Service
public class ProductosServiceImpl implements ProductosService {
	
	@Autowired
	ProductoRepository repo;

	@Override
	public void guardarProducto(ProductosDTORequest productos) {
		
		Productos p = new Productos();
		p.setProducto(productos.getProducto());
		p.setDescripcion(p.getDescripcion());
		p.setPrecio(productos.getPrecio());
		p.setStock(productos.getStock());
		repo.save(p);
		
	}

	@Override
	public void actualizarProducto(ProductosDTORequest productos) {

		Productos p = new Productos();
		p.setIdProducto(productos.getIdProducto());
		p.setProducto(productos.getProducto());
		p.setDescripcion(p.getDescripcion());
		p.setPrecio(productos.getPrecio());
		p.setStock(productos.getStock());
		
		repo.saveAndFlush(p);
		
	}

	@Override
	public void eliminarProducto(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public List<ProductosDTOResponse> listarProductos() {
		
		List<ProductosDTOResponse> listar = new ArrayList<>();
		ProductosDTOResponse dto = null;
		List<Productos> p = repo.findAll();
		
		for(Productos productos : p) {
			dto = new ProductosDTOResponse();
			dto.setDescripcion(productos.getDescripcion());
			dto.setProducto(productos.getProducto());
			dto.setPrecio(productos.getPrecio());
			dto.setStock(productos.getStock());
			dto.setIdProducto(productos.getIdProducto());
			
			listar.add(dto);
			
		}
		
		return listar;
	}

	@Override
	public ProductosDTOResponse obtenerProducto(Integer id) {
		
		Productos productos = repo.findById(id).orElse(null);
		ProductosDTOResponse dto = new ProductosDTOResponse();
		
		dto.setDescripcion(productos.getDescripcion());
		dto.setProducto(productos.getProducto());
		dto.setPrecio(productos.getPrecio());
		dto.setStock(productos.getStock());
		dto.setIdProducto(productos.getIdProducto());
		return dto;
	}
	

}
