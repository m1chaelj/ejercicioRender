package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Tag(name = "Tareas", description = "API para gestionar tareas")
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Operation(summary = "Obtener todas las tareas")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @Operation(summary = "Obtener una tarea por ID")
    @ApiResponse(responseCode = "200", description = "Tarea encontrada", 
        content = @Content(schema = @Schema(implementation = Task.class)))
    @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(
        @Parameter(description = "ID de la tarea") @PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear una nueva tarea")
    @ApiResponse(responseCode = "200", description = "Tarea creada", 
        content = @Content(schema = @Schema(implementation = Task.class)))
    @PostMapping
    public Task createTask(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la nueva tarea",
            required = true,
            content = @Content(schema = @Schema(implementation = Task.class))
        )
        @RequestBody Task task) {
        return taskService.save(task);
    }

    @Operation(summary = "Actualizar una tarea existente")
    @ApiResponse(responseCode = "200", description = "Tarea actualizada", 
        content = @Content(schema = @Schema(implementation = Task.class)))
    @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
        @Parameter(description = "ID de la tarea") @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos actualizados de la tarea",
            required = true,
            content = @Content(schema = @Schema(implementation = Task.class))
        )
        @RequestBody Task task) {
        Task updated = taskService.update(id, task);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una tarea")
    @ApiResponse(responseCode = "204", description = "Tarea eliminada")
    @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
        @Parameter(description = "ID de la tarea") @PathVariable Long id) {
        if (taskService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
