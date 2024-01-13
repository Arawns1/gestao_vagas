package br.com.gabriel.gestao_vagas.modules.company.service;

import br.com.gabriel.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.gabriel.gestao_vagas.modules.company.domain.Company;
import br.com.gabriel.gestao_vagas.modules.company.dto.AuthDTO;
import br.com.gabriel.gestao_vagas.modules.company.repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class CompanyAuth {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String auth(AuthDTO authCompanyDTO) throws AuthenticationException {
        Company companyFound = companyRepository.findByUsername(authCompanyDTO.username())
                         .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        boolean passwordMatches = passwordEncoder.matches(authCompanyDTO.password(),companyFound.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                          .withIssuer("javagas")
                          .withSubject(companyFound.getId().toString())
                          .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                          .sign(algorithm);
    }
}
