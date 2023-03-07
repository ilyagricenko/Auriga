package com.taskmanager.auriga.repository;

import com.taskmanager.auriga.dto.CountType;
import com.taskmanager.auriga.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM task ORDER BY due_date DESC", nativeQuery = true)
    public List<Task> getAllTaskDueDateDesc();

    @Query(value="SELECT new com.taskmanager.auriga.dto.CountType (COUNT(*)/(SELECT COUNT(*) FROM Task) * 100, type) FROM Task GROUP BY type")
    public List<CountType> getGroupByType();
}
