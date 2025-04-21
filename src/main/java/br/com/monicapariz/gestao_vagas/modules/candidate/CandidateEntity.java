package br.com.monicapariz.gestao_vagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Daniel de Souza",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Nome do Candidato")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "Username should not contain spaces")
    @Schema(example = "daniel",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Username do Candidato")
    private String username;

    @Email(message = "Email should be valid")
    @Schema(example = "daniel@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "E-mail do Candidato")
    private String email;

    @Length(min = 6, max = 100)
    @Schema(
            example = "admin@1234",
            minLength = 6, maxLength = 100,
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Senha do Candidato"
    )
    private String password;
    @Schema(example = "Desenvolvedor Java",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Breve descrição do Candidato")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime creationDate;
}
