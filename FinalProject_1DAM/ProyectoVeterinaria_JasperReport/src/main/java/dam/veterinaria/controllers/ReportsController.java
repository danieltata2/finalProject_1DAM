package dam.veterinaria.controllers;
import dam.veterinaria.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class ReportsController {

    @Autowired
    private ReportService reportsService;

    // Informe de animales
    @GetMapping("/obtenerAnimal")
    public ResponseEntity<byte[]> obtenerAnimal() {
        try {
            byte[] report = reportsService.generarReport("informeAnimal", new HashMap<>());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=InformeAnimal.pdf");
            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    // Informe de clientes
    @GetMapping("/obtenerCliente")
    public ResponseEntity<byte[]> obtenerCliente() {
        try {
            byte[] report = reportsService.generarReport("informeCliente", new HashMap<>());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=InformeCliente.pdf");
            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    // Informe de veterinarios
    @GetMapping("/obtenerVeterinario")
    public ResponseEntity<byte[]> obtenerVeterinario() {
        try {
            byte[] report = reportsService.generarReport("informeVeterinarios", new HashMap<>());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=InformeVeterinario.pdf");
            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping("/obtenerVeterinarioNombres/{nombre}")
    public ResponseEntity<byte[]> obtenerVeterinarioNombre(@PathVariable String nombre) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("nombreVeterinario", nombre);

            byte[] report = reportsService.generarReport("informeVeterinario", params);

            if (report == null || report.length == 0) {
                return ResponseEntity.noContent().build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "inline; filename=InformeVeterinarioNombre.pdf");

            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
