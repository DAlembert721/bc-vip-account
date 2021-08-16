package com.everis.bcvipaccount.service;

import com.everis.bcvipaccount.config.PersonalWebClientConf;
import com.everis.bcvipaccount.dto.AccountClientRequestDto;
import com.everis.bcvipaccount.dto.AccountClientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PersonalWebClientService {

    private PersonalWebClientConf personalWebClientConf;

    private final WebClient webClient;

    private final ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    @Autowired
    public PersonalWebClientService(WebClient.Builder builder, PersonalWebClientConf conf,
                                    ReactiveCircuitBreakerFactory circuitBreakerFactory) {
        this.personalWebClientConf = conf;
        this.webClient = builder.baseUrl(personalWebClientConf.getPersonalClientBaseUri()).build();
        this.reactiveCircuitBreakerFactory = circuitBreakerFactory;
    }

    public Mono<AccountClientResponseDto> createAccountClient(AccountClientRequestDto accountClientRequestDto,
                                                              String documentNumber) {
        return this.webClient.post()
                .uri(personalWebClientConf.getEndPoint().concat(documentNumber))
                .body(Mono.just(accountClientRequestDto), AccountClientRequestDto.class)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        clientResponse -> Mono.empty())
                .bodyToMono(AccountClientResponseDto.class)
                .transform(accountClientResponseDtoMono -> {
                    ReactiveCircuitBreaker reactiveCircuitBreaker =
                            reactiveCircuitBreakerFactory.create("personals-customers/accounts/");
                    return reactiveCircuitBreaker
                            .run(accountClientResponseDtoMono,
                                    throwable ->
                                            Mono.error(
                                                    new Exception("Error while connect with personal client Service")));
                });
    }

}
