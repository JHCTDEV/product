package com.microservice.product.infrastructure;

import com.microservice.product.domain.IProductService;
import com.microservice.product.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class Controller {
    @Autowired
    private IProductService productService;

    @GetMapping(value = "list", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductEntity> findAll(){
        return this.productService.findAll();
    }

    @GetMapping(value = "get/{id}")
    Mono<ProductEntity> findById(@PathVariable("id") String id){
        return this.productService.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public Mono<Void> delete(@PathVariable("id") String id){
        return this.productService.delete(id);
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductEntity> save(@RequestBody ProductEntity product){
        return this.productService.save(product);

    }

}
