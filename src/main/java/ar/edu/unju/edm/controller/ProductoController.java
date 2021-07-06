package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.servicee.ProductoService;

@Controller
public class ProductoController {
	
	private static final Log LOGGER = LogFactory.getLog(ProductoController.class);
	
	@Autowired
	@Qualifier("mysql")
	ProductoService iProductoService;
	
	@GetMapping("/producto/mostrar")
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("producto");
	}

	
	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
		iProductoService.guardarProducto(nuevoProducto);
		System.out.println(iProductoService.obtenerTodosProductos().get(0).getMarca());
		return "redirect:/producto/mostrar";
		
	}
	
	@GetMapping("/ultimo")
	public String cargarUltimoProducto(Model model) {
		model.addAttribute("ultimoProducto", iProductoService.obtenerUltimoProducto());
		return("mostrar-ultimo");
	}
	
	@GetMapping("/volver")
	public String cargarNuevoProducto(Model model) {
		
		return("redirect:/producto");
	}
	
	@GetMapping("/producto/eliminarProducto/{id}")
	public String eliminarProducto(Model model, @PathVariable(name="id") int id) {		
		try {			
			iProductoService.eliminarProducto(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/producto/mostrar";
	}
	
	
	@GetMapping("/producto/editar/{codProducto}")
	public String editarProducto (Model model, @PathVariable(name="codProducto") int codigo) throws Exception {
		try {
			Producto productoEncontrado = iProductoService.encontrarProducto(codigo);
			model.addAttribute("unProducto", productoEncontrado);	
			model.addAttribute("editMode", "true");
		}

		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", iProductoService.crearProducto());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("producto");
	}


	@PostMapping("/producto/modificar")
	public String modificarProducto(@ModelAttribute("unProducto") Producto productoAModificar, Model model) {


		try {
			iProductoService.modificarProducto(productoAModificar);
			model.addAttribute("unProducto", new Producto());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoAModificar);			
			model.addAttribute("productos", iProductoService.obtenerTodosProductos());
			model.addAttribute("editMode","true");

		}

		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("producto");

	}
}