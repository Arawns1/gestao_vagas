package br.com.gabriel.gestao_vagas.modules.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CandidateSaveDTO(String name,
                               @NotBlank
                               @Pattern(regexp = "\\S+",
                                       message = "O campo (username) não pode conter espaços") // Retira espaços
                               String username,
                               @Email(message = "O campo (email) deve conter um e-mail válido")
                               String email,
                               @Length(min = 8, max = 50, message = "A senha deve conter entre 8 e 50 caracteres")
                               String password,
                               String description,
                               String curriculum) {
}
