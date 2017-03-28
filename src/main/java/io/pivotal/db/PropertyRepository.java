package io.pivotal.db;


import io.pivotal.model.Property;

public interface PropertyRepository {

    Iterable<Property> luceneFindAFewByAddressFuzzy(String address) throws Exception;

    void save(Property property);
}
