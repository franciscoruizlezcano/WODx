package com.ls.wod.repository;

import com.ls.wod.domain.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface CountryRepository extends JpaRepository<Country, Integer>{
    
    @Query("select c from Country c where c.description like :description")
    List<Country> findByDescription(@Param("description") String description);
    
    @Query("select c from Country c where c.phonecode = :phonecode")
    Country findByPhoneCode(@Param("phonecode") String phonecode);
    
}
