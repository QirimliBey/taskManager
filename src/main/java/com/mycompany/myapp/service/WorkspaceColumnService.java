package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.WorkspaceColumn;
import com.mycompany.myapp.repository.WorkspaceColumnRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing WorkspaceColumn.
 */
@Service
@Transactional
public class WorkspaceColumnService {

    private final Logger log = LoggerFactory.getLogger(WorkspaceColumnService.class);

    private final WorkspaceColumnRepository workspaceColumnRepository;

    public WorkspaceColumnService(WorkspaceColumnRepository workspaceColumnRepository) {
        this.workspaceColumnRepository = workspaceColumnRepository;
    }

    /**
     * Save a workspaceColumn.
     *
     * @param workspaceColumn the entity to save
     * @return the persisted entity
     */
    public WorkspaceColumn save(WorkspaceColumn workspaceColumn) {
        log.debug("Request to save WorkspaceColumn : {}", workspaceColumn);
        return workspaceColumnRepository.save(workspaceColumn);
    }

    /**
     * Get all the workspaceColumns.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<WorkspaceColumn> findAll(Pageable pageable) {
        log.debug("Request to get all WorkspaceColumns");
        return workspaceColumnRepository.findAll(pageable);
    }

    /**
     * Get one workspaceColumn by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public WorkspaceColumn findOne(Long id) {
        log.debug("Request to get WorkspaceColumn : {}", id);
        return workspaceColumnRepository.findOne(id);
    }

    /**
     * Delete the workspaceColumn by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkspaceColumn : {}", id);
        workspaceColumnRepository.delete(id);
    }
}
