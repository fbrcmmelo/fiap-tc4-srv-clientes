package br.com.fiap.fiaptc4srvclientes.gateway.database.jpa.repository;


import java.util.Optional;

import br.com.fiap.fiaptc4srvclientes.gateway.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByCpf(String cpf);
}