package com.microservice.product.infrastructure.rest;

import com.microservice.product.domain.services.IProductService;
import com.microservice.product.infrastructure.IModelMapper;
import com.microservice.product.infrastructure.dto.ProductDto;
import com.microservice.product.infrastructure.dto.ProductTypeDto;
import com.microservice.product.infrastructure.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductRestController {
    @Autowired
    private IProductService productService;
    @PostMapping("save")
    public Mono<ResponseDto> create(@RequestBody ProductDto productDto){
        return this.productService.create(productDto);
    }
    @PutMapping("save")
    public Mono<ResponseDto> update(@RequestBody ProductDto productDto){
        return this.productService.update(productDto);
    }
    @GetMapping("list")
    public Mono<ResponseDto> findAll(){
        return this.productService.findAll();
    }
    @GetMapping("get/{id}")
    public Mono<ResponseDto> getById(@PathVariable("id") String id){
        return this.productService.findById(id);
    }

}
