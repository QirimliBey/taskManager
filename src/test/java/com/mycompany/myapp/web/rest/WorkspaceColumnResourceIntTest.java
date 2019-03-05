package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TaskManagerApp;

import com.mycompany.myapp.domain.WorkspaceColumn;
import com.mycompany.myapp.repository.WorkspaceColumnRepository;
import com.mycompany.myapp.service.WorkspaceColumnService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the WorkspaceColumnResource REST controller.
 *
 * @see WorkspaceColumnResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskManagerApp.class)
public class WorkspaceColumnResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private WorkspaceColumnRepository workspaceColumnRepository;

    @Autowired
    private WorkspaceColumnService workspaceColumnService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWorkspaceColumnMockMvc;

    private WorkspaceColumn workspaceColumn;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WorkspaceColumnResource workspaceColumnResource = new WorkspaceColumnResource(workspaceColumnService);
        this.restWorkspaceColumnMockMvc = MockMvcBuilders.standaloneSetup(workspaceColumnResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WorkspaceColumn createEntity(EntityManager em) {
        WorkspaceColumn workspaceColumn = new WorkspaceColumn()
            .name(DEFAULT_NAME);
        return workspaceColumn;
    }

    @Before
    public void initTest() {
        workspaceColumn = createEntity(em);
    }

    @Test
    @Transactional
    public void createWorkspaceColumn() throws Exception {
        int databaseSizeBeforeCreate = workspaceColumnRepository.findAll().size();

        // Create the WorkspaceColumn
        restWorkspaceColumnMockMvc.perform(post("/api/workspace-columns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workspaceColumn)))
            .andExpect(status().isCreated());

        // Validate the WorkspaceColumn in the database
        List<WorkspaceColumn> workspaceColumnList = workspaceColumnRepository.findAll();
        assertThat(workspaceColumnList).hasSize(databaseSizeBeforeCreate + 1);
        WorkspaceColumn testWorkspaceColumn = workspaceColumnList.get(workspaceColumnList.size() - 1);
        assertThat(testWorkspaceColumn.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createWorkspaceColumnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workspaceColumnRepository.findAll().size();

        // Create the WorkspaceColumn with an existing ID
        workspaceColumn.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkspaceColumnMockMvc.perform(post("/api/workspace-columns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workspaceColumn)))
            .andExpect(status().isBadRequest());

        // Validate the WorkspaceColumn in the database
        List<WorkspaceColumn> workspaceColumnList = workspaceColumnRepository.findAll();
        assertThat(workspaceColumnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWorkspaceColumns() throws Exception {
        // Initialize the database
        workspaceColumnRepository.saveAndFlush(workspaceColumn);

        // Get all the workspaceColumnList
        restWorkspaceColumnMockMvc.perform(get("/api/workspace-columns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workspaceColumn.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getWorkspaceColumn() throws Exception {
        // Initialize the database
        workspaceColumnRepository.saveAndFlush(workspaceColumn);

        // Get the workspaceColumn
        restWorkspaceColumnMockMvc.perform(get("/api/workspace-columns/{id}", workspaceColumn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(workspaceColumn.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkspaceColumn() throws Exception {
        // Get the workspaceColumn
        restWorkspaceColumnMockMvc.perform(get("/api/workspace-columns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkspaceColumn() throws Exception {
        // Initialize the database
        workspaceColumnService.save(workspaceColumn);

        int databaseSizeBeforeUpdate = workspaceColumnRepository.findAll().size();

        // Update the workspaceColumn
        WorkspaceColumn updatedWorkspaceColumn = workspaceColumnRepository.findOne(workspaceColumn.getId());
        // Disconnect from session so that the updates on updatedWorkspaceColumn are not directly saved in db
        em.detach(updatedWorkspaceColumn);
        updatedWorkspaceColumn
            .name(UPDATED_NAME);

        restWorkspaceColumnMockMvc.perform(put("/api/workspace-columns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWorkspaceColumn)))
            .andExpect(status().isOk());

        // Validate the WorkspaceColumn in the database
        List<WorkspaceColumn> workspaceColumnList = workspaceColumnRepository.findAll();
        assertThat(workspaceColumnList).hasSize(databaseSizeBeforeUpdate);
        WorkspaceColumn testWorkspaceColumn = workspaceColumnList.get(workspaceColumnList.size() - 1);
        assertThat(testWorkspaceColumn.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingWorkspaceColumn() throws Exception {
        int databaseSizeBeforeUpdate = workspaceColumnRepository.findAll().size();

        // Create the WorkspaceColumn

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWorkspaceColumnMockMvc.perform(put("/api/workspace-columns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workspaceColumn)))
            .andExpect(status().isCreated());

        // Validate the WorkspaceColumn in the database
        List<WorkspaceColumn> workspaceColumnList = workspaceColumnRepository.findAll();
        assertThat(workspaceColumnList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWorkspaceColumn() throws Exception {
        // Initialize the database
        workspaceColumnService.save(workspaceColumn);

        int databaseSizeBeforeDelete = workspaceColumnRepository.findAll().size();

        // Get the workspaceColumn
        restWorkspaceColumnMockMvc.perform(delete("/api/workspace-columns/{id}", workspaceColumn.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkspaceColumn> workspaceColumnList = workspaceColumnRepository.findAll();
        assertThat(workspaceColumnList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkspaceColumn.class);
        WorkspaceColumn workspaceColumn1 = new WorkspaceColumn();
        workspaceColumn1.setId(1L);
        WorkspaceColumn workspaceColumn2 = new WorkspaceColumn();
        workspaceColumn2.setId(workspaceColumn1.getId());
        assertThat(workspaceColumn1).isEqualTo(workspaceColumn2);
        workspaceColumn2.setId(2L);
        assertThat(workspaceColumn1).isNotEqualTo(workspaceColumn2);
        workspaceColumn1.setId(null);
        assertThat(workspaceColumn1).isNotEqualTo(workspaceColumn2);
    }
}
