package com.chikitania;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.chikitania.modules.HibernateBundleChikitania;
import com.chikitania.modules.HibernateModule;
import com.chikitania.modules.MainModule;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ChikitaniaApplication extends Application<ChikitaniaConfiguration> {

    private HibernateBundleChikitania hibernate;

    public static void main(final String[] args) throws Exception {
        new ChikitaniaApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<ChikitaniaConfiguration> bootstrap) {
        this.hibernate = new HibernateBundleChikitania();
        Hibernate5Module hm = new Hibernate5Module();
        hm.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        bootstrap.getObjectMapper().registerModule(hm);

        bootstrap.addBundle(new AssetsBundle("/swagger/", "/swagger"));
        bootstrap.addBundle(this.hibernate);
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new HibernateModule(this.hibernate), new MainModule())
                .build());
    }

    @Override
    public void run(final ChikitaniaConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(MultiPartFeature.class);
        this.setCORSconfiguration(environment);
        this.prepareSwaggerApiListingResource(environment,
                configuration.getServerName(), configuration.getVersion());
    }

    private void prepareSwaggerApiListingResource(final Environment environment, String nameServer, String version) {
        environment.jersey().register(new ApiListingResource());
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        BeanConfig config = new BeanConfig();
        config.setTitle(nameServer);
        config.setVersion(version);
        config.setBasePath("/api");
        config.setResourcePackage(this.getClass().getPackage().getName());
        config.setScan(true);
    }

    private void setCORSconfiguration(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter(CrossOriginFilter.EXPOSED_HEADERS_PARAM,
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location,username,password,token,admin,message,branch_office");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location,username,password,token,admin,message,branch_office");
        filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
    }
}
