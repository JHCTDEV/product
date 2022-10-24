package com.microservice.product.infrastructure.rest;

import com.microservice.product.domain.services.IProductTypeService;
import com.microservice.product.infrastructure.IModelMapper;
import com.microservice.product.infrastructure.dto.ProductTypeDto;
import com.microservice.product.infrastructure.dto.ResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("productType")
public class ProductTypeRestController {
    @Autowired
    private IProductTypeService productTypeService;
    @Autowired
    private IModelMapper modelMapper;
    @PostMapping("save")
    public Mono<ResponseDto> create(@RequestBody ProductTypeDto customerTypeDto){
        return this.productTypeService.create(customerTypeDto);
    }
    @PutMapping("save")
    public Mono<ResponseDto> update(@RequestBody ProductTypeDto customerTypeDto){
        return this.productTypeService.update(customerTypeDto);
    }
    @GetMapping("list")
    public Mono<ResponseDto> findAll(){
        return this.productTypeService.findAll();
    }
    @GetMapping("get/{id}")
    public Mono<ResponseDto> getById(@PathVariable("id") String id){
        return this.productTypeService.findById(id);
    }

}
