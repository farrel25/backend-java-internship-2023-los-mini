package com.los.service;

import com.los.dto.request.AuthenticationRequest;
import com.los.dto.response.AuthenticationResponse;
import com.los.entity.MasterUser;
import com.los.entity.Token;
import com.los.repository.MasterUserRepository;
import com.los.repository.TokenRepository;
import com.los.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    // REPOSITORY
    private final MasterUserRepository masterUserRepository;
    private final TokenRepository tokenRepository;

    // SERVICE
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        // validate request
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        MasterUser masterUser = masterUserRepository
                .findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // generate access token
        Collection<? extends GrantedAuthority> masterUserAuthorities = masterUser.getAuthorities();
        String accessToken;

        if (masterUserAuthorities.isEmpty()) {
            accessToken = jwtService.generateAccessToken(masterUser);

        } else {
            Set<String> authorities = masterUserAuthorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());

            Map<String, Object> claim = new HashMap<>();
            claim.put("roles", authorities);

            accessToken = jwtService.generateAccessToken(claim, masterUser);
        }

        // Revoke all the user's old access token and save the new access token
        this.revokeOldTokenAndSaveNewToken(masterUser, accessToken);

        return new AuthenticationResponse(accessToken);
    }

    private void revokeOldTokenAndSaveNewToken(MasterUser masterUser, String accessToken) {
        // Revoke all the user's access token
        List<Token> validTokens = tokenRepository.findAllValidTokenByUserId(masterUser.getId());
        validTokens.forEach(token -> token.setIsRevoked(true));
        tokenRepository.saveAll(validTokens);

        // save the access token
        Token token = new Token();
        token.setToken(accessToken);
        token.setIsRevoked(false);
        token.setMasterUser(masterUser);

        tokenRepository.save(token);
    }
}
