package com.authguard.authguard_client_service.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.authguard.authguard_client_service.DTO.ClientLoginRequest;
import com.authguard.authguard_client_service.Model.Domain.Client;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;

    public String[] validateClient(ClientLoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        Client client = (Client) authenticate.getPrincipal();
        String accesstoken = jwtService.createAccessToken(client);
        String refreshtoken = jwtService.createRefreshToken(client);
        return new String[] { accesstoken, refreshtoken, client.getUserId(), client.getUsername() };
    }
}
