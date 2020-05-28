package com.ls.wod.repository;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Person;
import com.ls.wod.domain.Typeuser;
import com.ls.wod.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);
    
    @Query("select u from User u where u.person = :person")
    User findByPerson(@Param("person") Person person);
    
    @Query("select u from User u where u.typeuser = :typeuser")
    Iterable<User> findByTypeUser(@Param("typeuser") Typeuser typeuser);
    
    @Query("select u from User u where u.company = :company")
    Iterable<User> findByCompany(@Param("company") Company company);
    
    @Query("select u from User u where u.company = :company and u.typeuser = :typeuser")
    Iterable<User> findByCompanyAndTypeUser(@Param("company") Company company ,@Param("typeuser") Typeuser typeuser);
    
}
