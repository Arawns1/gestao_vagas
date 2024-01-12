package br.com.gabriel.gestao_vagas.modules.candidate.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_candidate")
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String username;
    private String email;
    private String password;
    private String description;
    private String Curriculum;
}
