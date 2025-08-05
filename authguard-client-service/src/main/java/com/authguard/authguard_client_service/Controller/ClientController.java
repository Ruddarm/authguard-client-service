package com.authguard.authguard_client_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authguard.authguard_client_service.DTO.ClientLoginRequest;
import com.authguard.authguard_client_service.DTO.ClientLoginResponse;
import com.authguard.authguard_client_service.DTO.ClientRequest;
import com.authguard.authguard_client_service.Exception.ResourceException;
import com.authguard.authguard_client_service.Service.AuthService;
import com.authguard.authguard_client_service.Service.ClientService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final AuthService authService;

    @GetMapping
    public String getName() {
        return "name is fuck man";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> clientSignUp(@Valid @RequestBody ClientRequest clientRequest) throws ResourceException {
        return ResponseEntity.ok(clientService.saveClient(clientRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<ClientLoginResponse> cliengLoging(@Valid @RequestBody ClientLoginRequest loginRequest,
            HttpServletRequest request, HttpServletResponse response) {
        String[] data = authService.validateClient(loginRequest);
        String cookie = String.format(
                "client_refresh_token=%s; Path=/; HttpOnly; SameSite=Lax; Max-Age=%d",
                data[1], 7 * 24 * 60 * 60);
        response.setHeader("Set-Cookie", cookie);
        return ResponseEntity
                .ok(ClientLoginResponse.builder().accessToken(data[0]).userId(data[2]).email(data[3]).build());
    }
    

}
