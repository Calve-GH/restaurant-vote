package com.github.calve.repository.datajpa;

import com.github.calve.model.VoteLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteLogRepo extends JpaRepository<VoteLog, Integer> {

    Optional<VoteLog> findByUserId(Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM VoteLog v WHERE v.user.id=:id")
    int delete(@Param("id") int userId);

    @Transactional
    @Override
    VoteLog save(VoteLog voteLog);

    List<VoteLog> findAll();

    @Transactional
    @Override
    void deleteAll();

    //TODO TEST
    @Transactional
    void deleteAllByDateBefore(LocalDate date);
}
