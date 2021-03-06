package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.servicee.ProductoService;
import ar.edu.unju.edm.util.ListadoProductos;

@Service
public class ProductoServiceImp implements ProductoService {

	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImp.class);

	@Autowired
	Producto unProducto;

	private List<Producto> listaDeProductos = ListadoProductos.productos;

	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		System.out.println(unProducto.getNombre());
		listaDeProductos.add(unProducto);
		System.out.println(listaDeProductos.size());

		LOGGER.info("METHOD: ingresando a Guardar Producto");
		LOGGER.info("RESULT: guardado " + listaDeProductos.get(listaDeProductos.size() - 1).getNombre());
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProductos.size(); i++) {
			if (listaDeProductos.get(i).getCodProducto() == productoModificado.getCodProducto()) {
				listaDeProductos.set(i, productoModificado);
			}
		}
		
	}

	@Override
	public void eliminarProducto(int id) {
		List<Producto> listadoProductos = null;
		// TODO Auto-generated method stub
		for (int i = 0; i < listadoProductos.size(); i++){
		    if (listadoProductos.get(i).getCodProducto() == id ) {
		    	listadoProductos.remove(i);
		    
		    }
		}
	}

	@Override
	public Producto obtenerUnProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return listaDeProductos;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		int i = listaDeProductos.size() - 1;
		return listaDeProductos.get(i);
	}

	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto encontrarProducto(int codProducto) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProductos.size(); i++) {
			unProducto = listaDeProductos.get(i);
		}
		return unProducto;
	}	

}