package com.espe.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler) throws Exception {
        
        String path = request.getRequestURI();
        
        // Prevenir caché en todas las páginas
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
        if (path.startsWith("/login") || path.contains("css") || path.contains("js")) {
            return true;
        }
        
        HttpSession session = request.getSession();
        if (session.getAttribute("usuarioId") == null) {
            response.sendRedirect("/login");
            return false;
        }
        
        return true;
    }
} 