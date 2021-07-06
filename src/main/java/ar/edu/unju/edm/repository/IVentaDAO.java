package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.edm.model.Venta;

public interface IVentaDAO extends CrudRepository <Venta, Integer> {

}