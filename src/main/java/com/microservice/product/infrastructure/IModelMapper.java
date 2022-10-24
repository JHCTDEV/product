package com.microservice.product.infrastructure;

import java.lang.reflect.Type;

public interface IModelMapper {
    Object convert(Object input, Type output);
}
