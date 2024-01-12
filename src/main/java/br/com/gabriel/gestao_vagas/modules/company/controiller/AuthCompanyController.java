package br.com.gabriel.gestao_vagas.modules.company.controiller;

import br.com.gabriel.gestao_vagas.modules.company.dto.AuthDTO;
import br.com.gabriel.gestao_vagas.modules.company.service.CompanyAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private CompanyAuth companyAuth;

    @PostMapping("/company")
    public String create(@RequestBody AuthDTO authCompanyDTO){
        return companyAuth.auth(authCompanyDTO);
    }
}
