package ar.edu.unju.edm.service.imp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.repository.IClienteDAO;
import ar.edu.unju.edm.servicee.IClienteService;

@Service
@Qualifier("impmysql")

public class ClienteServiceMySQL implements IClienteService{

	@Autowired
	Cliente unCliente;
	@Autowired
	IClienteDAO clienteDAO;

	
	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		clienteDAO.save(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	public Cliente encontrarUnCliente(int dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void eliminarCliente(int dni) throws Exception{
		// TODO Auto-generated method stub
		Cliente eliminado = clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("no existe el cliente"));
		clienteDAO.delete(eliminado);
		
	}
	
}