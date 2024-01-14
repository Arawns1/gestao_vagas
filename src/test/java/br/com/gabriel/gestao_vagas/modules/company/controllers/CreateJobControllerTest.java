package br.com.gabriel.gestao_vagas.modules.company.controllers;

import br.com.gabriel.gestao_vagas.modules.company.domain.Company;
import br.com.gabriel.gestao_vagas.modules.company.repository.CompanyRepository;
import br.com.gabriel.gestao_vagas.modules.exceptions.CompanyNotFoundException;
import br.com.gabriel.gestao_vagas.modules.jobs.dto.CreateJobDTO;
import br.com.gabriel.gestao_vagas.modules.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CreateJobControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void ShouldBeAbleToCreateANewJob() throws Exception {
        var company = Company.builder()
                             .description("COMPANY_DESCRIPTION")
                             .email("EMAIL@COMPANY.COM.BR")
                             .password("1234567890")
                             .name("COMPANY_NAME")
                             .username("COMPANY_USERNAME")
                             .build();
        company = companyRepository.saveAndFlush(company);

        CreateJobDTO createJobDTO = new CreateJobDTO("DESCRIPTION_TEST", "BENEFITS_TEST", "LEVEL_TEST");

        mvc.perform(MockMvcRequestBuilders
                    .post("/company/jobs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", TestUtils.generateTestToken(company.getId(), "JAVAGAS_@123#"))
                    .content(TestUtils.objectToJSON(createJobDTO)))
           .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldNotBeAbleToCreateANewJobIfCompanyNotFound() throws Exception {
        var company = Company.builder()
                             .description("COMPANY_DESCRIPTION")
                             .email("EMAIL@COMPANY.COM.BR")
                             .password("1234567890")
                             .name("COMPANY_NAME")
                             .username("COMPANY_USERNAME")
                             .build();
        company = companyRepository.saveAndFlush(company);

        CreateJobDTO createJobDTO = new CreateJobDTO("DESCRIPTION_TEST", "BENEFITS_TEST", "LEVEL_TEST");

        try {
            mvc.perform(MockMvcRequestBuilders
                    .post("/company/jobs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", TestUtils.generateTestToken(UUID.randomUUID(), "JAVAGAS_@123#"))
                    .content(TestUtils.objectToJSON(createJobDTO)))
               .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }
        catch (Exception e){
            assertThat(e).isInstanceOf(CompanyNotFoundException.class);
        }
    }

}
