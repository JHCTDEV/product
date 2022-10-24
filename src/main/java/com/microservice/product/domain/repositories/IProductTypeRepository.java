package com.microservice.product.domain.repositories;

import com.microservice.product.domain.entities.ProductTypeEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductTypeRepository extends ReactiveMongoRepository<ProductTypeEntity, String> {
}
