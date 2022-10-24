package com.microservice.product.domain.services;

import com.microservice.product.infrastructure.dto.ResponseDto;
import reactor.core.publisher.Mono;

public interface IProductExceptionService {
    Mono<ResponseDto> convertToDto(Throwable exception);
}
