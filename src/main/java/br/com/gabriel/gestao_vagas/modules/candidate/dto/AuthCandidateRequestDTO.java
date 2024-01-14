package br.com.gabriel.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthCandidateRequestDTO(
        @Schema(example = "user", requiredMode = Schema.RequiredMode.REQUIRED)
        String username,
        @Schema(example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
        String password) {
}
