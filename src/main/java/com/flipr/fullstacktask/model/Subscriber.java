package com.flipr.fullstacktask.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subscribers",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @CreationTimestamp          // auto‑set when row is inserted
    private LocalDateTime subscribedAt;
}
