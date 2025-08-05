package com.authguard.authguard_client_service.Service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authguard.authguard_client_service.DTO.ClientRequest;
import com.authguard.authguard_client_service.DTO.ClientResponse;
import com.authguard.authguard_client_service.Exception.ResourceException;
import com.authguard.authguard_client_service.Model.Domain.Client;
import com.authguard.authguard_client_service.Model.Entity.ClientEntity;
import com.authguard.authguard_client_service.Repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public ClientResponse saveClient(ClientRequest clientRequest) throws ResourceException {
        ClientEntity clientEntity = modelMapper.map(clientRequest, ClientEntity.class);
        if (clientRepository.existsByEmail(clientRequest.getEmail())) {
            throw new ResourceException("Email is already Exist");
        }
        clientEntity.setHashPassword(passwordEncoder.encode(clientRequest.getPassword()));
        clientRepository.save(clientEntity);
        return modelMapper.map(clientEntity, ClientResponse.class);
    }

    public Optional<ClientEntity> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    @Cacheable("logedInUser")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientEntity clientEntity = findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found : " + username));
        return Client.builder().username(username).userId(clientEntity.getUserId().toString())
                .password(clientEntity.getHashPassword()).build();
    }

}
