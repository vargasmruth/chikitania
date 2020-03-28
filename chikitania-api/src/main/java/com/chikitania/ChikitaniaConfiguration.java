package com.chikitania;

import com.chikitania.config.ExternalServer;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

public class ChikitaniaConfiguration extends Configuration {

    private String serverName;

    private String version;

    private Set<ExternalServer> externalServers;

    private String uploadDirectory;

    private double installerCommission;

    public double getInstallerCommission() {
        return installerCommission;
    }

    public void setInstallerCommission(double installerCommission) {
        this.installerCommission = installerCommission;
    }

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    public String getServerName() {
        return this.serverName;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("externalServers")
    public Set<ExternalServer> getExternalServers() {
        return externalServers;
    }

    @JsonProperty("externalServers")
    public void setExternalServers(Set<ExternalServer> externalServers) {
        this.externalServers = externalServers;
    }

    public ExternalServer getExternalSever(String name) {
        return this.externalServers.stream()
                .filter(externalServer -> externalServer.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @JsonProperty
    public String getVersion() {
        return this.version;
    }

    @JsonProperty
    public String getUploadDirectory() {
        return this.uploadDirectory;
    }
}
