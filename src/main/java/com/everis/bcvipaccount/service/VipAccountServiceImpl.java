package com.everis.bcvipaccount.service;

import com.everis.bcvipaccount.domain.models.VipAccount;
import com.everis.bcvipaccount.domain.repository.VipAccountRepository;
import com.everis.bcvipaccount.domain.service.VipAccountService;
import com.everis.bcvipaccount.dto.AccountClientRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VipAccountServiceImpl implements VipAccountService {

    @Autowired
    private VipAccountRepository vipAccountRepository;

    @Autowired
    private PersonalWebClientService personalWebClientService;


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
        VipAccount vipAccount = VipAccount.generateNewVipAccount();
        AccountClientRequestDto accountClientRequestDto = AccountClientRequestDto.buildAccountClientRequest(vipAccount);
        return personalWebClientService.createAccountClient(accountClientRequestDto, documentNumber)
                .flatMap(responseAccountClient -> vipAccountRepository.insert(vipAccount))
                .switchIfEmpty(Mono.error(new Exception("Error on create account")))
                .onErrorResume(throwable -> Mono.error(new Exception("Error on create account")));
    }

    @Override
    public Mono<VipAccount> update(String accountNumber, VipAccount request) {
        return vipAccountRepository.findByAccountNumber(accountNumber)
                .flatMap(vipAccount -> {
                    vipAccount.setAccountBalance(request.getAccountBalance());
                    return vipAccountRepository.save(vipAccount);
                })
                .switchIfEmpty(Mono.error(new Exception("Error on update account")))
                .onErrorResume(throwable -> Mono.error(new Exception("Error on update account")));
    }

    @Override
    public Mono<VipAccount> findByAccountNumber(String accountNumber) {
        return vipAccountRepository.findByAccountNumber(accountNumber)
                .switchIfEmpty(Mono.empty())
                .onErrorResume(throwable -> Mono.error(new Exception("Vip account not founded")));
    }

    @Override
    public Mono<VipAccount> disableAccount(String accountNumber) {
        return vipAccountRepository.findByAccountNumber(accountNumber)
                .flatMap(vipAccount -> {
                    vipAccount.setIsActive(false);
                    return vipAccountRepository.save(vipAccount);
                })
                .switchIfEmpty(Mono.error(new Exception("Error on disable account")))
                .onErrorResume(throwable -> Mono.error(new Exception("Error on disable account")));
    }
}
