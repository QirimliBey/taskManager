package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.domain.Project;
import com.mycompany.myapp.domain.Workspace;
import com.mycompany.myapp.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Project.
 */
@Service
@Transactional
public class ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Save a project.
     *
     * @param project the entity to save
     * @return the persisted entity
     */
    public Project save(Project project) {
        log.debug("Request to save Project : {}", project);
        return projectRepository.save(project);
    }

    /**
     * Get all the projects.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Project> findAll(Pageable pageable) {
        log.debug("Request to get all Projects");
        return projectRepository.findAll(pageable);
    }

    /**
     * Get one project by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Project findOne(Long id) {
        log.debug("Request to get Project : {}", id);
        Project oneWithEagerRelationships = projectRepository.findOneWithEagerRelationships(id);
        return oneWithEagerRelationships;
    }

    /**
     * Get project clients.
     *
     * @param id the id of the entity
     * @param pageble the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Client> findProjectClients(Long id, Pageable pageble) {
        log.debug("Request to get Project clients");
        return projectRepository.findAllClientsOfProject(id, pageble);
    }

    /**
     * Get project workspaces.
     *
     * @param id the id of the entity
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Workspace> findProjectWorkspaces(Long id, Pageable pageable) {
        log.debug("Request to get Project workspaces");
        return projectRepository.findAllWorkspacesOfProject(id, pageable);
    }


    /**
     * Delete the project by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.delete(id);
    }


}
