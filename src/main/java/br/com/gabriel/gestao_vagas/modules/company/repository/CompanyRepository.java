package br.com.gabriel.gestao_vagas.modules.company.repository;

import br.com.gabriel.gestao_vagas.modules.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByUsernameOrCnpjOrEmail(String username, String cnpj, String email);
    Optional<Company> findByUsername(String usename);
}
