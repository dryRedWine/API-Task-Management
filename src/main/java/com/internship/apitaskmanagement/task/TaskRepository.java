package com.internship.apitaskmanagement.task;

import com.internship.apitaskmanagement.enums.Status;
import com.internship.apitaskmanagement.user.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override
    <S extends Task> S save(S entity);

    @Override
    Optional<Task> findById(Long aLong);

    @Override
    void deleteById(Long taskId);


    boolean existsTaskByIdAndAuthorUsername(Long taskId, String username);

    boolean existsTaskByIdAndExecutorsContains(Long taskId, User executor);

    @Modifying
    @Query("update Task t set t.executors=?2 where t.id = ?1")
    void changeExecutor(Long taskId, Set<User> executors);

    @Modifying
    @Query("update Task t set t.status=?2 where t.id = ?1")
    void changeStatus(Long taskId, Status status);

    List<Task> findAllByAuthorUsernameOrExecutorsContains(String username, User executor, Pageable pageable);
}
