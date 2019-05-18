package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.domain.Workspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Workspace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    @Query("select workspace.tasks from Workspace workspace where workspace.id =:id")
    Page<Task> findAllTasksOfWorkspace(@Param("id") Long id, Pageable pageable);
}
