package com.microservice.product.infrastructure.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Data
public class ProductDto {
    private String id;
    private String code;
    private String name;
    private Float minimumAmount;
    private String description;
    private String type;
    private Date createAt;
    private Date updateAt;
    private String status;

}
