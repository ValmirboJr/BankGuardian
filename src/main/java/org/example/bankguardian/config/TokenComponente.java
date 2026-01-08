package org.example.bankguardian.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.bankguardian.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
public class TokenComponente {

    @Value("${bankguardian.security.secret}")
    private String secret;

    public String gerartoken(Account account){
        Algorithm algo = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(account.getEmail())
                .withClaim("accountId", account.getAccountid().toString())
                .withClaim("email", account.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API Bank Guardian")
                .sign(algo);
    }
    public Optional<JWTUserData> verificarToken(String token){
        try{
            Algorithm algo = Algorithm.HMAC256(secret);
            DecodedJWT jwt =JWT.require(algo)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData
                    .builder()
                    .id(UUID.fromString("accountId"))
                    .password(jwt.getClaim("password").asString())
                    .email(jwt.getSubject())
                    .build());
        }catch (JWTVerificationException ex){
            return Optional.empty();
        }
    }
}