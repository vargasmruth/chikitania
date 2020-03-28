package com.chikitania.resources.Product.service;

import com.chikitania.api.Product;
import com.chikitania.api.custom.ProductResponse;

import javax.ws.rs.NotFoundException;
import java.util.List;

public interface ProductService {
    Integer save(ProductResponse request);

    List<ProductResponse> getAll();

    ProductResponse getById(Integer id) throws NotFoundException;

    void update(ProductResponse request) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;
}
