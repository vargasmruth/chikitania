package com.chikitania.resources.Product.dao;

import com.google.inject.Inject;
import com.chikitania.api.Product;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl extends AbstractDAO<Product> implements ProductDao {
    @Inject
    public ProductDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Integer save(Product product) {
        return persist(product).id;
    }

    @Override
    public List<Product> getAll() {
        return query("SELECT p FROM Product p where p.state = true  " +
                " ORDER BY p.id DESC ")
                .list();
    }

    @Override
    public Optional<Product> getById(Integer productId) {
        return Optional.ofNullable(get(productId));
    }


}
