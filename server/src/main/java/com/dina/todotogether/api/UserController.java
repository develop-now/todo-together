package com.dina.todotogether.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dina.todotogether.data.dto.MemberInfoSignUpRequest;
import com.dina.todotogether.data.dto.MemberSignUpRequest;
import com.dina.todotogether.data.dto.ResisterValidationRequest;
import com.dina.todotogether.data.entity.AllUser;
import com.dina.todotogether.data.entity.Role;
import com.dina.todotogether.service.UserService;
import com.dina.todotogether.util.CookieUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    private final CookieUtil cookieUtil = new CookieUtil();

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid MemberSignUpRequest member, @Valid MemberInfoSignUpRequest memberInfo) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
        return ResponseEntity.created(uri).body(userService.register(member, memberInfo));
    }

    @PostMapping("/register/overlapping-check")
    public ResponseEntity<Map<String, String>> overlappingCheck(@RequestBody ResisterValidationRequest memberInfo) {

        Boolean result = userService.overlappingCheck(memberInfo);
        Map<String, String> data = new HashMap<>();
        if (result == false) {
            data.put("overlapping-status", "사용불가");
        } else {
            data.put("overlapping-status", "사용가능");
        }

        return (result == false) ? ResponseEntity.status(CONFLICT).body(data) : ResponseEntity.status(OK).body(data);
    }

    @GetMapping("/token/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email = decodedJWT.getSubject();
                log.info("email{}", email);
                AllUser member = userService.getUser(email);
                String access_token = JWT.create()
                        .withSubject(member.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", member.getRoles().stream().map(Role::getRName).collect(Collectors.toList()))
                        .sign(algorithm);

                Cookie access_cookie = cookieUtil.updateJWTCookie(request, "Access-token", access_token);
                response.addCookie(access_cookie);


                Map<String, String> result = new HashMap<>();
                result.put("result", "success");
                result.put("url", request.getServletPath());
                return ResponseEntity.status(OK).body(result);

            } catch (Exception exception) {
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                ResponseEntity.status(FORBIDDEN).body(error);
            }
        } else {
            throw new RuntimeException("refresh token 사용불가.");
        }
        return null;
    }
}
