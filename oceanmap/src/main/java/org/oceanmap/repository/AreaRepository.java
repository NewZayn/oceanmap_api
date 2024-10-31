package org.oceanmap.repository;

import org.oceanmap.model.UnsafeArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<UnsafeArea ,  Long>{

    
} 
