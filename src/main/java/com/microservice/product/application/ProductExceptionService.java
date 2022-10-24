package com.microservice.product.application;

import com.microservice.product.domain.services.IProductExceptionService;
import com.microservice.product.infrastructure.dto.ExceptionDto;
import com.microservice.product.infrastructure.dto.ResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductExceptionService implements IProductExceptionService {
    @Override
    public Mono<ResponseDto> convertToDto(Throwable exception) {
        ResponseDto responseDto = new ResponseDto();
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        responseDto.setSuccess(false);
        responseDto.setError(exceptionDto);
        return Mono.just(responseDto);
    }
}
