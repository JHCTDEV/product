package com.microservice.product.application;

import com.microservice.product.domain.entities.ProductEntity;
import com.microservice.product.domain.entities.ProductTypeEntity;
import com.microservice.product.domain.repositories.IProductRepository;
import com.microservice.product.domain.repositories.IProductTypeRepository;
import com.microservice.product.domain.services.IProductExceptionService;
import com.microservice.product.domain.services.IProductTypeService;
import com.microservice.product.infrastructure.IModelMapper;
import com.microservice.product.infrastructure.dto.ProductTypeDto;
import com.microservice.product.infrastructure.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductTypeService implements IProductTypeService {
    @Autowired
    private IProductTypeRepository productTypeRepository;
    @Autowired
    private IProductRepository     productRepository;
    @Autowired
    private IModelMapper           modelMapper;
    @Autowired
    private IProductExceptionService productExceptionService;
    @Override
    public Mono<ResponseDto> findAll() {
        return this.productTypeRepository.findAll().collectList().flatMap(listProductTypeEntity -> {
            List<ProductTypeDto> listProductTypeDto = listProductTypeEntity.stream().map(productTypeEntity -> (ProductTypeDto) this.modelMapper.convert(productTypeEntity, ProductTypeDto.class)).collect(Collectors.toList());
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setData(listProductTypeDto);
            return Mono.just(responseDto);
        });

    }

    @Override
    public Mono<ResponseDto> findByProduct(String idProduct) {
        Function<ProductEntity,Mono<ResponseDto>> getProductType = productEntity -> {
            return this.productTypeRepository.findById(productEntity.getType()).flatMap(productTypeEntity -> {
                ProductTypeDto productTypeDto = (ProductTypeDto) this.modelMapper.convert(productTypeEntity, ProductTypeDto.class);
                ResponseDto responseDto = new ResponseDto();
                responseDto.setSuccess(true);
                responseDto.setData(productTypeDto);
                return Mono.just(responseDto);
            });
        };
        return this.productRepository.findById(idProduct).flatMap(getProductType);
    }

    @Override
    public Mono<ResponseDto> create(ProductTypeDto productTypeDto) {
        ProductTypeEntity productTypeEntity = (ProductTypeEntity) this.modelMapper.convert(productTypeDto,ProductTypeEntity.class);
        productTypeEntity.setCreateAt(new Date());
        productTypeEntity.setStatus("1");
        return this.productTypeRepository.save(productTypeEntity).flatMap(entity -> {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setData(this.modelMapper.convert(entity,ProductTypeDto.class));
            return Mono.just(responseDto);
        });

    }

    @Override
    public Mono<ResponseDto> update(ProductTypeDto productTypeDto) {
        return this.productTypeRepository.findById(productTypeDto.getId()).flatMap(productTypeEntity -> {
            if (productTypeEntity.getId() == null)
                return Mono.error(new Exception("product type does not exist"));
            return this.productTypeRepository.save(productTypeEntity).flatMap(entity -> {
                ResponseDto responseDto = new ResponseDto();
                responseDto.setSuccess(true);
                responseDto.setData(this.modelMapper.convert(entity,ProductTypeDto.class));
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
        return this.productTypeRepository.findById(id).flatMap(productTypeEntity -> {
            ProductTypeDto productTypeDto = (ProductTypeDto) this.modelMapper.convert(productTypeEntity, ProductTypeDto.class);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setSuccess(true);
            responseDto.setData(productTypeDto);
            return Mono.just(responseDto);
        });
    }
}
