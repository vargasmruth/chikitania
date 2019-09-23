package com.chikitania.resources.Product.service;

import com.google.inject.Inject;
import com.chikitania.api.Product;
import com.chikitania.api.custom.ProductResponse;
import com.chikitania.core.UtilsValidators;
import com.chikitania.resources.Product.dao.ProductDao;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Inject
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Integer save(ProductResponse request) {
        Product product = new Product();
        product.name = request.name;
        product.price = request.price;

        return productDao.save(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        List<ProductResponse> response = new ArrayList<>();
        productDao.getAll().forEach(x -> response.add(new ProductResponse(x.id, x.name, x.price)));

        return response;
    }

    @Override
    public ProductResponse getById(Integer id) {
        Product product = productDao.getById(id).orElseThrow(() -> new NotFoundException("El producto no existe."));

        return new ProductResponse(product.id, product.name, product.price);
    }

    @Override
    public void update(ProductResponse request) throws NotFoundException {
        Product productToUpdate = productDao.getById(request.id).orElseThrow(() -> new NotFoundException("El producto no existe."));
        productToUpdate.name = request.name;
        productToUpdate.price = request.price;

        productDao.save(productToUpdate);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        Product productToDelete = productDao.getById(id).orElseThrow(() -> new NotFoundException("El producto no existe."));
        productToDelete.state = false;

        productDao.save(productToDelete);
    }
}
