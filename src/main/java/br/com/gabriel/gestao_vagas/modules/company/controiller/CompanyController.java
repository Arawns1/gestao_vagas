package br.com.gabriel.gestao_vagas.modules.company.controiller;

import br.com.gabriel.gestao_vagas.modules.candidate.domain.Candidate;
import br.com.gabriel.gestao_vagas.modules.company.domain.Company;
import br.com.gabriel.gestao_vagas.modules.company.service.CompanyService;
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
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @PostMapping
    @Transactional
    public ResponseEntity<Object> create(@RequestBody @Valid Company company, UriComponentsBuilder uriBuilder){
        try{
            Company response = companyService.save(company);

            var uri = uriBuilder.path("/company/{id}")
                                .buildAndExpand(response.getId())
                                .toUri();

            return ResponseEntity.created(uri).body(response);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
