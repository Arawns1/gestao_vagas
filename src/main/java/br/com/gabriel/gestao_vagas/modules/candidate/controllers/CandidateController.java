package br.com.gabriel.gestao_vagas.modules.candidate.controllers;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import br.com.gabriel.gestao_vagas.modules.candidate.dto.CandidateSaveDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.candidate.services.CandidateService;
import br.com.gabriel.gestao_vagas.modules.candidate.services.ListAllJobsByFilterService;
import br.com.gabriel.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserFoundException;
import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name="Candidato", description = "Informações do candidato")
public class CandidateController {

@Autowired
private CandidateService candidateService;

@Autowired
private ProfileCandidateService profileCandidateService;

@Autowired
private ListAllJobsByFilterService listAllJobsByFilterService;
    @PostMapping
    @Transactional
    @Operation(summary = "Cadastro de Candidato",
            description = "Essa função é responsável por cadastrar um novo candidato"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = Candidate.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid CandidateSaveDTO candidate,UriComponentsBuilder uriBuilder){
        try{
            Candidate response = candidateService.save(candidate);

            var uri = uriBuilder.path("/candidate/{id}")
                                .buildAndExpand(response.getId())
                                .toUri();

            return ResponseEntity.created(uri).body(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('candidate')")
    @Operation(summary = "Perfil do candidato",
               description = "Essa função é responsável por buscar as informações do perfil do usuário"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                          schema = @Schema(implementation = ProfileCandidateResponseDTO.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "User not found")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> findUser(HttpServletRequest request){
        var idCandidate = request.getAttribute("candidate_id");
        try{
            ProfileCandidateResponseDTO result = profileCandidateService.findProfile(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(result);
        }
        catch(Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('candidate')")
    @Operation(summary = "Listagem de vagas disponíveis para o candidato",
            description = "Essa função é responsável por listar todas as vagas disponíveis com base no filtro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                       array = @ArraySchema(schema = @Schema(implementation = Jobs.class))
                    )
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> findJobByFilter(@RequestParam String filter) {
        try {
            var result = listAllJobsByFilterService.search(filter);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
