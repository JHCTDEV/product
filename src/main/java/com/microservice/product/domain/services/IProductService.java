package com.microservice.product.domain.services;

import com.microservice.product.domain.entities.ProductEntity;
import com.microservice.product.infrastructure.dto.ProductDto;
import com.microservice.product.infrastructure.dto.ProductTypeDto;
import com.microservice.product.infrastructure.dto.ResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    Mono<ResponseDto> findAll();
    Mono<ResponseDto> create(ProductDto productTypeDto);
    Mono<ResponseDto> update(ProductDto productTypeDto);
    Mono<Void> delete(String id);
    Mono<ResponseDto> findById(String id);
}
