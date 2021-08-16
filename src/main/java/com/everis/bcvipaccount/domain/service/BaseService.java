package com.everis.bcvipaccount.domain.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService <T>{
    Flux<T> findAll();
    Mono<T> findById(String id);
    Mono<T> create(T request);
    Mono<T> update(String id, T request);
}
