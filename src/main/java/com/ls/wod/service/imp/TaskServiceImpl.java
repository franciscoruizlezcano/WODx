package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.TaskRepository;
import com.ls.wod.domain.Task;
import com.ls.wod.domain.User;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.service.TaskService;
import java.util.List;

/**
 *
 * @author francisco
 */

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Task> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByUser(User user) {
        return repository.countByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return (List<Task>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Task findById(Task t) {
        return repository.findById(t.getIdTask()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Task> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Task t) {
        return repository.existsById(t.getIdTask());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Task save(Task t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Task> saveAll(Iterable<Task> itrbl) {
        return repository.saveAll(itrbl);

    }

    @Override
    @Transactional
    public void delete(Task t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Task> itrbl) {
        repository.deleteAll(itrbl);
    }
}
