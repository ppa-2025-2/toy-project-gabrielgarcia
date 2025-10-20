package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.service.IslandApplicationService;
import com.example.demo.controller.dto.NewUserDTO;
import com.example.demo.domain.service.UserService;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private IslandApplicationService islandApplicationService;
    private UserService userBusiness;
    private UserRepository userRepository;

    public UserController(
            IslandApplicationService islandApplicationService,
            UserService userBusiness,
            UserRepository userRepository) {
        this.islandApplicationService = islandApplicationService;
        this.userBusiness = userBusiness;
        this.userRepository = userRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void newUser(@RequestBody NewUserDTO newUser) {
        // O Controller pode ter lógica?
        // Pode, lógica de controle (roteamento).
        // O Controller delega para o Domain Business
        userBusiness.cadastrarUsuario(newUser);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/{userId}/alocar-workstation")
    public ResponseEntity<Void> alocarWorkstation(@PathVariable Integer userId) {
        islandApplicationService.alocarWorkstationDisponivel(userId);
        return ResponseEntity.ok().build();
    }

    /*
     * 
     * curl -X POST http://localhost:8080/api/v1/users/1/alocar-workstation \
     * -H "Content-Type: application/json" \
     * -w "\nHTTP Status: %{http_code}\n"
     * 
     * deixei o curl pq o httpRequests ta meio bugado local aqui
     *
     * essa req curl retorna status 200 e da pra ver no próprio db.sqlite na tabela
     * workstation que uma workstation foi alocada para o user do id 1
     * 
     */
}
