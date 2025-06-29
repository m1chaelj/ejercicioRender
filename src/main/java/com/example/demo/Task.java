package com.example.demo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa una tarea")
public class Task {
    @Schema(description = "Identificador único de la tarea", example = "1")
    private Long id;

    @Schema(description = "Título de la tarea", example = "Comprar leche")
    private String title;

    @Schema(description = "Indica si la tarea está completada", example = "false")
    private boolean completed;

    public Task() {}

    public Task(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
