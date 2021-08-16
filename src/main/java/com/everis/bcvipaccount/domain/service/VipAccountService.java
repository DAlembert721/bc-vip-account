package com.everis.bcvipaccount.domain.service;

import com.everis.bcvipaccount.domain.models.VipAccount;
import reactor.core.publisher.Mono;

public interface VipAccountService extends BaseService<VipAccount>{
    Mono<VipAccount> findByAccountNumber(String accountNumber);
    Mono<VipAccount> disableAccount(String id);
}