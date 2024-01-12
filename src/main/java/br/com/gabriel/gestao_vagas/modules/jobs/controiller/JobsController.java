package br.com.gabriel.gestao_vagas.modules.jobs.controiller;

import br.com.gabriel.gestao_vagas.modules.jobs.domain.Jobs;
import br.com.gabriel.gestao_vagas.modules.jobs.service.JobsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    private JobsService jobsService;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@RequestBody @Valid Jobs jobs, UriComponentsBuilder uriBuilder){
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
