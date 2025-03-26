package com.example.bimix.configuration;


import com.example.bimix.model.Utilisateur;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTManager {
    private final Key key;
    private final int jwt_expiration_days;

    @Autowired
    public JWTManager(Dotenv dotenv) {
        this.key = new SecretKeySpec(Base64.getDecoder().decode(dotenv.get("JWT_SECRET")),
                SignatureAlgorithm.HS256.getJcaName());
        this.jwt_expiration_days = Integer.parseInt(dotenv.get("JWT_EXPIRATION_DAYS"));
    }

    public String generateToken(Utilisateur utilisateur) {
        Date currentDate = new Date();
        return Jwts.builder()
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + dayToMs(this.jwt_expiration_days)))
                .claim("id", String.valueOf(utilisateur.getIdUtilisateur()))
                .claim("role", String.valueOf(utilisateur.getIdRole()))
                .claim("nom", String.valueOf(utilisateur.getIdPersonnel().getNom()))
                .claim("prenom", String.valueOf(utilisateur.getIdPersonnel().getPrenom()))
                .claim("matricule", String.valueOf(utilisateur.getIdPersonnel().getMatricule()))
                .signWith(key)
                .compact();
    }

    public String getClaim(String token, String claim_name) {
        Claims claims = getClaims(token);
        return claims.get(claim_name, String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void validateToken(String token) throws AuthenticationCredentialsNotFoundException {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            if (expirationDate != null && expirationDate.before(new Date())) {
                throw new AuthenticationCredentialsNotFoundException("Token expired");
            }
            // test token base (valide)
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Invalid token", ex.fillInStackTrace());
        }
    }



    private long dayToMs(int days) {
        return days * 24 * 60 * 60 * 1000L;
    }
}
