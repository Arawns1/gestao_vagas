package br.com.gabriel.gestao_vagas.modules.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class TestUtils {
    public static String generateTestToken(UUID idCompany, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                  .withIssuer("javagas")
                  .withSubject(idCompany.toString())
                  .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                  .sign(algorithm);
    }

    public static String objectToJSON(Object obj){
        try{
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}
