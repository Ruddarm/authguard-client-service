package com.authguard.authguard_client_service.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authguard.authguard_client_service.Model.ClientEntity;



public interface  ClientRepository extends  JpaRepository<ClientEntity, UUID> {
    
}
