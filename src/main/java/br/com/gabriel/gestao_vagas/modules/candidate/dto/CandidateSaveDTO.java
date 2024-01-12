package br.com.gabriel.gestao_vagas.modules.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CandidateSaveDTO(String name,
                               String username,
                               String email,
                               String password,
                               String description,
                               String curriculum) {
}
