package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.WorkspaceColumn;
import com.mycompany.myapp.service.WorkspaceColumnService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkspaceColumn.
 */
@RestController
@RequestMapping("/api")
public class WorkspaceColumnResource {

    private final Logger log = LoggerFactory.getLogger(WorkspaceColumnResource.class);

    private static final String ENTITY_NAME = "workspaceColumn";

    private final WorkspaceColumnService workspaceColumnService;

    public WorkspaceColumnResource(WorkspaceColumnService workspaceColumnService) {
        this.workspaceColumnService = workspaceColumnService;
    }

    /**
     * POST  /workspace-columns : Create a new workspaceColumn.
     *
     * @param workspaceColumn the workspaceColumn to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workspaceColumn, or with status 400 (Bad Request) if the workspaceColumn has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/workspace-columns")
    @Timed
    public ResponseEntity<WorkspaceColumn> createWorkspaceColumn(@RequestBody WorkspaceColumn workspaceColumn) throws URISyntaxException {
        log.debug("REST request to save WorkspaceColumn : {}", workspaceColumn);
        if (workspaceColumn.getId() != null) {
            throw new BadRequestAlertException("A new workspaceColumn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkspaceColumn result = workspaceColumnService.save(workspaceColumn);
        return ResponseEntity.created(new URI("/api/workspace-columns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workspace-columns : Updates an existing workspaceColumn.
     *
     * @param workspaceColumn the workspaceColumn to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workspaceColumn,
     * or with status 400 (Bad Request) if the workspaceColumn is not valid,
     * or with status 500 (Internal Server Error) if the workspaceColumn couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/workspace-columns")
    @Timed
    public ResponseEntity<WorkspaceColumn> updateWorkspaceColumn(@RequestBody WorkspaceColumn workspaceColumn) throws URISyntaxException {
        log.debug("REST request to update WorkspaceColumn : {}", workspaceColumn);
        if (workspaceColumn.getId() == null) {
            return createWorkspaceColumn(workspaceColumn);
        }
        WorkspaceColumn result = workspaceColumnService.save(workspaceColumn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workspaceColumn.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workspace-columns : get all the workspaceColumns.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workspaceColumns in body
     */
    @GetMapping("/workspace-columns")
    @Timed
    public ResponseEntity<List<WorkspaceColumn>> getAllWorkspaceColumns(Pageable pageable) {
        log.debug("REST request to get a page of WorkspaceColumns");
        Page<WorkspaceColumn> page = workspaceColumnService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workspace-columns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /workspace-columns/:id : get the "id" workspaceColumn.
     *
     * @param id the id of the workspaceColumn to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workspaceColumn, or with status 404 (Not Found)
     */
    @GetMapping("/workspace-columns/{id}")
    @Timed
    public ResponseEntity<WorkspaceColumn> getWorkspaceColumn(@PathVariable Long id) {
        log.debug("REST request to get WorkspaceColumn : {}", id);
        WorkspaceColumn workspaceColumn = workspaceColumnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(workspaceColumn));
    }

    /**
     * DELETE  /workspace-columns/:id : delete the "id" workspaceColumn.
     *
     * @param id the id of the workspaceColumn to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/workspace-columns/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkspaceColumn(@PathVariable Long id) {
        log.debug("REST request to delete WorkspaceColumn : {}", id);
        workspaceColumnService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
