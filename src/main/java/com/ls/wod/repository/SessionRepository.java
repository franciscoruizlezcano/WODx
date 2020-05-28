package com.ls.wod.repository;

import com.ls.wod.domain.Session;
import com.ls.wod.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface SessionRepository extends JpaRepository<Session, Integer>{
    @Query("select s from Session s where s.status = true")
    List<Session> findByStatus();
    
    @Query("select s from Session s where s.user = :user")
    List<Session> findByUser(@Param("user") User user);
    
    @Query("select s from Session s where s.user = :user and s.status = true")
    List<Session> findByUserAndStatus(@Param("user") User user);
}
