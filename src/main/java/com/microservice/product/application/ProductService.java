package com.microservice.product.application;

import com.microservice.product.domain.IProductService;
import com.microservice.product.domain.ProductEntity;
import com.microservice.product.domain.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;

    @Override
    public Flux<ProductEntity> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Mono<ProductEntity> save(ProductEntity product) {
        return this.productRepository.save(product);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.productRepository.deleteById(id);
    }

    @Override
    public Mono<ProductEntity> findById(String id) {
        return this.productRepository.findById(id);
    }
}
