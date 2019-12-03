package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WorkspaceColumn;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the WorkspaceColumn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkspaceColumnRepository extends JpaRepository<WorkspaceColumn, Long> {

}
