package com.ls.wod.service.imp;

import com.ls.wod.domain.Session;
import com.ls.wod.domain.TypeuserRole;
import com.ls.wod.domain.User;
import com.ls.wod.exception.AuthorizationException;
import com.ls.wod.exception.UsernamePasswordException;
import com.ls.wod.exception.UnsupportedParameterException;
import com.ls.wod.repository.SessionRepository;
import com.ls.wod.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.ls.wod.service.AuthService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Transactional
    public String authentication(String username, String password) {
        if (username != null || !username.isEmpty() && password != null || !password.isEmpty()) {
            User user = userRepository.findByUsername(username).orElseThrow(UsernamePasswordException::new);
            if (user.getIdUser() != null && user.getIdUser() != 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                log.info(encoder.encode(password));
                if (encoder.matches(password, user.getPassword())) {
                    Session session = new Session();
                    session.setUser(user);
                    session.setCreationdate(new Date());
                    session.setStatus(true);
                    Session save = sessionRepository.save(session);
                    return save.toString();
                } else {
                    throw new UsernamePasswordException();
                }
            } else {
                throw new UsernamePasswordException();
            }
        } else {
            throw new UnsupportedParameterException();
        }
    }

    @Override
    public String login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logout(Integer id) {
        User user = userRepository.findById(id).orElseThrow(UnsupportedParameterException::new);
        List<Session> sessions = sessionRepository.findByUser(user);

        for (Session session : sessions) {
            session.setDeletiondate(new Date());
        }

        sessionRepository.saveAll(sessions);
    }

}
