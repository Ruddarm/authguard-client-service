package com.authguard.authguard_client_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.authguard.authguard_client_service.DTO.ClientRequest;
import com.authguard.authguard_client_service.Service.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String getName(){
        return "name is fuck man";
    }
    @PostMapping("/signup")
    public ResponseEntity<?> clientSignUp(@Valid @RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.saveClient(clientRequest));
    }

}
