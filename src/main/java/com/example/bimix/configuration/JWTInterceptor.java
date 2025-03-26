package com.example.bimix.configuration;

import com.example.bimix.model.Utilisateur;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

public class JWTInterceptor extends OncePerRequestFilter {

    @Autowired
    private JWTManager jwt;
    @Autowired
    private com.example.bimix.service.LoginService LoginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJWTFromRequest(request);


        if (StringUtils.hasText(token)) {
            try {

                jwt.validateToken(token);

                String matricule = jwt.getClaim(token, "matricule");
                Utilisateur utilisateur = this.LoginService.login(matricule);

                CustomUserDetails userDetails = new CustomUserDetails(utilisateur);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Enregistrer l'authentification dans le SecurityContext de Spring
                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (AuthenticationCredentialsNotFoundException e) {
                // En cas d'erreur liée aux identifiants
                logger.error("AuthenticationCredentialsNotFoundException: " + e.getMessage());

                // Retourner une réponse avec un statut 401 Unauthorized
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(new ObjectMapper().writeValueAsString(
                        new HashMap<String, String>() {{ put("error", "Invalid token"); }}
                ));
                return;

            } catch (Exception e) {
                // Autre erreur (par exemple, problème avec la validation du token)
                logger.error("Token validation error: " + e.getMessage());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(new ObjectMapper().writeValueAsString(
                        new HashMap<String, String>() {{ put("error", "Invalid token"); }}
                ));
                return;
            }
        }

        // Continuer la chaîne de filtres si tout est valide
        filterChain.doFilter(request, response);
    }
    public static String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
