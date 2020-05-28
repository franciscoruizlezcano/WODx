package com.ls.wod.repository;

import com.ls.wod.domain.Exercise;
import com.ls.wod.domain.Typeexercise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface ExerciseRepository extends JpaRepository<Exercise, Integer>{
    
    @Query("select e from Exercise e where e.description like :description")
    List<Exercise> findByDescription(@Param("description") String description);
    
    @Query("select e from Exercise e where e.typeexercise = :typeexercise")
    List<Exercise> findByTypeExercise(@Param("typeexercise") Typeexercise typeexercise);
}
