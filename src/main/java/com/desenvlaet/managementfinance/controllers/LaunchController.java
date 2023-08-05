package com.desenvlaet.managementfinance.controllers;

import com.desenvlaet.managementfinance.dto.LaunchDTO;
import com.desenvlaet.managementfinance.dto.UpdateStatusDTO;
import com.desenvlaet.managementfinance.enums.StatusLaunch;
import com.desenvlaet.managementfinance.enums.TypeLaunch;
import com.desenvlaet.managementfinance.exception.RuleBusinessException;
import com.desenvlaet.managementfinance.model.Launch;
import com.desenvlaet.managementfinance.model.User;
import com.desenvlaet.managementfinance.service.LaunchService;
import com.desenvlaet.managementfinance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/launchs")
public class LaunchController {

    @Autowired
    private LaunchService service;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity search(@RequestParam(value = "descricao", required = false) String descricao,
                                 @RequestParam(value = "mes",required = false) Integer mes,
                                 @RequestParam(value = "ano", required = false) Integer ano,
                                 @RequestParam("usuario") Long idUsuario) {

        Launch launchFilter = new Launch();
        launchFilter.setDescription(descricao);
        launchFilter.setMonth(mes);
        launchFilter.setYear(ano);

        Optional<User> user = userService.obterPorId(idUsuario);
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body("Não foi possivel realizar a consulta. Usuário não encontrado");
        } else {
            launchFilter.setUser(user.get());
        }

        List<Launch> launches = service.search(launchFilter);
        return  ResponseEntity.ok(launches);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody LaunchDTO launchDTO) {
        try {
            Launch launch = convert(launchDTO);
            launch = service.save(launch);
            return new ResponseEntity(launch, HttpStatus.CREATED);
        } catch (RuleBusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody LaunchDTO launchDTO) {
        return service.obterPorId(id).map(entity -> {
            try {
                Launch launch = convert(launchDTO);
                launch.setId(entity.getId());
                service.update(launch);
                return ResponseEntity.ok(launch);
            } catch (RuleBusinessException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }).orElseGet(() -> new ResponseEntity("Lançamento não encontrado na base de dados", HttpStatus.BAD_REQUEST));
    }

    @PutMapping("{id}/update-status")
    public ResponseEntity updateStatus(@PathVariable("id") Long id, @RequestBody UpdateStatusDTO statusDTO) {
        String a = "0";
        return service.obterPorId(id).map(entity -> {
            StatusLaunch statusSelection = StatusLaunch.valueOf(statusDTO.getStatus());
            if (statusSelection == null) {
                return ResponseEntity.badRequest().body("Não foi possível atualizar o status do lançamento, envie um status válido");
            }
            try {
                entity.setStatusLaunch(statusSelection);
                service.update(entity);
                return ResponseEntity.ok(entity);
            } catch (RuleBusinessException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity<>("Lançamento não encontrado na base de dados", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return service.obterPorId(id).map(entity -> {
            service.delete(entity);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>("Lançamento não encontrado na base de dados", HttpStatus.BAD_REQUEST));
    }

    private Launch convert(LaunchDTO dto) {
        Launch launch = new Launch();

        launch.setId(dto.getId());
        launch.setDescription(dto.getDescription());
        launch.setYear(dto.getYear());
        launch.setMonth(dto.getMonth());
        launch.setValue(dto.getValue());

        User user =
        userService.obterPorId(dto.getUser()).orElseThrow(() -> new RollbackException("Usuário não encontrado para o Id não informado."));

        launch.setUser(user);

        if (dto.getTypeLaunch() != null) {
            launch.setTypeLaunch(TypeLaunch.valueOf(dto.getTypeLaunch()));
        }

        if (dto.getStatusLaunch() != null) {
            launch.setStatusLaunch(StatusLaunch.valueOf(dto.getStatusLaunch()));
        }

        return launch;
    }
}
