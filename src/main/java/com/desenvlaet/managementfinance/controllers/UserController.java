package com.desenvlaet.managementfinance.controllers;

import com.desenvlaet.managementfinance.dto.UserDTO;
import com.desenvlaet.managementfinance.exception.ErrorAuthenticateException;
import com.desenvlaet.managementfinance.exception.RuleBusinessException;
import com.desenvlaet.managementfinance.model.User;
import com.desenvlaet.managementfinance.service.LaunchService;
import com.desenvlaet.managementfinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private LaunchService launchService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UserDTO userDTO) {

        User user = User.builder().
                name(userDTO.getName()).
                email(userDTO.getEmail()).
                password(userDTO.getPassword()).
                build();

        try {
            User userSave = service.saveUser(user);
            return new ResponseEntity<>(userSave, HttpStatus.CREATED);
        } catch (RuleBusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody UserDTO dto) {
        try {
            User userAuthenticate = service.authenticate(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(userAuthenticate);
        } catch (ErrorAuthenticateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}/saldo")
    public ResponseEntity getBalance(@PathVariable("id") Long id) {
        Optional<User> user =  service.obterPorId(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BigDecimal saldo = launchService.getBalanceUser(id);
        return ResponseEntity.ok(saldo);
    }
}
