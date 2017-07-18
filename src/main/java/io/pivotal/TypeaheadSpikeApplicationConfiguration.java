package io.pivotal;

import java.util.Properties;

import org.apache.geode.cache.*;
import org.apache.geode.cache.lucene.LuceneService;
import org.apache.geode.cache.lucene.LuceneServiceProvider;
import org.apache.geode.cache.server.CacheServer;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.pivotal.model.Property;

@Configuration
public class TypeaheadSpikeApplicationConfiguration {

    @Value("${embedded-locator-host:localhost}")
    private String embeddedLocatorHost;

    @Value("${embedded-locator-port:10334}")
    private int embeddedLocatorPort;

    @Value("${cache-server.port:40404}")
    private int port;


    @Bean
    Properties geodeProperties() {
        Properties geodeProperties = new Properties();
        geodeProperties.setProperty("name", "DataGeodeTypeaheadSpike");
        geodeProperties.setProperty("start-locator", embeddedLocatorHost + "[" + embeddedLocatorPort + "]");
        //geodeProperties.setProperty("start-dev-rest-api", "true");
        //geodeProperties.setProperty("http-service-port", "7070");
        geodeProperties.setProperty("jmx-manager", "true");
        geodeProperties.setProperty("log-level", "config");
        return geodeProperties;
    }


    // Use the following if not using SDG
    @Bean
    public Cache createCache() throws Exception {
        CacheFactory cf = new CacheFactory();
        cf.set("start-locator", embeddedLocatorHost + "[" + embeddedLocatorPort + "]");
        cf.setPdxPersistent(true);
        cf.setPdxSerializer(new ReflectionBasedAutoSerializer("io.pivotal.model.*"));
        Cache c = cf.create();
        CacheServer cs = c.addCacheServer();
        cs.setPort(port);
        cs.start();
        return c;
    }

    @Bean
    public LuceneService luceneService(Cache cache) {
        return LuceneServiceProvider.get(cache);
    }

    @Bean
    public Region<Integer, Property> propertyRegion(Cache cache, LuceneService luceneService) {
        // Create Index on fields with default analyzer:
        luceneService.createIndexFactory()
               .addField("Address")
               .create("myIndex", "/Property");
        Region region = cache.createRegionFactory(RegionShortcut.PARTITION).create("Property");
        return region;
    }


}
