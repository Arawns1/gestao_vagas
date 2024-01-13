package br.com.gabriel.gestao_vagas.modules.candidate.dto;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;

import java.util.UUID;

public record ProfileCandidateResponseDTO(UUID id,
                                          String name,
                                          String description,
                                          String username,
                                          String email) {
    public ProfileCandidateResponseDTO(Candidate candidate){
        this(candidate.getId(), candidate.getName(), candidate.getDescription(), candidate.getUsername(), candidate.getEmail());
    }
}
