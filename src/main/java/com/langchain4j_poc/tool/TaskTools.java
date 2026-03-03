package com.langchain4j_poc.tool;


import com.langchain4j_poc.entity.Task;
import com.langchain4j_poc.repository.TaskRepository;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskTools {

    private final TaskRepository repository;

    @Tool("Create a new task with given title")
    public String createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setStatus("CREATED");
        repository.save(task);
        return "Task created successfully";
    }

    @Tool("Get all tasks")
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Tool("Update task status by id")
    public String updateTask(Long id, String status) {
        Task task = repository.findById(id).orElseThrow();
        task.setStatus(status);
        repository.save(task);
        return "Task updated successfully";
    }

    @Tool("Filter tasks by status. Valid status values are: CREATED, IN_PROGRESS, COMPLETED, CANCELLED")
    public List<Task> getFilteredTasks(String filter) {
        return repository.findByStatus(filter);
    }

    @Tool("Get all tasks excluding a specific status. Use this when user asks for tasks that are NOT a certain status.")
    public List<Task> getTasksExcludingStatus(String excludeStatus) {
        return repository.findByStatusNot(excludeStatus);
    }

}