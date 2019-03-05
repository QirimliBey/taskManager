package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Workspace.
 */
@Entity
@Table(name = "workspace")
public class Workspace implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "workspace")
    @JsonIgnore
    private Set<WorkspaceColumn> workspaceColumns = new HashSet<>();

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

    public Workspace name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public Workspace project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<WorkspaceColumn> getWorkspaceColumns() {
        return workspaceColumns;
    }

    public Workspace workspaceColumns(Set<WorkspaceColumn> workspaceColumns) {
        this.workspaceColumns = workspaceColumns;
        return this;
    }

    public Workspace addWorkspaceColumn(WorkspaceColumn workspaceColumn) {
        this.workspaceColumns.add(workspaceColumn);
        workspaceColumn.setWorkspace(this);
        return this;
    }

    public Workspace removeWorkspaceColumn(WorkspaceColumn workspaceColumn) {
        this.workspaceColumns.remove(workspaceColumn);
        workspaceColumn.setWorkspace(null);
        return this;
    }

    public void setWorkspaceColumns(Set<WorkspaceColumn> workspaceColumns) {
        this.workspaceColumns = workspaceColumns;
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
        Workspace workspace = (Workspace) o;
        if (workspace.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workspace.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Workspace{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
