package io.pivotal.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.annotation.PostConstruct;

import io.pivotal.db.PropertyRepository;
import io.pivotal.model.Property;

@RestController
class PropertyController {
    
    @Autowired
    private PropertyRepository propertyRepository;

    @RequestMapping(value = "/properties", method = GET)
    public Iterable<Property> getProperty(@RequestParam(value = "address") String address) throws Exception {
        return propertyRepository.findAFewByAddressFuzzy(address);
    }
    
    @RequestMapping(value = "/nproperties", method = GET)
    public Integer getPropertyCount() throws Exception {
        return propertyRepository.getCountOfProperties();
    }
    
    @RequestMapping(value = "/aproperties", method = GET)
    public Iterable<String> getProperties() throws Exception {
        return propertyRepository.getProperties();
    }
    
    @PostConstruct 
    public void doLoad(){
    	Property one = new Property(1, "123 Main St.", 5280);
    	Property two = new Property(2, "1256 Mane Row", 0);
    	Property three = new Property(3, "345 Sane Blvd", 400);
    	
    	propertyRepository.save(one);
    	propertyRepository.save(three);
    	propertyRepository.save(two);
    	
    	//System.out.println("Did some loads.");
    }
    
}
