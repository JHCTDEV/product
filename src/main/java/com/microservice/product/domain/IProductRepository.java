package com.microservice.product.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductRepository extends ReactiveMongoRepository<ProductEntity,String> {
}
