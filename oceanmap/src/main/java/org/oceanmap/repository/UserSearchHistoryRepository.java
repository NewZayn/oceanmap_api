package org.oceanmap.repository;


import org.oceanmap.model.SearchHistory;
import org.oceanmap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    @Query("SELECT u FROM SearchHistory u WHERE u.id = :id")
    List<SearchHistory> findAllById(@Param("id") Long id);

}
