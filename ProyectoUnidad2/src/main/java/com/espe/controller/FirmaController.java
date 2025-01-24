package com.espe.controller;

import com.espe.bean.CertificadoBean;
import com.espe.nucleo.CertificadoLee;
import com.espe.nucleo.FirmaPdf;
import com.espe.util.Constante;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
public class FirmaController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/gestion")
    public String gestion() {
        return "gestion";
    }
    
    @GetMapping("/firma")
    public String mostrarFormulario() {
        return "firma";
    }
    
    @PostMapping("/firma/firmar")
    public ResponseEntity<?> firmarPdf(
            @RequestParam("archivo") MultipartFile archivo,
            HttpSession session) {
        try {
            if (archivo.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Por favor seleccione un archivo PDF");
            }

            // Obtener el ID del usuario de la sesión
            Long usuarioId = (Long) session.getAttribute("usuarioId");
            
            // Crear directorio base si no existe
            Path directorioBase = Paths.get(Constante.DIRECTORIO_ARCHIVOS_FIRMADOS);
            if (!Files.exists(directorioBase)) {
                Files.createDirectories(directorioBase);
            }
            
            // Crear directorio específico del usuario
            Path directorioUsuario = directorioBase.resolve(usuarioId.toString());
            if (!Files.exists(directorioUsuario)) {
                Files.createDirectories(directorioUsuario);
            }

            // Generar nombre único para el archivo
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String nombreUnico = UUID.randomUUID().toString().substring(0, 8);
            String nombreOriginal = archivo.getOriginalFilename();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
            String nombreFirmado = timestamp + "_" + nombreUnico + "_firmado" + extension;
            
            // Obtener el certificado y firmar
            CertificadoBean certificado = CertificadoLee.getCertificadoDeArchivo(
                    Constante.CERTIFICADO, 
                    Constante.CLAVE
            );
            
            byte[] documentoOriginal = archivo.getBytes();
            byte[] documentoFirmado = FirmaPdf.firmaPdfBasico(documentoOriginal, certificado);
            
            if (documentoFirmado == null) {
                return ResponseEntity.badRequest()
                        .body("Error al firmar el documento");
            }
            
            // Guardar el archivo firmado
            Path rutaCompleta = directorioUsuario.resolve(nombreFirmado);
            Files.write(rutaCompleta, documentoFirmado);
            
            // Devolver el archivo firmado para descarga
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                           "attachment; filename=\"" + nombreFirmado + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(documentoFirmado);
                    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Error al firmar el PDF: " + e.getMessage());
        }
    }
} 