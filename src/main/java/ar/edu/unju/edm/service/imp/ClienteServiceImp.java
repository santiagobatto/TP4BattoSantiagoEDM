package ar.edu.unju.edm.service.imp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.servicee.IClienteService;
import ar.edu.unju.edm.util.ListadoClientes;

@Service
@Qualifier("unImp")
public class ClienteServiceImp implements IClienteService{
	
	private List<Cliente> listadoClientes = ListadoClientes.clientes;
	
	@Autowired
	Cliente unCliente;

	
	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		
		//Fecha fechacumpleaños
		LocalDate fechaNac=unCliente.getFechaNacimiento();
		
		//Fecha actual
		LocalDate fechaHoy=LocalDate.now();
		Period periodo = Period.between(fechaNac, fechaHoy);
		unCliente.setEdad(periodo.getYears());		
		
		LocalDate ultimaCompra = unCliente.getFechaUltimaCompra();
        Period periodo1 = Period.between(ultimaCompra,fechaHoy );
        unCliente.setTiempoUltCompra("DD-MM-YY;" + periodo1.getDays() +"-"+ periodo1.getMonths() +"-"+ periodo1.getYears());

        //Tiempo faltante hasta el cumpleaños
        LocalDate nextBDay = fechaNac.withYear(fechaHoy.getYear());
        if (nextBDay.isBefore(fechaHoy) || nextBDay.isEqual(fechaHoy)) {
            nextBDay = nextBDay.plusYears(1);
        }
        
        Period p = Period.between(fechaHoy, nextBDay);
        
        unCliente.setDatosAdicionales("Restan " + p.getDays() + "dias"+ p.getMonths() + " meses, y " + p.getYears() +"años");
               
		
		listadoClientes.add(unCliente);
	}
	
	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return listadoClientes;
	}

	@Override
	public Cliente encontrarUnCliente(int dni) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listadoClientes.size(); i++){
		    if (listadoClientes.get(i).getNroDocumento() == dni) {
		    	unCliente = listadoClientes.get(i);
		    }
		}
		return unCliente;
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listadoClientes.size(); i++){
		    if (listadoClientes.get(i).getNroDocumento() == clienteModificado.getNroDocumento()) {
		    	listadoClientes.set(i, clienteModificado);
		    }
		}	
	}
	
	@Override
	public void eliminarCliente(int dni) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listadoClientes.size(); i++){
		    if (listadoClientes.get(i).getNroDocumento() == dni) {
		    	listadoClientes.remove(i);
		    }
		}
	}

}