package com.costa.projetoBiblioteca.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final String SECRET = "sxyIpQg6ST4AGDsMWQdc4BAZTidXlztz1Qmy4Dq+2WY=";

    //gerando o token
    public String gerarToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), Jwts.SIG.HS256)
                .compact();
    }

    //extraindo info
    public String extrairUsername(String token){

        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }



    public boolean tokenValido(String token){
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                    .build()
                    .parseSignedClaims(token);
            return true;

        }catch (Exception e){
            return false;
        }
    }
}
