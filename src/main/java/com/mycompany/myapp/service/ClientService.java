package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.domain.Project;
import com.mycompany.myapp.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Client.
 */
@Service
@Transactional
public class ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Save a client.
     *
     * @param client the entity to save
     * @return the persisted entity
     */
    public Client save(Client client) {
        log.debug("Request to save Client : {}", client);
        return clientRepository.save(client);
    }

    /**
     * Get all the clients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        log.debug("Request to get all Clients");
        return clientRepository.findAll(pageable);
    }

    /**
     * Get one client by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Client findOne(Long id) {
        log.debug("Request to get Client : {}", id);
        Client oneWithEagerRelationships = clientRepository.findOneWithEagerRelationships(id);
        //return clientRepository.findOneWithEagerRelationships(id);
        return oneWithEagerRelationships;
    }

    /**
     * Get client projects.
     *
     * @param id the id of the entity
     * @param pageble the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Project> findClientProjects(Long id, Pageable pageble) {
        log.debug("Request to get Client projects");
        return clientRepository.findAllProjectsOfClient(id, pageble);
    }

    /**
     * Delete the client by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Client : {}", id);
        clientRepository.delete(id);
    }
}
