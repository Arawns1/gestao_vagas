package br.com.gabriel.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CandidateSaveDTO(
                                @Schema(example = "User Full Name",
                                        requiredMode = Schema.RequiredMode.REQUIRED)
                                String name,
                                @Schema(example = "user",
                                        requiredMode = Schema.RequiredMode.REQUIRED)
                               String username,
                                @Schema(example = "user@email.com",
                                        requiredMode = Schema.RequiredMode.REQUIRED)
                               String email,
                                @Schema(example = "12345678",
                                        requiredMode = Schema.RequiredMode.REQUIRED,
                                        minLength = 8,
                                        maxLength = 100)
                               String password,
                                @Schema(example = "Muito Empenhado")
                               String description,
                                @Schema(example = "Big Curriculum")
                               String curriculum) {
}
