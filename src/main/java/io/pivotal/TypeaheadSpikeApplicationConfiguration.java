package io.pivotal;

//import org.apache.geode.cache.Cache;
//import org.apache.geode.cache.CacheFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.gemfire.CacheFactoryBean;
//import org.springframework.data.gemfire.PartitionedRegionFactoryBean;
//import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionFactory;
import org.apache.geode.cache.RegionShortcut;
import org.apache.geode.cache.lucene.LuceneServiceProvider;
import org.apache.geode.cache.server.CacheServer;
//import org.apache.geode.cache.GeodeCache;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.apache.geode.cache.lucene.LuceneService;

import io.pivotal.model.Property;

//@EnableGemfireRepositories
@Configuration
public class TypeaheadSpikeApplicationConfiguration {

	@Value("${embedded-locator-host:localhost}")
	private String embeddedLocatorHost;

	@Value("${embedded-locator-port:10334}")
	private int embeddedLocatorPort;

	@Value("${cache-server.port:40404}")
	private int port;

	/*
	 * @Bean Properties geodeProperties() { Properties geodeProperties = new
	 * Properties(); geodeProperties.setProperty("name",
	 * "DataGeodeTypeaheadSpike"); geodeProperties.setProperty("start-locator",
	 * embeddedLocatorHost + "[" + embeddedLocatorPort + "]");
	 * //geodeProperties.setProperty("start-dev-rest-api", "true");
	 * //geodeProperties.setProperty("http-service-port", "7070");
	 * geodeProperties.setProperty("jmx-manager", "true");
	 * geodeProperties.setProperty("log-level", "config"); return
	 * geodeProperties; }
	 * 
	 * @Bean CacheFactoryBean geodeCache() { CacheFactoryBean geodeCache = new
	 * CacheFactoryBean(); geodeCache.setClose(true);
	 * //geodeCache.setPdxReadSerialized(true);
	 * //geodeCache.setPdxPersistent(true); geodeCache.setPdxSerializer(new
	 * ReflectionBasedAutoSerializer("io.pivotal.model"));
	 * geodeCache.setProperties(geodeProperties()); return geodeCache; }
	 */

	/* Use the following if not using SDG */
	@Bean
	public Cache createCache() throws Exception {

		// LOG.info("creating Cache: locators={}, port={}", locators, port);
		CacheFactory cf = new CacheFactory();
		cf.set("name", "DataGeodeTypeaheadSpike");
		cf.set("start-locator", embeddedLocatorHost + "[" + embeddedLocatorPort + "]");
		cf.set("jmx-manager", "true");

		// cf.setPdxPersistent(true);
		cf.setPdxSerializer(new ReflectionBasedAutoSerializer("io.pivotal.model.*"));

		Cache c = cf.create();

		// LOG.info("Creating CacheServer");
		CacheServer cs = c.addCacheServer();
		cs.setPort(port);
	
		cs.start();

		return c;
	}

	/* Use the following if not using SDG */
	/*
	 * @Bean public Region<String, Property> propertyRegion(Cache cache) {
	 * RegionFactory<String, Property> rf =
	 * cache.createRegionFactory(RegionShortcut.PARTITION); return
	 * rf.create("Property"); }
	 */

	@Bean
	public LuceneService luceneService(Cache cache) {
		return LuceneServiceProvider.get(cache);
	}

	@Bean
	public Region<Integer, Property> propertyRegion(Cache cache, LuceneService luceneService) {
		String regionName = "Property";
		String indexName = "propertyIndex";

		// Create Index on fields with default analyzer:
		luceneService.createIndex(indexName, regionName, "Address");
		RegionFactory<Integer, Property> rf = cache.createRegionFactory(RegionShortcut.PARTITION);
		return rf.create(regionName);
	}

	/*
	 * @Bean public PartitionedRegionFactoryBean<Long, Property>
	 * propertyRegion(final Cache cache) { PartitionedRegionFactoryBean<Long,
	 * Property> rf = new PartitionedRegionFactoryBean<>(); rf.setCache(cache);
	 * rf.setName("Property"); return rf; }
	 */

}
