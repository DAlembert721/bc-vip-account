package com.everis.bcvipaccount.service;

import com.everis.bcvipaccount.domain.models.VipAccount;
import com.everis.bcvipaccount.domain.repository.VipAccountRepository;
import com.everis.bcvipaccount.domain.service.VipAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VipAccountServiceImpl implements VipAccountService {

    @Autowired
    private VipAccountRepository vipAccountRepository;

    @Override
    public Flux<VipAccount> findAll() {
        return vipAccountRepository.findAll();
    }

    @Override
    public Mono<VipAccount> findById(String id) {
        return vipAccountRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(throwable -> Mono.error(new Exception("Vip account not founded")));
    }

    @Override
    public Mono<VipAccount> createVipAccount(String documentNumber) {
        return null;
    }

    @Override
    public Mono<VipAccount> update(String id, VipAccount request) {
        return null;
    }

    @Override
    public Mono<VipAccount> findByAccountNumber(String accountNumber) {
        return vipAccountRepository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(throwable -> Mono.error(new Exception("Vip account not founded")));
    }

    @Override
    public Mono<VipAccount> disableAccount(String accountNumber) {
        return null;
    }
}
