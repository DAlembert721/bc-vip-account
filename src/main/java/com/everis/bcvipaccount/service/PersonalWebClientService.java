package com.everis.bcvipaccount.service;

import com.everis.bcvipaccount.dto.AccountClientRequestDto;
import com.everis.bcvipaccount.dto.AccountClientResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PersonalWebClientService {
    String personalClientBaseUri = "http://172.25.0.2:8080/";
    String endPoint = "personals-customers/accounts/";
    
    private final WebClient webClient;


    public PersonalWebClientService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(personalClientBaseUri).build();
    }
    
    public Mono<AccountClientResponseDto> createAccountClient(AccountClientRequestDto accountCL)
    
}
