package com.ls.wod.service;

import com.ls.wod.domain.Session;
import com.ls.wod.domain.User;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface SessionService extends CrudService<Session>{
    List<Session> findByStatus();
    
    List<Session> findByUser(User user);
    
    List<Session> findByUserAndStatus(User user);
}
