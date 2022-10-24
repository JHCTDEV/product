package com.microservice.product.domain.repositories;

import com.microservice.product.domain.entities.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductRepository extends ReactiveMongoRepository<ProductEntity,String> {
}
