package ar.edu.unju.edm.servicee;

import java.util.List;

import ar.edu.unju.edm.model.Producto;

//@Service
public interface ProductoService {
	
	public void guardarProducto(Producto unProducto);
	public Producto crearProducto();
	public void modificarProducto(Producto productoAModificar);
	public Producto obtenerUnProducto(String nombreProducto);
	public List<Producto> obtenerTodosProductos();
	public Producto obtenerProductoNuevo();
	public Producto obtenerUltimoProducto();
	public void eliminarProducto(int id);
		
}