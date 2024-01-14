package br.com.gabriel.gestao_vagas.modules.jobs.controiller;

import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import br.com.gabriel.gestao_vagas.modules.jobs.dto.CreateJobDTO;
import br.com.gabriel.gestao_vagas.modules.jobs.service.JobsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/company")
public class JobsController {
    @Autowired
    private JobsService jobsService;
    @PostMapping("/jobs")
    @Transactional
    @Tag(name="Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vaga", description = "Essa função é responsável por cadastrar as vagas dentro da empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Jobs.class)))
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@RequestBody @Valid CreateJobDTO jobDTO, UriComponentsBuilder uriBuilder, HttpServletRequest request){
        var companyId = request.getAttribute("company_id");
        Jobs jobs = Jobs.builder()
                        .benefits(jobDTO.benefits())
                        .companyId(UUID.fromString(companyId.toString()))
                        .description(jobDTO.description())
                        .level(jobDTO.level())
                        .build();
        try{
            Jobs response = jobsService.save(jobs);

            var uri = uriBuilder.path("/jobs/{id}")
                                .buildAndExpand(response.getId())
                                .toUri();

            return ResponseEntity.created(uri).body(response);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
