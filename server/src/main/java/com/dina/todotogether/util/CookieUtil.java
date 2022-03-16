package com.dina.todotogether.util;

import com.auth0.jwt.JWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class CookieUtil {

    private final int TOKEN_VALIDITY_SECONDS = 5 * 60;

    public Cookie createJWTCookie(String cookieName, String value) {
        Cookie token = new Cookie(cookieName, value);
        token.setHttpOnly(true);
        token.setMaxAge(TOKEN_VALIDITY_SECONDS);
        token.setPath("/");
        return token;
    }

    public Cookie getJWTCookie(HttpServletRequest request, String cookieName) {
        final Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }

    public Cookie updateJWTCookie(HttpServletRequest request, String cookieName, String value) {
        final Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)) {
                cookie.setValue(value);
                return cookie;
            }
        }
        return null;
    }
}
