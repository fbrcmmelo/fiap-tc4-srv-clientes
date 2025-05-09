package br.com.fiap.fiaptc4srvclientes.repository;

import br.com.fiap.fiaptc4srvclientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
