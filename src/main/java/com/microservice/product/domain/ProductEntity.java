package com.microservice.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivilegedAction;

@Document(collection = "product")
@Data
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private String type;
    private String status;
}
