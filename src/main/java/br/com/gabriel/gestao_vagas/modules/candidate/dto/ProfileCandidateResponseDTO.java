package br.com.gabriel.gestao_vagas.modules.candidate.dto;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record ProfileCandidateResponseDTO(UUID id,
                                          @Schema(example = "gabriel")
                                          String name,
                                          @Schema(example = "Desenvolvedor Java")
                                          String description,
                                          @Schema(example = "arawns")
                                          String username,
                                          @Schema(example = "gabriel@email.com")
                                          String email) {
    public ProfileCandidateResponseDTO(Candidate candidate){
        this(candidate.getId(), candidate.getName(), candidate.getDescription(), candidate.getUsername(), candidate.getEmail());
    }
}
