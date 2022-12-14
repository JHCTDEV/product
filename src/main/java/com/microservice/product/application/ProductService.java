package com.microservice.product.application;

import com.microservice.product.domain.entities.ProductEntity;
import com.microservice.product.domain.services.IProductExceptionService;
import com.microservice.product.domain.services.IProductService;
import com.microservice.product.domain.repositories.IProductRepository;
import com.microservice.product.infrastructure.IModelMapper;
import com.microservice.product.infrastructure.dto.ProductDto;
import com.microservice.product.infrastructure.dto.ProductTypeDto;
import com.microservice.product.infrastructure.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IModelMapper modelMapper;
    @Autowired
    private IProductExceptionService productExceptionService;
    @Override
    public Mono<ResponseDto> findAll() {
        return this.productRepository.findAll().collectList().flatMap(listProductEntity -> {
            List<ProductTypeDto> listProductTypeDto = listProductEntity.stream().map(productEntity -> (ProductTypeDto) this.modelMapper.convert(productEntity, ProductTypeDto.class)).collect(Collectors.toList());
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setData(listProductTypeDto);
            return Mono.just(responseDto);
        });

    }
    @Override
    public Mono<ResponseDto> create(ProductDto productDto) {
        ProductEntity productEntity = (ProductEntity) this.modelMapper.convert(productDto,ProductEntity.class);
        productEntity.setCreateAt(new Date());
        productEntity.setStatus("1");
        return this.productRepository.save(productEntity).flatMap(entity -> {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setData(this.modelMapper.convert(entity,ProductTypeDto.class));
            return Mono.just(responseDto);
        });

    }

    @Override
    public Mono<ResponseDto> update(ProductDto productDto) {
        return this.productRepository.findById(productDto.getId()).flatMap(productEntity -> {
            if (productEntity.getId() == null)
                return Mono.error(new Exception("product does not exist"));
            return this.productRepository.save(productEntity).flatMap(entity -> {
                ResponseDto responseDto = new ResponseDto();
                responseDto.setSuccess(true);
                responseDto.setData(this.modelMapper.convert(entity,ProductDto.class));
                return Mono.just(responseDto);
            });

        }).onErrorResume(productExceptionService::convertToDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return null;
    }

    @Override
    public Mono<ResponseDto> findById(String id) {
        return this.productRepository.findById(id).flatMap(productEntity -> {
            ProductDto productDto = (ProductDto) this.modelMapper.convert(productEntity, ProductDto.class);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setData(productDto);
            return Mono.just(responseDto);
        });

    }
}
