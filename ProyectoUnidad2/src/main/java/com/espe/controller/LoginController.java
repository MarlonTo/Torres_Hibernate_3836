package com.espe.controller;

import com.espe.model.Usuario;
import com.espe.service.UsuarioService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin(HttpServletResponse response) {
        // Prevenir caché en la página de login
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                       @RequestParam String password,
                       HttpSession session,
                       HttpServletResponse response,
                       Model model) {
        Usuario usuario = usuarioService.obtenerPorUsername(username)
                .orElse(null);

        if (usuario != null && usuario.getPassword().equals(password)) {
            session.setAttribute("usuarioId", usuario.getId());
            session.setAttribute("username", usuario.getUsername());
            session.setAttribute("nombreCompleto", 
                usuario.getPrimerNombre() + " " + usuario.getPrimerApellido());
            
            // Prevenir caché después del login
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            
            return "redirect:/";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        
        // Prevenir caché después del logout
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        
        return "redirect:/login";
    }
} 