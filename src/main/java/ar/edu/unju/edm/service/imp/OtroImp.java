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
@Qualifier("mysql")
public class OtroImp implements IClienteService{

	@Autowired
	Cliente unCliente;
	@Autowired
	IClienteDAO clienteDAO;
	
	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		/*
		LocalDate nacimiento = unCliente.getFechaNacimiento();
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(nacimiento, hoy);
		unCliente.setEdad(periodo.getYears());

		LocalDate ultimaCompra = unCliente.getFechaUltimaCompra();
        Period periodo1 = Period.between(ultimaCompra, hoy);
        unCliente.setTiempoTranscurrido("; T.ult.compra " + periodo1.getDays()+ "/" + periodo1.getMonths()+"/" + periodo1.getYears());

        LocalDate cumple = nacimiento.withYear(hoy.getYear());
        if (cumple.isBefore(hoy) || cumple.isEqual(hoy)) {
            cumple = cumple.plusYears(1);
        }
        Period periodo2 = Period.between(hoy, cumple);
        unCliente.setHastaCumple("Faltan " +periodo2.getDays() + " días " + "y " +periodo2.getMonths()+" meses");
        */
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
		return (List<Cliente>)clienteDAO.findAll();
	}

	@Override
	public Cliente encontrarUnCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		return clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("EL cliente no existe"));
	}

	@Override
	public void modificarCliente(Cliente unClienteModificado) throws Exception {
		// TODO Auto-generated method stub
		Cliente clienteAModificar = clienteDAO.findByNroDocumento(unClienteModificado.getNroDocumento()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		cambiarCliente(unClienteModificado, clienteAModificar);
		clienteDAO.save(clienteAModificar);
	}
	
	private void cambiarCliente(Cliente desde, Cliente hacia) {
		hacia.setNroDocumento(desde.getNroDocumento());
		hacia.setTipoDocumento(desde.getTipoDocumento());
		hacia.setFechaNacimiento(desde.getFechaNacimiento());
		hacia.setCodigoAreaTelefono(desde.getCodigoAreaTelefono());
		hacia.setNumTelefono(desde.getNumTelefono());
		hacia.setEmail(desde.getEmail());
	}
	
	@Override
	public void eliminarCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		Cliente clienteEliminar = clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}

}