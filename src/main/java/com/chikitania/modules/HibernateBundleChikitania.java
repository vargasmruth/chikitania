package com.chikitania.modules;

import com.chikitania.ChikitaniaConfiguration;
import com.chikitania.api.*;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

/**
 * HibernateBundle set the models for DataBases.
 *
 * @author Ruth
 * @since 23/09/2019
 */
public class HibernateBundleChikitania extends HibernateBundle<ChikitaniaConfiguration> {
    public HibernateBundleChikitania() {
        super(Product.class, EventStock.class);
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(ChikitaniaConfiguration chikitaniaConfiguration) {
        return chikitaniaConfiguration.getDataSourceFactory();
    }
}