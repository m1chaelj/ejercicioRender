package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public Task save(Task task) {
        Long id = counter.getAndIncrement();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    public Task update(Long id, Task task) {
        if (tasks.containsKey(id)) {
            task.setId(id);
            tasks.put(id, task);
            return task;
        }
        return null;
    }

    public boolean delete(Long id) {
        return tasks.remove(id) != null;
    }
}
