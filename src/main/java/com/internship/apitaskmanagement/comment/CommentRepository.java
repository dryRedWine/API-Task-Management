package com.internship.apitaskmanagement.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    <S extends Comment> S save(S comment);

    @Override
    Optional<Comment> findById(Long id);

    @Override
    boolean existsById(Long id);

    boolean existsByIdAndWriterUsername(Long id, String username);

    @Override
    void deleteById(Long id);

    @Modifying
    @Query("update Comment c set c.text = ?1 where c.id = ?2")
    void setNewText(String newText, Long id);

    @Modifying
    @Query("update Comment c set c.changed = true where c.id = ?1")
    void setChangedStatusIsTrue(Long id);

    @Query("select count(c) > 0 " +
            "from Comment c " +
            "where c.id = ?1 and c.written > ?2")
    boolean timeCheck(Long commentId, LocalDateTime nowMinusHour);

}
