package com.chikitania.modules;

import com.google.inject.AbstractModule;
import com.chikitania.ChikitaniaConfiguration;
import com.chikitania.resources.Product.dao.ProductDao;
import com.chikitania.resources.Product.dao.ProductDaoImpl;
import com.chikitania.resources.Product.service.ProductService;
import com.chikitania.resources.Product.service.ProductServiceImpl;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

/**
 * MainModule build the dependencies that need ChikitaniaConfiguration class.
 *
 * @author Ruth
 * @since 23/09/2019
 */
public class MainModule extends DropwizardAwareModule<ChikitaniaConfiguration> {
    /**
     * Binding setup.
     *
     * @see AbstractModule
     */
    @Override
    protected void configure() {
        bind(ProductDao.class).to(ProductDaoImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
    }
}