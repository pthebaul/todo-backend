package fr.paulthebault.todobackend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.paulthebault.todobackend.model.Task;
import fr.paulthebault.todobackend.service.TaskService;

@RestController
@CrossOrigin("http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Create - Add a new task
     * 
     * @param task An object task
     * @return The task object saved
     */
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    /**
     * Read - Get one task
     * 
     * @param id - The id of the task
     * @return - A Task object full filled
     */
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") final Long id) {
        Optional<Task> task = taskService.getTask(id);
        if (task.isPresent()) {
            return task.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all tasks
     * 
     * @return - An Iterable object of Task full filled
     */
    @GetMapping("/tasks")
    public Iterable<Task> getTasks() {
        return taskService.getTasks();
    }

    /**
     * Update - Update an existing task
     * 
     * @param id - The id of the task to update
     * @param task - The task object updated
     * @return
     */
    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable("id") final Long id, @RequestBody Task task) {
        Optional<Task> e = taskService.getTask(id);
        if (e.isPresent()) {
            Task currentTask = e.get();

            String title = task.getTitle();
            if (title != null) {
                currentTask.setTitle(title);
            }
            String description = task.getDescription();
            if (description != null) {
                currentTask.setDescription(description);
            }
            Boolean isDone = task.getIsDone();
            if (isDone != null) {
                currentTask.setIsDone(isDone);
            }
            Long orderValue = task.getOrderValue();
            if (orderValue != null) {
                currentTask.setOrderValue(orderValue);
            }
            taskService.saveTask(currentTask);
            return currentTask;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a task
     * 
     * @param id - The id of the task to delete
     */
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") final Long id) {
        taskService.deleteTask(id);
    }

}