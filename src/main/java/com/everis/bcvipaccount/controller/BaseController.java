package com.everis.bcvipaccount.controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseController<T, R> {
    Flux<R> getAll();
    Mono<R> getById(String id);
    Mono<R> update(String id, T request);
}
