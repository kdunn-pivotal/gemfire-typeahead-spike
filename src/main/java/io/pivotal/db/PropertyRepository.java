package io.pivotal.db;

import org.springframework.data.gemfire.mapping.Region;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.gemfire.repository.query.annotation.Import;
import org.springframework.data.repository.CrudRepository;

import io.pivotal.model.Property;

@Region("Property")
public interface PropertyRepository extends CrudRepository<Property, Long> {

	@Query("SELECT * FROM /Property p WHERE p.address LIKE $1 LIMIT 5")
	Iterable<Property> findAFewByAddressFuzzy(String address);
	
	Iterable<Property> findByAddressIgnoreCaseContaining(String address);

	@Query("SELECT count(*) FROM /Property p")
	Integer getCountOfProperties();

	@Query("SELECT p.address FROM /Property p LIMIT 5")
	Iterable<String> getProperties();
}
