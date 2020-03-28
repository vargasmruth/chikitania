package com.chikitania.modules;

import com.google.inject.Provides;
import com.chikitania.ChikitaniaConfiguration;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

/**
 * HibernateModule build the dependencies that need Hibernate and Hibernate SessionFactory.
 *
 * @author Ruth
 * @since 23/09/2019
 */
public class HibernateModule extends DropwizardAwareModule<ChikitaniaConfiguration> {

    private final HibernateBundleChikitania hibernate;

    public HibernateModule(HibernateBundleChikitania hibernate) {
        this.hibernate = hibernate;
    }

    @Override
    protected void configure() {

    }

    @Provides
    public SessionFactory provideSessionFactory() {
        return this.hibernate.getSessionFactory();
    }


}
