package com.microservice.product.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    Flux<ProductEntity> findAll();
    Mono<ProductEntity> save(ProductEntity product);
    Mono<Void> delete(String id);
    Mono<ProductEntity> findById(String id);
}
