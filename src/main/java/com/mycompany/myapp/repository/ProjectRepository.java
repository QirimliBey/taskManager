package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Project;
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

}
