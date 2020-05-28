package com.ls.wod.service;

/**
 *
 * @author francisco
 */

public interface AuthService{    
    String login(String username, String password);
    
    void logout(Integer id);
}
