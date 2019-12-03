package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;



/**
 * A Project.
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "project_client",
               joinColumns = @JoinColumn(name="projects_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="clients_id", referencedColumnName="id"))
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Workspace> workspaces = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Project name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Project clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Project addClient(Client client) {
        this.clients.add(client);
        client.getProjects().add(this);
        return this;
    }

    public Project removeClient(Client client) {
        this.clients.remove(client);
        client.getProjects().remove(this);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Workspace> getWorkspaces() {
        return workspaces;
    }

    public Project workspaces(Set<Workspace> workspaces) {
        this.workspaces = workspaces;
        return this;
    }

    public Project addWorkspace(Workspace workspace) {
        this.workspaces.add(workspace);
        workspace.setProject(this);
        return this;
    }

    public Project removeWorkspace(Workspace workspace) {
        this.workspaces.remove(workspace);
        workspace.setProject(null);
        return this;
    }

    public void setWorkspaces(Set<Workspace> workspaces) {
        this.workspaces = workspaces;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        if (project.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
