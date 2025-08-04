package com.authguard.authguard_client_service.Model;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "entity")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String hashPassword;
    // @Column(unique = true, nullable = false)
    // private String contactNumber;
    @Column
    @CreationTimestamp
    private LocalDate createdAt;
    @Column(unique = true)
    private String country;
    @Column
    private boolean emailVerified;

   
}
