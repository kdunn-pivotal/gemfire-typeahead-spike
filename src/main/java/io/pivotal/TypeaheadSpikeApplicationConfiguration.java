package io.pivotal;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.pdx.ReflectionBasedAutoSerializer;

import io.pivotal.model.Property;

@Configuration
@EnableGemfireRepositories
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
        geodeProperties.setProperty("start-locator", embeddedLocatorHost + "[" + embeddedLocatorPort  + "]");
        //geodeProperties.setProperty("start-dev-rest-api", "true");
        //geodeProperties.setProperty("http-service-port", "7070");
        geodeProperties.setProperty("jmx-manager", "true");
        geodeProperties.setProperty("log-level", "config");
        return geodeProperties;
    }

    @Bean
    CacheFactoryBean geodeCache() {
        CacheFactoryBean geodeCache = new CacheFactoryBean();
        geodeCache.setClose(true);
        //geodeCache.setPdxReadSerialized(true);
        //geodeCache.setPdxPersistent(true);
        geodeCache.setPdxSerializer(new ReflectionBasedAutoSerializer("io.pivotal.model"));
        geodeCache.setProperties(geodeProperties());
        return geodeCache;
    }
    
    /* Use the following if not using SDG
    @Bean
    public Cache createCache() throws Exception {
    	
        //LOG.info("creating Cache: locators={}, port={}", locators, port);
        CacheFactory cf = new CacheFactory();
        cf.set("start-locator", embeddedLocatorHost + "[" + embeddedLocatorPort  + "]");

        cf.setPdxPersistent(true);
        cf.setPdxSerializer(new ReflectionBasedAutoSerializer("io.pivotal.model.*"));

        Cache c = cf.create();

        //LOG.info("Creating CacheServer");
        CacheServer cs = c.addCacheServer();
        cs.setPort(port);
        cs.start();

        return c;
    }
    */

    @Bean
    public ReplicatedRegionFactoryBean<Long, Property> propertyRegion(final GemFireCache cache) {   
    	// RegionShortcut.REPLICATE
    	ReplicatedRegionFactoryBean<Long, Property> rf = new ReplicatedRegionFactoryBean<>();
    	rf.setCache(cache);
    	rf.setName("Property");
        return rf;
    }
    
}
