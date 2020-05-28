package com.ls.wod.service;

import com.ls.wod.domain.Country;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface CountryService extends CrudService<Country>{
    
    List<Country> findByDescription(String description);
    
    Country findByPhoneCode(String phonecode);
    
}
