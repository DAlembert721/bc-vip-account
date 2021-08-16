package com.everis.bcvipaccount.domain.repository;

import com.everis.bcvipaccount.domain.models.VipAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface VipAccountRepository extends ReactiveMongoRepository<VipAccount, String> {
    Mono<VipAccount> findByAccountNumber(String accountNumber);
}
