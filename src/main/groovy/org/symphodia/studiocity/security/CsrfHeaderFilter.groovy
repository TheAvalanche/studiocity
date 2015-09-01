package org.symphodia.studiocity.security

import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.WebUtils

import javax.servlet.FilterChain
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CsrfHeaderFilter extends OncePerRequestFilter {

    void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        CsrfToken csrf = request.getAttribute(CsrfToken.class.name);
        if (csrf != null) {
            Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
            String token = csrf.getToken();
            if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie("XSRF-TOKEN", token);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        filterChain.doFilter(request, response);
    }
}
