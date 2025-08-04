package com.authguard.authguard_client_service.Service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authguard.authguard_client_service.DTO.ClientRequest;
import com.authguard.authguard_client_service.DTO.ClientResponse;
import com.authguard.authguard_client_service.Model.ClientEntity;
import com.authguard.authguard_client_service.Repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public ClientResponse saveClient(ClientRequest clientRequest) {
        ClientEntity clientEntity = modelMapper.map(clientRequest, ClientEntity.class);
        clientEntity.setHashPassword(passwordEncoder.encode(clientRequest.getPassword()));
        clientRepository.save(clientEntity);
        return modelMapper.map(clientEntity, ClientResponse.class);
    }

}
