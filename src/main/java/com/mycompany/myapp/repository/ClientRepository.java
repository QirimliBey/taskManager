package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select distinct client from Client client left join fetch client.projects")
    List<Client> findAllWithEagerRelationships();

    @Query("select client from Client client left join fetch client.projects where client.id =:id")
    Client findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select client.projects from Client client where client.id =:id")
    Page<Project> findAllProjectsOfClient(@Param("id") Long id, Pageable pageable);
}
