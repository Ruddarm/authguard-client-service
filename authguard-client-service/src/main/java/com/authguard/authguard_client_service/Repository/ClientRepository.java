package com.authguard.authguard_client_service.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authguard.authguard_client_service.Model.Entity.ClientEntity;



public interface  ClientRepository extends  JpaRepository<ClientEntity, UUID> {
    
    public boolean existsByEmail(String email);
    
    public Optional<ClientEntity> findByEmail(String email);

}
