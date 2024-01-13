package br.com.gabriel.gestao_vagas.modules.jobs.dto;

import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;

public record CreateJobDTO (String description, String benefits, String level) {
    public CreateJobDTO(Jobs jobs){
        this(jobs.getDescription(),jobs.getBenefits(), jobs.getLevel());
    }
}
