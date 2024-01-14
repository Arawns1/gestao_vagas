package br.com.gabriel.gestao_vagas.modules.jobs.dto;

import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record CreateJobDTO (
        @Schema(example = "Vaga para pessoa desenvolvedora junior", requiredMode = RequiredMode.REQUIRED)
        String description,
        @Schema(example = "Gympass, Plano de Saúde...", requiredMode = RequiredMode.REQUIRED)
        String benefits,
        @Schema(example = "Junior", requiredMode = RequiredMode.REQUIRED)
        String level) {
    public CreateJobDTO(Jobs jobs){
        this(jobs.getDescription(),jobs.getBenefits(), jobs.getLevel());
    }
}
