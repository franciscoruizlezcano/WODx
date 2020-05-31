package com.ls.wod.service;

import com.ls.wod.domain.Task;
import com.ls.wod.domain.User;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface TaskService extends CrudService<Task>{
    List<Task> findByUser(User user);

    long countByUser(User user);
}
