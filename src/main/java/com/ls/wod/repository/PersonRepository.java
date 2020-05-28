package com.ls.wod.repository;

import com.ls.wod.domain.Person;
import com.ls.wod.domain.Phone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface PersonRepository extends JpaRepository<Person, Integer>{
    
    @Query("select p from Person p where p.email = :email")
    Person findByEmail(@Param("email") String email);
    
    @Query("select p from Person p where p.phone = :phone")
    List<Person> findByPhone(@Param("phone") Phone phone);
    
}
