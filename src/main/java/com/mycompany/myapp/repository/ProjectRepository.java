package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.domain.Project;
import com.mycompany.myapp.domain.Workspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Project entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select distinct project from Project project left join fetch project.clients")
    List<Project> findAllWithEagerRelationships();

    @Query("select project from Project project left join fetch project.clients where project.id =:id")
    Project findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select project.clients from Project project where project.id =:id")
    Page<Client> findAllClientsOfProject(@Param("id") Long id, Pageable pageable);

    @Query("select project.workspaces from Project project where project.id =:id")
    Page<Workspace> findAllWorkspacesOfProject(@Param("id") Long id, Pageable pageable);

}
