package br.com.gabriel.gestao_vagas.modules.company.controiller;

import br.com.gabriel.gestao_vagas.modules.company.dto.AuthDTO;
import br.com.gabriel.gestao_vagas.modules.company.service.CompanyAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    @Autowired
    private CompanyAuth companyAuth;

    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody AuthDTO authCompanyDTO) {
        try {
            var result = companyAuth.auth(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
