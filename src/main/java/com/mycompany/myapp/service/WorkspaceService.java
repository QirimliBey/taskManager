package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.domain.Workspace;
import com.mycompany.myapp.repository.WorkspaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Workspace.
 */
@Service
@Transactional
public class WorkspaceService {

    private final Logger log = LoggerFactory.getLogger(WorkspaceService.class);

    private final WorkspaceRepository workspaceRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    /**
     * Save a workspace.
     *
     * @param workspace the entity to save
     * @return the persisted entity
     */
    public Workspace save(Workspace workspace) {
        log.debug("Request to save Workspace : {}", workspace);
        return workspaceRepository.save(workspace);
    }

    /**
     * Get all the workspaces.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Workspace> findAll(Pageable pageable) {
        log.debug("Request to get all Workspaces");
        return workspaceRepository.findAll(pageable);
    }

    /**
     * Get one workspace by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Workspace findOne(Long id) {
        log.debug("Request to get Workspace : {}", id);
        return workspaceRepository.findOne(id);
    }

    /**
     * Get workspace projects.
     *
     * @param id the id of the entity
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Task> findWorkspaceTasks(Long id, Pageable pageable) {
        log.debug("Request to get Workspace tasks");
        return workspaceRepository.findAllTasksOfWorkspace(id, pageable);
    }

    /**
     * Delete the workspace by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Workspace : {}", id);
        workspaceRepository.delete(id);
    }
}
