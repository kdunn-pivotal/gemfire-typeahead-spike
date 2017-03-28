package io.pivotal.db;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.lucene.LuceneQuery;
import org.apache.geode.cache.lucene.LuceneService;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.gemfire.mapping.Region;
//import org.springframework.data.gemfire.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.context.annotation.Configuration;

import io.pivotal.model.Property;

//@Region("Property")
@Configuration
public class PropertyRepository { // implements CrudRepository<Property, Long> {

	@Autowired
	Cache cache;
	
	@Autowired
	Region<Integer, Property> propertyRegion;
	
	@Autowired
	LuceneService luceneService;
	
	QueryService qs;
	
	@PostConstruct
	void setQueryService() {
		this.qs = cache.getQueryService();
	}
	
	public Collection<Property> findAFewByAddressFuzzy(String address) throws Exception {
		//Object[] params = new Object[1];
		//params[0] = address;
		
		String regionName = "Property";
		String indexName = "propertyIndex";
		
		LuceneQuery<String, Property> query = luceneService.createLuceneQueryFactory()
				  .setResultLimit(10)
				  .create(indexName, regionName, address, "Address");

		return query.findValues();
		
		//String queryString = "SELECT * FROM /Property p WHERE p.address LIKE $1 LIMIT 5";
		//Query theQuery = qs.newQuery(queryString);
		//return (Iterable<Property>) theQuery.execute(params);
	}

	//@Query("SELECT count(*) FROM /Property p")
	public Integer getCountOfProperties() throws Exception {
		String queryString = "SELECT count(*) FROM /Property p";
		Query theQuery = qs.newQuery(queryString);
		return (Integer) theQuery.execute();
	}

	// @Query("SELECT p.address FROM /Property p LIMIT 5")
	@SuppressWarnings("unchecked")
	public Iterable<String> getProperties() throws Exception {
		String queryString = "SELECT p.address FROM /Property p LIMIT 5";
		Query theQuery = qs.newQuery(queryString);
		return (Iterable<String>) theQuery.execute();
	}
	
	public void save(Property p) {
		propertyRegion.put(p.getId(), p);
	}
}
