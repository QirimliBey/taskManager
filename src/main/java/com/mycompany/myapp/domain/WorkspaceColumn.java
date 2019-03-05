package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WorkspaceColumn.
 */
@Entity
@Table(name = "workspace_column")
public class WorkspaceColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Workspace workspace;

    @OneToMany(mappedBy = "workspaceColumn")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();

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

    public WorkspaceColumn name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public WorkspaceColumn workspace(Workspace workspace) {
        this.workspace = workspace;
        return this;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public WorkspaceColumn tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public WorkspaceColumn addTask(Task task) {
        this.tasks.add(task);
        task.setWorkspaceColumn(this);
        return this;
    }

    public WorkspaceColumn removeTask(Task task) {
        this.tasks.remove(task);
        task.setWorkspaceColumn(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
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
        WorkspaceColumn workspaceColumn = (WorkspaceColumn) o;
        if (workspaceColumn.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workspaceColumn.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkspaceColumn{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
