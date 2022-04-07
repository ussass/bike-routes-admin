package ru.trofimov.bikeroutesadmin.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.bikeroutesadmin.model.Admin;
import ru.trofimov.bikeroutesadmin.model.User;
import ru.trofimov.bikeroutesadmin.service.ConnectionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminRestController {

    private final ConnectionService connectionService;

    public AdminRestController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Admin admin) {
        HttpHeaders httpHeaders = new HttpHeaders();
        connectionService.authorization(admin);
        String location = request.getRequestURL().toString();
        httpHeaders.add("Location", location.substring(0, location.length() - 6));
        return new ResponseEntity<>("Authorization successfully", httpHeaders, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(connectionService.getUserList(), HttpStatus.OK);
    }
}
