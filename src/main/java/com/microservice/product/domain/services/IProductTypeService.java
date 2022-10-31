package com.microservice.product.domain.services;

import com.microservice.product.domain.entities.ProductEntity;
import com.microservice.product.infrastructure.dto.ProductTypeDto;
import com.microservice.product.infrastructure.dto.ResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductTypeService {
    Mono<ResponseDto> findAll();
    Mono<ResponseDto> create(ProductTypeDto productTypeDto);
    Mono<ResponseDto> update(ProductTypeDto productTypeDto);
    Mono<Void> delete(String id);
    Mono<ResponseDto> findById(String id);
    Mono<ResponseDto> findByProduct(String id);
}
