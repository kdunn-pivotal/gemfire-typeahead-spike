package io.pivotal.db;

import io.pivotal.model.Property;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.lucene.LuceneQuery;
import org.apache.geode.cache.lucene.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PropertyRepositoryImpl implements PropertyRepository {

    @Autowired
    Region<Integer, Property> propertyRegion;

    @Autowired
    LuceneService luceneService;

    @Override
    public Iterable<Property> luceneFindAFewByAddressFuzzy(String address) throws Exception {
        LuceneQuery<Integer, Property> query = luceneService.createLuceneQueryFactory()
                .setResultLimit(10)
                .create("myIndex", "/Property", address, "Address");
        Iterable<Property> queryResult = query.findValues();
        return queryResult;
    }

    @Override
    public void save(Property property) {
        Integer propertyKey = property.getId();
        propertyRegion.put(propertyKey, property);
    }
}
