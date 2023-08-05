package com.desenvlaet.managementfinance.service.impl;

import com.desenvlaet.managementfinance.enums.StatusLaunch;
import com.desenvlaet.managementfinance.enums.TypeLaunch;
import com.desenvlaet.managementfinance.exception.RuleBusinessException;
import com.desenvlaet.managementfinance.model.Launch;
import com.desenvlaet.managementfinance.repository.LaunchRepository;
import com.desenvlaet.managementfinance.service.LaunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LaunchServiceImpl implements LaunchService {

    @Autowired
    private LaunchRepository repository;

    @Override
    @Transactional
    public Launch save(Launch launch) {
        validate(launch);
        launch.setStatusLaunch(StatusLaunch.PENDENTE);
        return repository.save(launch);
    }

    @Override
    @Transactional
    public Launch update(Launch launch) {
        Objects.requireNonNull(launch.getId());
        validate(launch);
        return repository.save(launch);
    }

    @Override
    @Transactional
    public void delete(Launch launch) {
        Objects.requireNonNull(launch.getId());
        repository.delete(launch);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Launch> search(Launch launchFilter) {
        Example<Launch> example = Example.of(launchFilter, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return repository.findAll(example);
    }

    @Override
    @Transactional
    public void updateStatus(Launch launch, StatusLaunch status) {
        launch.setStatusLaunch(status);
        update(launch);
    }

    @Override
    public void validate(Launch launch) {
        if (launch.getDescription() == null || launch.getDescription().trim().equals("")) {
            throw new RuleBusinessException("Informe uma Descrição válida.");
        }

        if (launch.getMonth() == null || launch.getMonth() < 1 || launch.getMonth() > 12) {
            throw new RuleBusinessException("Informe um Mês válido.");
        }

        if (launch.getYear() == null || launch.getYear().toString().length() != 4) {
            throw new RuleBusinessException("Informe um Ano válido.");
        }

        if (launch.getUser() == null || launch.getUser().getId() == null) {
            throw new RuleBusinessException("Informe um Usuário válido.");
        }

        if (launch.getValue() == null || launch.getValue().compareTo(BigDecimal.ZERO) < 1) {
            throw new RuleBusinessException("Informe um Valor válido.");
        }

        if (launch.getTypeLaunch() == null) {
            throw new RuleBusinessException("Informe um Tipo de Lançamento válido.");
        }
    }

    @Override
    public Optional<Launch> obterPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalanceUser(Long id) {
        BigDecimal receitas = repository.getBalanceTypeLaunchAndUser(id, TypeLaunch.RECEITA.name());
        BigDecimal despesas = repository.getBalanceTypeLaunchAndUser(id, TypeLaunch.DESPESA.name());

        if (receitas == null) {
            receitas = BigDecimal.ZERO;
        }

        if (despesas == null) {
            despesas = BigDecimal.ZERO;
        }

        return receitas.subtract(despesas);
    }
}
