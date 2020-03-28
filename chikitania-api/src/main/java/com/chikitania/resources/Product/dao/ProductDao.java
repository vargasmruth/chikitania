package com.chikitania.resources.Product.dao;


import com.chikitania.api.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Integer save(Product product);

    List<Product> getAll();

    Optional<Product> getById(Integer productId);

}
