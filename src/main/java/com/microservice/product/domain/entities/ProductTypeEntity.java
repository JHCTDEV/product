package com.microservice.product.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("productType")
public class ProductTypeEntity {
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private Date createAt;
    private Date updateAt;
    private String status;

}
