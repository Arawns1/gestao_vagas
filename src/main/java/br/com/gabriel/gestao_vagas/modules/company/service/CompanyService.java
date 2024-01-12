package br.com.gabriel.gestao_vagas.modules.company.service;

import br.com.gabriel.gestao_vagas.modules.company.domain.Company;
import br.com.gabriel.gestao_vagas.modules.company.repository.CompanyRepository;
import br.com.gabriel.gestao_vagas.modules.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Company save(Company company){
        companyRepository
                .findByUsernameOrCnpjOrEmail(company.getUsername(),company.getCnpj(), company.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        return companyRepository.save(company);
    }
}
