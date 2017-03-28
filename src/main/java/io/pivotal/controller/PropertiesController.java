package io.pivotal.controller;

import io.pivotal.db.PropertyRepositoryImpl;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.annotation.PostConstruct;

import io.pivotal.model.Property;

@RestController
class PropertyController {

    @Autowired
    PropertyRepositoryImpl propertyRepository;

    @RequestMapping(value = "/properties", method = GET)
    public Iterable<Property> getLuceneProperty(@RequestParam(value = "address") String address) throws Exception {
        return propertyRepository.luceneFindAFewByAddressFuzzy(address);
    }


    @PostConstruct
    public void doLoad() {

        Property one = new Property(1, 5280, "123 Main St.");
        Property two = new Property(2, 5280, "1256 Mane Row");
        Property three = new Property(3, 5280, "345 Sane Blvd");

        propertyRepository.save(one);
        propertyRepository.save(three);
        propertyRepository.save(two);

    }

}
