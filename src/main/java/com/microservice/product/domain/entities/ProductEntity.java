package com.microservice.product.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivilegedAction;
import java.util.Date;

@Document(collection = "product")
@Data
public class ProductEntity {
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private Float minimumAmount;
    private Integer maximumNumberTransactions;
    private String type;
    private Date createAt;
    private Date updateAt;
    private String status;
}
