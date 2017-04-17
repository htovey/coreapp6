/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package het.springapp.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author heather
 */
public class CsrfTokenGeneratorFilter extends OncePerRequestFilter{

    public CsrfTokenGeneratorFilter() { }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Create CSRF Token from request param
        CsrfToken token = (CsrfToken)request.getAttribute("_csrf");
        //Set up CSRF Response Headers for: HEADER, TOKEN & PARAM
        //response.setHeader("X-CSRF-HEADER", token.getHeaderName());
        //response.setHeader("X-CSRF-PARAM", token.getParameterName());
       // response.setHeader("X-CSRF-TOKEN", token.getToken());
        
        Cookie cookie = new Cookie("X-CSRF-TOKEN", token.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);
        //Filter
        filterChain.doFilter(request, response);
    }    
}
