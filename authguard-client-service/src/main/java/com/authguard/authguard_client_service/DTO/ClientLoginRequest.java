package com.authguard.authguard_client_service.DTO;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientLoginRequest {

    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NonNull
    private String password;
}
