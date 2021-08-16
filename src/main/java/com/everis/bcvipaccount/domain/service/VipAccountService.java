package com.everis.bcvipaccount.domain.service;

import com.everis.bcvipaccount.domain.models.VipAccount;
import reactor.core.publisher.Mono;

public interface VipAccountService extends BaseService<VipAccount>{
    Mono<VipAccount> findByAccountNumber(String accountNumber);
    Mono<VipAccount> createVipAccount(String documentNumber);
    Mono<VipAccount> update(String accountNumber, VipAccount request);
    Mono<VipAccount> disableAccount(String accountNumber);
}
