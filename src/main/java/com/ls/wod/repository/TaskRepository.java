package com.ls.wod.repository;

import com.ls.wod.domain.Task;
import com.ls.wod.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface TaskRepository extends JpaRepository<Task, Integer>{
    @Query("select t from Task t where t.user = :user")
    List<Task> findByUser(@Param("user") User user);

    @Query("select count(t) from Task t where t.user = :user")
    long countByUser(@Param("user") User user);
}