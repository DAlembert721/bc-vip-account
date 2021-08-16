package com.everis.bcvipaccount.controller;

import com.everis.bcvipaccount.domain.models.VipAccount;
import com.everis.bcvipaccount.domain.service.VipAccountService;
import com.everis.bcvipaccount.dto.VipAccountRequestDto;
import com.everis.bcvipaccount.dto.VipAccountResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/vip-accounts")
@RestController
public class VipAccountsController implements BaseController<VipAccountRequestDto, VipAccountResponseDto>{

    @Autowired
    private VipAccountService vipAccountService;

    @GetMapping("")
    @Override
    public Flux<VipAccountResponseDto> getAll() {
        return vipAccountService.findAll()
                .map(VipAccountResponseDto::entityToResponse);
    }

    @PostMapping("")
    public Mono<VipAccountResponseDto> create(@RequestParam String documentNumber) {
        return vipAccountService.createVipAccount(documentNumber)
                .mapNotNull(VipAccountResponseDto::entityToResponse)
                .switchIfEmpty(Mono.error(new Exception("Error on create vip account")))
                .onErrorResume(throwable -> Mono.error(new Exception("Error on create vip account")));
    }

    @GetMapping("/{accountId}")
    @Override
    public Mono<VipAccountResponseDto> getById(@PathVariable String accountId) {
        return vipAccountService.findById(accountId)
                .mapNotNull(VipAccountResponseDto::entityToResponse)
                .switchIfEmpty(Mono.error(new Exception("Vip account not founded")))
                .onErrorResume(throwable -> Mono.error(new Exception("Vip account not founded")));
    }

    @PutMapping("")
    public Mono<VipAccountResponseDto> update(@RequestParam String accountNumber, @RequestBody VipAccountRequestDto request) {
        VipAccount vipAccount = VipAccountRequestDto.requestToEntity(request);
        return vipAccountService.update(accountNumber, vipAccount)
                .mapNotNull(VipAccountResponseDto::entityToResponse)
                .switchIfEmpty(Mono.error(new Exception("Vip account not founded")))
                .onErrorResume(throwable -> Mono.error(new Exception("Vip account not founded")));
    }

    @PutMapping("/disable")
    public Mono<VipAccountResponseDto> disableVipAccount(@RequestParam String accountNumber) {
        return vipAccountService.disableAccount(accountNumber)
                .mapNotNull(VipAccountResponseDto::entityToResponse)
                .switchIfEmpty(Mono.error(new Exception("Vip account not founded")))
                .onErrorResume(throwable -> Mono.error(new Exception("Vip account not founded")));
    }
}
