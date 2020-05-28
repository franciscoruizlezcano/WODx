package com.ls.wod.repository;

import com.ls.wod.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface PhoneRepository extends JpaRepository<Phone, Integer>{
    @Query("select p from Phone p where p.phone = :phone")
    Phone findByPhone(@Param("phone") String phone);
}
