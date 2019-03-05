package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Workspace;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Workspace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

}
