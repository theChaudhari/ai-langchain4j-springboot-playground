package com.langchain4j_poc.repository;

import com.langchain4j_poc.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByStatus(String filter);

    List<Task> findByStatusNot(String excludeStatus);

}
